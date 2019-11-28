package com.lanswon.estate.bean.vo.report;


import lombok.Data;

/**
 * 每个月的房源统计
 *
 * @author jaswine
 */
@Data
public class MonthReportHouseVO {

	/** 月份 */
	private String hMonth;

	/** 新签合同数量 */
	private int newDealNum;

	/** 续签合同数量 */
	private int oldDealNum;

	/** 新签房源面积 */
	private double newRentArea;

	/** 续签房源面积 */
	private double oldRentArea;
}
