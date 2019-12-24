package com.lanswon.estate.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.PojoConstants;
import com.lanswon.estate.bean.cd.RentCD;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.pojo.DealAndHouse;
import com.lanswon.estate.bean.pojo.MoneyRealFlow;
import com.lanswon.estate.bean.pojo.RentCharge;
import com.lanswon.estate.bean.vo.*;
import com.lanswon.estate.bean.vo.page.MustMoneyPageVO;
import com.lanswon.estate.bean.vo.report.RentChargeHasDateAndLastNoDateVO;
import com.lanswon.estate.mapper.DealMapper;
import com.lanswon.estate.mapper.HouseResourceMapper;
import com.lanswon.estate.mapper.MoneyRealFlowMapper;
import com.lanswon.estate.mapper.RentChargeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 合同租金service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class RentChargeService {


	@Resource
	private DealMapper dealMapper;
	@Resource
	private RentChargeMapper rentChargeMapper;
	@Resource
	private MoneyRealFlowMapper moneyRealFlowMapper;
	@Resource
	private HouseResourceMapper houseResourceMapper;


	/**
	 * 获得租金信息
	 *
	 * @param id 合同id
	 * @return 租金信息
	 */
	public DTO getDetailRentChargeInfo(long id) {

		log.info("获得合同id为:{}的合同&租金信息", id);
		DetailRentChargeVO detailRentChargeVO = new DetailRentChargeVO();

		DetailDealVO detailDealInfo = dealMapper.getDetailDealInfo(id);

		// 合同详情
		if (detailDealInfo == null) {
			log.error("合同id：{}的资源{}", id, CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		// 租金详情
		List<MonthRentChargeVO> monthRentChargeList = rentChargeMapper.getRentChargeInfoByDealId(id);

		if (monthRentChargeList.isEmpty()) {
			log.error("合同id：{}的收款月节点月租金为{}", id, CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(), CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		detailRentChargeVO.setDetailDeal(detailDealInfo);
		detailRentChargeVO.setMonthRentCharge(monthRentChargeList);

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), detailRentChargeVO);
	}

	public DTO getDetailRentChargeInfoByDate(RentCD cd) {
		log.info("获得-->{}的应收租金情况", cd.getDate());

		IPage<MustMoneyPageVO> rentChargeInfoByDate = rentChargeMapper.getRentChargeInfoByDatePage(new Page<>(cd.getPage(), cd.getLimit()), cd);

		if (rentChargeInfoByDate.getRecords().isEmpty()) {
			log.error("应收信息为空");
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(), rentChargeInfoByDate);
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(), rentChargeInfoByDate);
	}


	/**
	 * 依据合同计算租金
	 * <p>
	 * 1.合同开始日期是否是月的第一天
	 * 1.1--Y:收款=方式X月租金
	 * 1.2--N:收款=头租金+方式X月租金
	 * <p>
	 * 2.计算收款的时间节点
	 *
	 * </P>
	 *
	 * @param deal 合同信息
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<RentCharge> computeRent(Deal deal) {

		List<RentCharge> rentCharges = new ArrayList<>();
		/* 总月数*/
		int months = deal.getRentMonth();

		/* 一次交几月 */
		int rentMonth = deal.getPayType();

		/* 交的次数 */
		int k = months / rentMonth;

		/* 计算收租次数 */
		if (months % rentMonth != 0) {
			log.error("不支持的交租方式");
			throw new RuntimeException("不支持的交租方式");
		}

		/* 月租金 */
		double monthRent = 0.0;

		for (DealAndHouse dealAndHouse : deal.getDealAndHouses()) {
			log.info("计算房源-->{}的月租金", dealAndHouse.getFkHouseResourceId());
			monthRent = monthRent + dealAndHouse.getRealMoney() * houseResourceMapper.getHouseResourceAreaById(dealAndHouse.getFkHouseResourceId());
		}

		// 计算 交租时间 & 租金
		for (int i = 0; i < k; i++) {

			RentCharge rentCharge = new RentCharge();
			// 收款日期
			Calendar mustRentCalendar = DateTimeUtil.addMonth(deal.getStartTime(), rentMonth * i);

			// 关联的合同信息
			rentCharge.setFkDealId(deal.getId());
			// 未启用(审核后启用)
			rentCharge.setIsEnable(PojoConstants.MUST_NOT_AVAILABLE);
			// 创建时间
			rentCharge.setCreatedTime(new Date());

			/* 情况1:只要交一次 */
			if (k == 1) {
				// 关联的合同信息
				rentCharge.setFkDealId(deal.getId());
				// 收租时间(精确到 年/月/日)
				rentCharge.setRentDate(mustRentCalendar.getTime());
				// 应收款
				rentCharge.setMustCharge(new BigDecimal(rentMonth * monthRent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			} else {
				/* 情况2:交多次 */
				/*=======第一次交=======*/
				if (i == 0) {

					rentCharge.setRentDate(mustRentCalendar.getTime());
					/* 是否第一天 */
					if (mustRentCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
						/* 一个循环单位的钱 */
						rentCharge.setMustCharge(new BigDecimal(rentMonth * monthRent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					} else {
						/* 头租金 */
						//int headMonthRent =(int) Math.ceil(DateTimeUtil.getDaysToEndOfMonth(deal.getStartTime()) * (monthRent / 30));
						double headMonthRent = DateTimeUtil.getDaysToEndOfMonth(deal.getStartTime()) * (monthRent * 12 / 365);
						/* 头租金 + 一个循环单位-1月的钱 */
						rentCharge.setMustCharge(new BigDecimal(headMonthRent + monthRent * (rentMonth - 1)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				} else if (i == k - 1) {
					/*=======最后一次交=======*/
					/* 尾租金 */
					//int footMonthRent = (int) Math.ceil(DateTimeUtil.getDaysToStartOfMonth(deal.getEndTime()) * (monthRent / 30));
					double footMonthRent = DateTimeUtil.getDaysToStartOfMonth(deal.getEndTime()) * (monthRent * 12 / 365);
					/* 尾租金 + 一个循环单位-1 */
					rentCharge.setMustCharge(new BigDecimal(footMonthRent + monthRent * rentMonth).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					/* 每月一号收钱 */
					rentCharge.setRentDate(DateTimeUtil.getDayOfMonth(mustRentCalendar.getTime(), 1).getTime());
				} else {
					/*=======正常交=======*/
					//rentCharge.setMustCharge(monthRent * rentMonth);
					rentCharge.setMustCharge(new BigDecimal(rentMonth * monthRent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					/* 每月一号收钱 */
					rentCharge.setRentDate(DateTimeUtil.getDayOfMonth(mustRentCalendar.getTime(), 1).getTime());
				}
			}

			// 租金列表
			rentCharges.add(rentCharge);
		}
		return rentCharges;
	}

	/**
	 * 合同终止后计算尾款
	 *
	 * @param id   合同id
	 * @param date 终结时间
	 */
	@Transactional(rollbackFor = Exception.class)
	public void computeTail(long id, Date date) {

		// 0.获得最近的两个收租节点(第一个是最后一次收租节点)
		RentChargeHasDateAndLastNoDateVO hasDateAndLastNoDateVO = new RentChargeHasDateAndLastNoDateVO();

		// 最后一次交租日期
		Date lastDate = rentChargeMapper.getLastHasRentedDateByDealId(id);

		Date lastDateAddMonth = DateTimeUtil.addMonth(lastDate, dealMapper.getPermonthByDealId(id)).getTime();
		// 应收天数
		long daysBtDate = DateTimeUtil.getDaysBtDate(lastDateAddMonth, date);

		double tail = dealMapper.getMonthRentByDealId(id) * 12 /360 * daysBtDate;

		// 生成尾款
		//double tail = rentChargeMapper.generateTail(id, date);

		RentCharge rentCharge = new RentCharge();
		rentCharge.setFkDealId(id);
		rentCharge.setRentDate(date);
		// 这个租金默认启用
		rentCharge.setIsEnable(PojoConstants.MUST_NORMAL);
		rentCharge.setMustCharge(new BigDecimal(tail).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		// 插入尾款
		if (rentChargeMapper.insert(rentCharge) == 0) {
			log.error("插入尾款租金信息失败");
			throw new RuntimeException("插入尾款租金信息失败");
		}

		log.info("计算合同尾款成功");
	}

	/***
	 * 收租金
	 * @param moneyRealFlow 租金
	 * @return dto
	 */
	public DTO pay4Rent(MoneyRealFlow moneyRealFlow) {
		log.info("收取{}", moneyRealFlow.getMoney());
		moneyRealFlow.setCreatedTime(new Date());
		if (moneyRealFlowMapper.insert(moneyRealFlow) == 0) {
			log.error("交租金失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}
}
