package com.lanswon.estate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.core.serial.SerialEnum;
import com.lanswon.commons.core.serial.SerialNo;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.PojoConstants;
import com.lanswon.estate.bean.cd.DealCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.pojo.MoneyDepositMust;
import com.lanswon.estate.bean.vo.*;
import com.lanswon.estate.bean.vo.page.DealPage;
import com.lanswon.estate.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
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

	static AtomicInteger serialNo = new AtomicInteger(0);


	@Transactional(rollbackFor = Exception.class)
	public DTO insertDeal(Deal deal) {
		log.info("新增合同信息");

		// 0.一些校验
		deal.getDealAndHouses().forEach(dealAndHouse -> {
			if (houseResourceMapper.getHouseResourceDetailInfo(dealAndHouse.getFkHouseResourceId()).getRentCode() == PojoConstants.RESOURCE_HAS_RENT) {
				log.error("房源已被租赁");
				throw new RuntimeException("房源"+dealAndHouse.getFkHouseResourceId()+"已被租赁");
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
		GregorianCalendar a = new GregorianCalendar();
		a.setTime(DateTimeUtil.addMonth(deal.getStartTime(), deal.getRentMonth()).getTime());
		a.add(Calendar.DAY_OF_YEAR,-1);
		deal.setEndTime(a.getTime());
		// 合同正常存在
		deal.setDealExistStatus(1);
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
			if (!houseResourceMapper.updateResource2HasRented(dealAndHouse.getFkHouseResourceId(),PojoConstants.RESOURCE_HAS_RENT)) {
				log.error("修改房源状态为-->已出租  失败");
				throw new RuntimeException("更新房源状态为[已出租]失败");
			}
		});


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
		deal.setEndTime(DateTimeUtil.addMonth(deal.getStartTime(),deal.getRentMonth()).getTime());
		deal.setDealExistStatus(1);
		deal.setDealReviewStatus(1);

		// 1.修改合同
		deal.setUpdatedTime(new Date());
		if (dealMapper.updateById(deal) == 0) {
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			throw  new RuntimeException("修改合同失败");
		}

		// 1.1 解绑房源

		dealMapper.getHouseResourceIdByDealId(deal.getId()).forEach(rid ->{
			if (!houseResourceMapper.updateResource2FreeRent(rid,PojoConstants.RESOURCE_NO_RENT)){
				log.error("释放房源失败");
				throw  new RuntimeException("释放房源失败");
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
			if (!houseResourceMapper.updateResource2HasRented(dealAndHouse.getFkHouseResourceId(),PojoConstants.RESOURCE_HAS_RENT)) {
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
			dealPage.setMustMoney(dealMapper.getMustMoneyByDealId(dealPage.getId()));
			// 实收信息
			log.info("获得合同-->{}的实收款信息",dealPage.getId());
			dealPage.setTransFlows(dealMapper.getTransFlowByDealId(dealPage.getId()));
		});


		log.info(CustomRtnEnum.SUCCESS.toString());

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), dealInfoList);
	}

	public DTO getDetailDealInfo(long id) {
		log.info("获得id为：{}的合同信息", id);

		// 获得合同信息
		DetailDealVO dealVO = dealMapper.getDetailDealInfo(id);

		if (dealVO == null) {
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		// 获得对应房源信息
		houseResourceMapper.getHouseResourceDetailByDealId(id);
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), dealVO);
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
		if (!dealMapper.reviewDeal(id, status)) {
			log.error("修改合同-->{}--状态失败", id);
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		// 2.1.通过审核(启用租金)
		if (status == PojoConstants.DEAL_REVIEW_PASS){
			log.info("启用租金");
			if (!rentChargeMapper.enableRentCharge(id,PojoConstants.MUST_NORMAL)){
				log.error("启用租金失败");
				throw new RuntimeException("启用租金失败");
			}
		}
		// 2.2.未通过审核(释放房源，冻结租金)
		if (status == PojoConstants.DEAL_REVIEW_DENIED){
			log.info("合同未通过审核");

			// 2.2.1.释放房源
			dealMapper.getHouseResourceIdByDealId(id).forEach(rid -> {
				if (!houseResourceMapper.updateResource2FreeRent(rid,PojoConstants.RESOURCE_NO_RENT)){
					log.error("释放房源失败");
					throw  new RuntimeException("释放房源失败");
				}
			});

			// todo 没必要冻结
			// 2.2.2冻结租金
			if (!rentChargeMapper.recallRentChargeByDealId(id)){
				log.error("取消租金失败");
				throw new RuntimeException("取消租金失败");
			}
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO stopDeal(long id, Date date) {
		log.info("终止合同id为:{}的合同",id);

		DealStatusVO statusVO = dealMapper.getDealStatusById(id);

		// 0.校验合同审核状态
		if (statusVO.getDealReviewStatus() != PojoConstants.DEAL_REVIEW_PASS){
			log.error("合同审核状态为--->{},不可通提前结束",statusVO.getDealReviewStatus());
			throw new RuntimeException("合同状态不是通过审核状态,不可提前结束");
		}


		// 1.修改合同状态为 4(异常终止)
		if (!dealMapper.stopDeal(id, PojoConstants.DEAL_RUN_ABNORMAL_STOP)){
			log.error("合同终止失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		// 2.释放房源
		dealMapper.getHouseResourceIdByDealId(id).forEach(rid -> {
			if (!houseResourceMapper.updateResource2FreeRent(rid,PojoConstants.RESOURCE_NO_RENT)){
				log.error("释放房源失败");
				throw  new RuntimeException("释放房源失败");
			}
		});
		//if (!houseResourceMapper.updateResource2FreeRent(dealMapper.getHouseResourceIdByDealId(id),PojoConstants.RESOURCE_NO_RENT)){
		//	log.error("释放房源失败");
		//	throw  new RuntimeException("终止合同-释放房源失败");
		//}

		// 3.未完成租金冻结
		if (!rentChargeMapper.freezeRentCharge(id, date,PojoConstants.MUST_CANCEL)){
			log.error("取消租金失败");
			throw  new RuntimeException("终止合同-取消租金失败");
		}

		// 4.计算尾款
		rentChargeService.computeTail(id,date);

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
		StringBuilder builder = new StringBuilder();
		builder.append(LocalDate.now().getYear());
		builder.append(LocalDate.now().getMonthValue());

		// 不是同一天(序列归0)
		if (!s.equals(builder.toString())){
			serialNo.set(0);
			return;
		}
		serialNo.set(Integer.valueOf(latestSeq.substring(10)));
	}

}
