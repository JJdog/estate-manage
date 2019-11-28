package com.lanswon.estate.service;


import com.lanswon.commons.core.time.DateTimeUtil;
import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.commons.web.rtn.SimpleRtnDTO;
import com.lanswon.estate.bean.pojo.Deal;
import com.lanswon.estate.bean.pojo.RentCharge;
import com.lanswon.estate.bean.vo.DetailDealVO;
import com.lanswon.estate.bean.vo.DetailRentChargeVO;
import com.lanswon.estate.bean.vo.MonthRentChargeVO;
import com.lanswon.estate.bean.vo.report.RentChargeHasDateAndLastNoDateVO;
import com.lanswon.estate.mapper.DealMapper;
import com.lanswon.estate.mapper.HouseResourceMapper;
import com.lanswon.estate.mapper.RentChargeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
	private HouseResourceMapper houseResourceMapper;


	/**
	 * 获得租金信息
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
		int months = DateTimeUtil.getMonthsBtDate(deal.getStartTime(), deal.getEndTime());

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

		for (Long aLong : deal.getFkHouseResourceId()) {
			monthRent = monthRent + houseResourceMapper.getHouseMonthRentByResourceId(aLong);
		}


		// 计算 交租时间 & 租金
		for (int i = 0; i < k; i++) {
			RentCharge rentCharge = new RentCharge();

			// 收款日期
			Calendar mustRentCalendar = DateTimeUtil.addMonth(deal.getStartTime(), rentMonth * i);

			// 关联的合同信息
			rentCharge.setFkDealId(deal.getId());
			// 收租时间(精确到 年/月/日)
			rentCharge.setRentDate(mustRentCalendar.getTime());
			// 收租年份
			rentCharge.setRentYear(mustRentCalendar.get(Calendar.YEAR));
			// 收租月份
			rentCharge.setRentMonth(mustRentCalendar.get(Calendar.MONTH) + 1);
			// 未启用(审核后启用)
			rentCharge.setRentStatus(2);

			/* 判断是否是月的第一天
			 * Y:无头无尾，正常交钱
			 * N:有头有尾，判断是第一次还是最后一次
			 * */
			if (mustRentCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
				/* 一个循环单位的钱 */
				rentCharge.setMustCharge(rentMonth * monthRent);
			} else {
				/* 头租金 */
				int headMonthRent =(int) Math.ceil(DateTimeUtil.getDaysToEndOfMonth(deal.getStartTime()) * (monthRent / 30));
				/* 尾租金 */
				int footMonthRent = (int) Math.ceil(DateTimeUtil.getDaysToStartOfMonth(deal.getEndTime()) * (monthRent / 30));
				//第一次交钱
				if (i == 0) {
					/* 头租金 + 一个循环单位的钱 */
					rentCharge.setMustCharge(headMonthRent + monthRent * (rentMonth - 1));
				} else if (i == k - 1) {
					/* 尾租金 + 一个循环单位-1 */
					rentCharge.setMustCharge(footMonthRent + monthRent * rentMonth);
				} else {
					rentCharge.setMustCharge(monthRent * rentMonth);
				}
			}

			// 租金列表
			rentCharges.add(rentCharge);
		}
		return rentCharges;
	}

	/**
	 * 合同终止后计算尾款
	 * @param id 合同id
	 * @param date 终结时间
	 */
	@Transactional(rollbackFor = Exception.class)
	public void computeTail(long id, Date date) {

		// 0.获得最近的两个收租节点(第一个是最后一次收租节点)
		RentChargeHasDateAndLastNoDateVO hasDateAndLastNoDateVO = new RentChargeHasDateAndLastNoDateVO();
		hasDateAndLastNoDateVO.setK1Date(rentChargeMapper.getLastHasRentedDateByDealId(id));
		hasDateAndLastNoDateVO.setK2Date(rentChargeMapper.getNextRentDate(id));



		/* 1.冻结资金 */
		if (!rentChargeMapper.freezeRentCharge(id,date)){
			log.error("冻结租金失败");
			throw new RuntimeException("冻结租金失败");
		}

		/* 2. 插入尾款 */
		// k1 < 结束点 < k2 (退钱)
		if (date.before(hasDateAndLastNoDateVO.getK2Date())){

		}
		// k2 < 结束点  (收钱)
		if(date.after(hasDateAndLastNoDateVO.getK2Date())){

		}
		// 生成尾款
		double tail = rentChargeMapper.generateTail(id,date);

		RentCharge rentCharge = new RentCharge();
		rentCharge.setFkDealId(id);
		rentCharge.setRentDate(date);
		rentCharge.setRentYear(DateTimeUtil.getYearOfDate(date));
		rentCharge.setRentMonth(DateTimeUtil.getMonthByDate(date));
		// 这个租金默认启用
		rentCharge.setRentStatus(1);
		rentCharge.setMustCharge(tail);
		// 插入尾款
		if (rentChargeMapper.insert(rentCharge) == 0){
			log.error("插入尾款租金信息失败");
			throw new RuntimeException("插入尾款租金信息失败");
		}

		log.info("计算合同尾款成功");
	}

	public DTO pay4Rent(long id, double money) {
		log.info("租金节点{}收租{}",id,money);

		if (!rentChargeMapper.pay4Rent(id,money)){
			log.error("交租金失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
	}
}
