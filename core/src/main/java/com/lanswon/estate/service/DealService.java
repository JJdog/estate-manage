package com.lanswon.estate.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.itextpdf.text.DocumentException;
import com.lanswon.commons.core.serial.SerialEnum;
import com.lanswon.commons.core.serial.SerialNo;
import com.lanswon.commons.core.time.DateFormatEnum;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.cd.DealStopCD;
import com.lanswon.estate.bean.cd.DealStopOkCD;
import com.lanswon.estate.bean.pojo.DealAndHouse;
import com.lanswon.estate.bean.pojo.MoneyDamageMust;
import com.lanswon.estate.bean.pojo.backup.BackupDeal;
import com.lanswon.estate.bean.pojo.backup.BackupMidDealHouse;
import com.lanswon.estate.bean.vo.doc.DealTemp;
import com.lanswon.estate.constant.DatabaseConstants;
import com.lanswon.estate.constant.PojoConstants;
import com.lanswon.estate.bean.cd.DealCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.pojo.MoneyDepositMust;
import com.lanswon.estate.bean.vo.*;
import com.lanswon.estate.bean.vo.page.DealPage;
import com.lanswon.estate.mapper.*;
import com.lanswon.estate.service.pdf.GenertorPdfImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 合同service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class DealService {

	@Resource
	private RentChargeService rentChargeService;
	@Resource
	private DealMapper dealMapper;
	@Resource
	private HouseResourceMapper houseResourceMapper;
	@Resource
	private RentChargeMapper rentChargeMapper;
	@Resource
	private DealAndHouseMapper dealAndHouseMapper;
	@Resource
	private MoneyDepositMustMapper moneyDepositMustMapper;
	@Resource
	private MoneyDamageMustMapper moneyDamageMustMapper;
	@Resource
	private MoneyDepositReturnMapper moneyDepositReturnMapper;
	@Resource
	private BackupDealMapper backupDealMapper;
	@Resource
	private BackupDealAndHouseMapper backupDealAndHouseMapper;

	/** 合同编号 */
	private static AtomicInteger serialNo = new AtomicInteger(0);


	@Transactional(rollbackFor = Exception.class)
	public DTO insertDeal(Deal deal) {
		log.info("新增合同信息");

		// 0.一些校验(新签/续签 时间校验)
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			Date houseEndtime   = dealMapper.getDealEndtimeByHouseId(dealAndHouse.getFkHouseResourceId());
			if (houseEndtime != null){
				if (houseEndtime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(deal.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
					log.error("房源在-->{}还未释放",dealAndHouse.getFkHouseResourceId());
					throw new RuntimeException("房源"+dealAndHouse.getFkHouseResourceId()+"还未释放");
				}
			}
		});

		String dealType;
		switch (deal.getDealType().intValue()){
			case PojoConstants.DEAL_TYPE_XS:
				dealType = "XS";
				break;
			case PojoConstants.DEAL_TYPE_YS:
				dealType = "YS";
				break;
			case PojoConstants.DEAL_TYPE_GK:
				dealType = "GK";
				break;
			default:
				throw new RuntimeException("错误的合同类型");
		}


		// 合同编号
		changeSerial();
		deal.setDealSerial(SerialNo.builder(SerialEnum.PREFIX_TIME_YEAR_MONTH_SUFFIX)
				.withPrefix(dealType + "-")
				.withSuffix("-" + String.format("%03d", serialNo.incrementAndGet()))
				.generateSerial());

		// 推算结束时间
		deal.setEndTime(DateTimeUtil.convert2Date(DateTimeUtil.convertDate2LocalDateTime(deal.getStartTime())
				.plusMonths(deal.getRentMonth())
				.plusDays(-1)));
		// 合同正常存在
		deal.setDealExistStatus(PojoConstants.DEAL_RUN_NORMAL);
		// 合同未审核
		deal.setDealReviewStatus(PojoConstants.DEAL_REVIEW_NO);
		// 创建时间
		deal.setCreatedTime(new Date());


		// 1.插入合同
		log.info("插入合同");
		if (dealMapper.insert(deal) == 0) {
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		/*=======房源=========*/


		// 2.插入合同和房源关系
		log.info("绑定房源和合同");
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			log.info("绑定合同-->{}和房源:{}的关系",deal.getId(),dealAndHouse.getFkHouseResourceId());
			dealAndHouse.setCreatedTime(new Date());
			dealAndHouse.setFkDealId(deal.getId());
			if (dealAndHouseMapper.insert(dealAndHouse) == 0){
				log.error("绑定合同和房源关系失败");
				throw new RuntimeException("绑定合同和房源关系失败");
			}
		});

		// 3.修改房源状态
		log.info("修改房源状态为已租赁");
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			log.info("修改房源：{}的状态为已出租",dealAndHouse.getFkHouseResourceId());
			if (!houseResourceMapper.updateResourceRentStatus(dealAndHouse.getFkHouseResourceId(),PojoConstants.RESOURCE_HAS_RENT)) {
				log.error("修改房源状态为-->已出租  失败");
				throw new RuntimeException("更新房源状态为[已出租]失败");
			}
		});


		/*=======钱=========*/

		// 4.1.插入应收保证金
		log.info("插入应收保证金");
		int deposit = moneyDepositMustMapper.insert(new MoneyDepositMust()
				.toBuilder()
				.fkDealId(deal.getId())
				.deposit(deal.getDeposit())
				.isEnable(PojoConstants.DEPOSIT_STATUS_DISABLED)
				.build());
		if (deposit == 0){
			log.error("插入应收保证金失败");
			throw new RuntimeException("插入应收保证金失败");
		}

		// 4.2.合同的租金明细
		log.info("插入应收租金明细");
		rentChargeService.computeRent(deal).forEach(rentCharge -> {
			log.info("插入租金信息,合同编号{},年月{}", rentCharge.getFkDealId(), rentCharge.getRentDate());
			if (rentChargeMapper.insert(rentCharge) == 0) {
				log.error("租金插入异常--->租金年月{}", rentCharge.getRentDate());
				throw new RuntimeException("租金生成异常");
			}
		});


		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO deleteDeal(long id) {
		log.info("删除id为：{}的合同信息", id);
		// 合同状态
		DealStatusVO dealStatusVO = dealMapper.getDealStatusById(id);

		// 0.通过审核 合同不可删除
		if (dealStatusVO.getDealReviewStatus() == 2 ){
			log.error("合同{}已通过审核，不可删除",id);
			return new SimpleRtnDTO(500,"合同已通过审核，不可删除");
		}

		// 1.删除合同(真删除)
		if (dealMapper.deleteById(String.valueOf(id)) == 0) {
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			throw new RuntimeException("删除合同失败");
		}

		// 2.释放房源
		dealMapper.getHouseResourceIdByDealId(id).forEach(rid -> {
			if (!houseResourceMapper.updateResource2FreeRent(rid,PojoConstants.RESOURCE_NO_RENT)){
				log.error("释放房源失败");
				throw  new RuntimeException("释放房源失败");
			}
		});


		// 3.真删除租金信息
		if (!rentChargeMapper.deleteByDealId(id)){
			log.error("删除该合同的租金信息(真删除）");
			throw new RuntimeException("删除合同绑定的租金信息失败");
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}


	@Transactional(rollbackFor = Exception.class)
	public DTO updateDeal(Deal deal) {
		log.info("更新合同id为{}的合同信息", deal.getId());

		DealStatusVO statusVO = dealMapper.getDealStatusById(deal.getId());

		// 0.只有未审核可修改
		if (statusVO.getDealReviewStatus() != PojoConstants.DEAL_REVIEW_NO){
			log.error("合同{}已通过审核，不可修改",deal.getId());
			throw  new RuntimeException("合同已通过审核，不可修改");
		}

		// 设置结束时间
		// todo 时间不对
		deal.setEndTime(DateTimeUtil.convert2Date(DateTimeUtil.convertDate2LocalDateTime(deal.getStartTime())
				.plusMonths(deal.getRentMonth())
				.plusDays(-1)));
		deal.setDealExistStatus(PojoConstants.DEAL_RUN_NORMAL);
		deal.setDealReviewStatus(PojoConstants.DEAL_REVIEW_NO);

		// 1.修改合同
		deal.setUpdatedTime(new Date());
		if (dealMapper.updateById(deal) == 0) {
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			throw  new RuntimeException("修改合同失败");
		}

		// 1.1 解绑房源
		dealMapper.getHouseResourceIdByDealId(deal.getId()).forEach(rid ->{

			log.info("更新房源为闲置");
			if (!houseResourceMapper.updateResourceRentStatus(rid,PojoConstants.RESOURCE_NO_RENT)){
				log.error("更新房源为闲置失败");
				throw  new RuntimeException("更新房源为闲置失败");
			}

			log.info("删除原来合同房源绑定关系");
			if(dealAndHouseMapper.delete(new QueryWrapper<DealAndHouse>()
					.eq(DatabaseConstants.MID_DEAL_HOUSE_FK_DEAL_ID,deal.getId())
					.eq(DatabaseConstants.MID_DEAL_HOUSE_FK_HOUSE_ID,rid)) == 0){
				log.error("删除原来合同房源绑定关系失败");
				throw  new RuntimeException("删除原来合同房源绑定关系失败");
			}
		});


		// 1.2.插入合同和房源关系
		log.info("绑定房源和合同");
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			log.info("绑定合同-->{}和房源:{}的关系",deal.getId(),dealAndHouse.getFkHouseResourceId());
			dealAndHouse.setCreatedTime(new Date());
			dealAndHouse.setFkDealId(deal.getId());
			if (dealAndHouseMapper.insert(dealAndHouse) == 0){
				log.error("绑定合同和房源关系失败");
				throw new RuntimeException("绑定合同和房源关系失败");
			}
		});

		// 1.3 重新绑定房源
		log.info("修改房源状态为已租赁");
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			log.info("修改房源：{}的状态为已出租",dealAndHouse.getFkHouseResourceId());
			if (!houseResourceMapper.updateResourceRentStatus(dealAndHouse.getFkHouseResourceId(),PojoConstants.RESOURCE_HAS_RENT)) {
				log.error("修改房源状态为-->已出租  失败");
				throw new RuntimeException("更新房源状态为[已出租]失败");
			}
		});

		// 2.删除有租金方案(真删除)
		if (!rentChargeMapper.deleteByDealId(deal.getId())){
			log.error("删除该合同的租金信息(真删除）");
			throw new RuntimeException("删除合同绑定的租金信息失败");
		}


		// 3.重新计算租金插入
		rentChargeService.computeRent(deal).forEach(rentCharge -> {
			log.info("插入租金信息,合同编号{},年月{}", rentCharge.getFkDealId(), rentCharge.getRentDate());
			if (rentChargeMapper.insert(rentCharge) == 0) {
				log.error("租金插入异常--->年月{}", rentCharge.getRentDate());
				throw new RuntimeException("租金生成异常");
			}
		});

		log.info("删除原合同应收保证金");
		if (moneyDepositMustMapper.delete(new QueryWrapper<MoneyDepositMust>()
				.eq(DatabaseConstants.MONEY_DEPOSIT_MUST_FK_DEAL_ID,deal.getId())) == 0){
			log.error("删除原合同应收保证金 失败");
			throw new RuntimeException("删除原合同应收保证金 失败");

		}

		// 4.1.插入应收保证金
		log.info("插入应收保证金");
		int deposit = moneyDepositMustMapper.insert(new MoneyDepositMust()
				.toBuilder()
				.fkDealId(deal.getId())
				.deposit(deal.getDeposit())
				.build());
		if (deposit == 0){
			log.error("插入应收保证金失败");
			throw new RuntimeException("插入应收保证金失败");
		}


		log.info(CustomRtnEnum.SUCCESS.toString());

		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getDealInfoPage(DealCD cd) {

		log.info("获得合同信息---分页");


		IPage<DealPage> dealInfoList = dealMapper.getDealInfoPage(new Page<>(cd.getPage(), cd.getLimit()), cd);


		if (dealInfoList.getRecords().isEmpty()) {
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new DataRtnDTO<>(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg(),dealInfoList);
		}

		dealInfoList.getRecords().forEach(dealPage -> {
			// 房源信息
			log.info("获得合同-->{}的房源信息",dealPage.getId());
			dealPage.setHouseResourceDetail(houseResourceMapper.getHouseResourceDetailByDealId(dealPage.getId()));
			// 应收信息
			log.info("获得合同-->{}的应收款信息",dealPage.getId());
			List<MustMoneyVO> mustMoneys = dealMapper.getMustMoneyByDealId(dealPage.getId());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dealPage.getStartTime());
			if (calendar.get(Calendar.DAY_OF_MONTH) != 1){
				StringBuilder reason = new StringBuilder();
				reason.append(DateTimeUtil.formatDatetime(dealPage.getStartTime(),"yyyy-MM-dd"));
				reason.append("应收");
				reason.append(DateTimeUtil.formatDatetime(dealPage.getStartTime(),"yyyy-MM-dd"));
				reason.append("~");
				reason.append(DateTimeUtil.formatDatetime(DateTimeUtil.getEndDateOfMonth(DateTimeUtil.addMonth(dealPage.getStartTime(), dealPage.getPayTypeCode()-1).getTime()).getTime(),"yyyy-MM-dd"));
				reason.append("租金");
				mustMoneys.get(1).setMustReason(reason.toString());
			}
			dealPage.setMustMoney(mustMoneys);
			// 实收信息
			log.info("获得合同-->{}的实收款信息",dealPage.getId());
			dealPage.setTransFlows(dealMapper.getTransFlowByDealId(dealPage.getId()));

			// 保证金退还信息

			log.info("获得保证金退还信息");
			dealPage.setDepositReturns(moneyDepositReturnMapper.getDepositReturnsByDealId(dealPage.getId()));
		});


		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), dealInfoList);
	}

	public DTO getDetailDealInfo(long id) {
		log.info("获得id为：{}的合同信息", id);

		//// 获得合同信息
		//DetailDealVO dealVO = dealMapper.(id);
		//
		//if (dealVO == null) {
		//	log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
		//	return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		//}
		//
		//// 获得对应房源信息
		//houseResourceMapper.getHouseResourceDetailByDealId(id);
		//log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), null);
	}


	@Transactional(rollbackFor = Exception.class)
	public DTO reviewDeal(long id, int status) {
		log.info("审核合同id为{}的合同状态为{}", id, status);

		DealStatusVO statusVO = dealMapper.getDealStatusById(id);

		// 0.只有未审核可修改
		if (statusVO.getDealReviewStatus() != PojoConstants.DEAL_REVIEW_NO){
			log.error("合同{}已审核，不可重新审核",id);
			return new SimpleRtnDTO(500,"合同已通过审核，不可重新审核");
		}

		// 1.修改合同状态
		if (!dealMapper.updateDealReviewStatus(id, status)) {
			log.error("修改合同-->{}--状态失败", id);
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		// 2.1.通过审核(启用租金)
		if (status == PojoConstants.DEAL_REVIEW_PASS){
			log.info("启用租金");
			if (!rentChargeMapper.updateMustRentStatus(id,PojoConstants.MUST_NORMAL)){
				log.error("启用租金失败");
				throw new RuntimeException("启用租金失败");
			}

			log.info("启用保证金");
			if (!rentChargeMapper.updateDepositStatus(id,PojoConstants.DEPOSIT_STATUS_ENABLED)){
				log.error("启用保证金失败");
				throw new RuntimeException("启用保证金失败");
			}

			log.info("合同备份");
			BackupDeal backupDeal = dealMapper.getDealInfo2Backup(id);
			if (backupDealMapper.insert(backupDeal) == 0){
				log.error("备份合同失败");
				throw new RuntimeException("备份合同失败");
			}

			log.info("备份合同和房源");
			List<BackupMidDealHouse> backupMidDealHouses = dealAndHouseMapper.getDealAndHouseInfo2Backup(id);
			backupMidDealHouses.forEach(item->{
				if (backupDealAndHouseMapper.insert(item) == 0){
					log.error("备份合同和房源失败");
					throw new RuntimeException("备份合同和房源失败");
				}
			});
		}

		// 2.2.未通过审核(释放房源，冻结租金)
		if (status == PojoConstants.DEAL_REVIEW_DENIED){
			// todo 房源租金不做任何修改
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO stopDealApply(DealStopCD cd) {
		log.info("合同:{}申请提前结束",cd.getId());
		DealStatusVO statusVO = dealMapper.getDealStatusById(cd.getId());

		// 0.校验合同审核状态
		if (statusVO.getDealReviewStatus() != PojoConstants.DEAL_REVIEW_PASS){
			log.error("合同审核状态为--->{},不可通提前结束",statusVO.getDealReviewStatus());
			throw new RuntimeException("合同状态不是通过审核状态,不可申请提前结束");
		}

		// 1.修改合同状态为4(异常终止,提交申请)
		if (!dealMapper.stopDeal(cd.getId(), PojoConstants.DEAL_RUN_ABNORMAL_STOP_APPLYING)){
			log.error("合同终止申请失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}


		// todo 有问题
		// 3.未完成租金冻结
		rentChargeMapper.freezeRentCharge(cd.getId(), cd.getDate(),PojoConstants.MUST_CANCEL);
		//if (!){
		//	log.error("取消租金失败");
		//	throw  new RuntimeException("终止合同-取消租金失败");
		//}

		// 4.计算尾款// TODO 需要完善
		rentChargeService.computeTail(cd.getId(),cd.getDate());

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO stopDealOk(DealStopOkCD cd ) {
		log.info("终止合同id为:{}的合同",cd.getId());

		DealStatusVO statusVO = dealMapper.getDealStatusById(cd.getId());

		// 0.校验合同审核状态
		if (statusVO.getDealExistStatus() != PojoConstants.DEAL_RUN_ABNORMAL_STOP_APPLYING){
			log.error("合同审核状态为--->{},不可通提前结束",statusVO.getDealReviewStatus());
			throw new RuntimeException("合同状态不是提前结束,审核中状态,不可完成提前结束");
		}

		// 1.修改合同状态为5(异常终止,申请通过)
		if (!dealMapper.stopDeal(cd.getId(), PojoConstants.DEAL_RUN_ABNORMAL_STOP_APPLIED)){
			log.error("合同终止失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		// 2.释放房源
		dealMapper.getHouseResourceIdByDealId(cd.getId()).forEach(rid -> {
			log.info("修改房源为闲置");
			if (!houseResourceMapper.updateResourceRentStatus(rid,PojoConstants.RESOURCE_NO_RENT)){
				log.error("修改房源为闲置 失败");
				throw  new RuntimeException("修改房源为闲置  失败");
			}

		});

		// 3.启用尾款
		if (!rentChargeMapper.updateDepositStatus(cd.getId(),PojoConstants.DEPOSIT_STATUS_ENABLED)){
			log.error("启用尾款失败");
			throw new RuntimeException("启用尾款失败");
		}

		// 4.输入违约金
		if (moneyDamageMustMapper.insert(new MoneyDamageMust().toBuilder()
				.fkDealId(cd.getId())
				.money(cd.getMoney())
				.mustDate(cd.getMustDate())
				.isEnable(PojoConstants.DAMAGE_STATUS_ENABLED)
				.build()) == 0){
			log.error("插入违约金失败");
			throw new RuntimeException("插入违约金失败");
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getNoReviewDealInfoPage(DealCD cd) {
		log.info("获得未审核合同信息---分页");

		IPage<DealPage> dealInfoList = dealMapper.getNoReviewDealInfoPage(new Page<>(cd.getPage(), cd.getLimit()), cd);


		if (dealInfoList.getSize() == 0) {
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		dealInfoList.getRecords().forEach(dealPage -> {
					// 房源信息
					log.info("获得合同-->{}的房源信息", dealPage.getId());
					dealPage.setHouseResourceDetail(houseResourceMapper.getHouseResourceDetailByDealId(dealPage.getId()));
					// 应收信息
					log.info("获得合同-->{}的应收款信息", dealPage.getId());
					dealPage.setMustMoney(dealMapper.getMustMoneyByDealId(dealPage.getId()));
				});

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), dealInfoList);
	}


	public DTO getStopApply(DealCD cd) {
		log.info("获得提前结束审核中合同信息---分页");

		IPage<DealPage> dealInfoList = dealMapper.getStopApply(new Page<>(cd.getPage(), cd.getLimit()), cd);


		if (dealInfoList.getSize() == 0) {
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		dealInfoList.getRecords().forEach(dealPage -> {
			// 房源信息
			log.info("获得合同-->{}的房源信息", dealPage.getId());
			dealPage.setHouseResourceDetail(houseResourceMapper.getHouseResourceDetailByDealId(dealPage.getId()));
			// 应收信息
			log.info("获得合同-->{}的应收款信息", dealPage.getId());
			dealPage.setMustMoney(dealMapper.getMustMoneyByDealId(dealPage.getId()));
		});

		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), dealInfoList);
	}

	/**
	 * 修改序列号
	 * <P>
	 *     1.获得对应类型凭证的最新凭证号
	 *     2.如果时间和获得的凭证号的时间是不一样的那么将序列号归'0'[return]
	 *     3.如果是同一天，那么直接给对应的序列号增加器赋值[return]
	 *
	 *     Tips：
	 *     1.最新的序列号获得不能使用时间作为但一维度去查询，要加上序列号的后缀
	 * </P>
	 */
	private void changeSerial(){
		// 判断是否为同一天
		String latestSeq = dealMapper.getLatestSeqByType();
		if (latestSeq == null){
			serialNo.set(0);
			return;
		}
		String s = latestSeq.substring(3, 9);
		String nowMonth = DateTimeUtil.format(LocalDateTime.now(), DateFormatEnum.YYYY_MM_LINK);

		// 不是同一天(序列归0)
		if (!s.equals(nowMonth)){
			serialNo.set(0);
			return;
		}
		serialNo.set(Integer.valueOf(latestSeq.substring(10)));
	}


	/** 导出word */
	public XWPFTemplate getDealWord(Long did,HttpServletResponse response) throws IOException {

		DealTemp dealTemp = dealMapper.getDealWordInfo(did);

		//编码
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(dealTemp.getRenter(), "utf-8")+ ".docx");

		if ("0".equals(dealTemp.getFreeRent())){
			dealTemp.setFreeRent("");
		}else {
			dealTemp.setFreeRent("其中"+DateTimeUtil.format(DateTimeUtil.convertDate2LocalDateTime(dealTemp.getStartTime()), DateFormatEnum.YYYY_MM_DD_CN)+"至"+DateTimeUtil.format(DateTimeUtil.convertDate2LocalDate(dealTemp.getStartTime()).plusMonths(Long.valueOf(dealTemp.getFreeRent())),DateFormatEnum.YYYY_MM_DD_CN)+"为房屋装修期,免收房租。");
		}

		File file = ResourceUtils.getFile("classpath:temp.docx");
		XWPFTemplate template = XWPFTemplate.compile(file);

		template.render(dealTemp);

		return template;

	}

	public void getDealPdf(Long did,HttpServletResponse response) throws IOException, DocumentException {
		DealTemp dealTemp = dealMapper.getDealWordInfo(did);
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(dealTemp.getRenter(), "utf-8")+ ".pdf");
		GenertorPdfImpl.createPdf(new ByteArrayServletOutputStream(),dealTemp);
		GenertorPdfImpl.createPdf(response.getOutputStream(),dealTemp);

	}

}
