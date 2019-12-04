package com.lanswon.estate.bean.vo.report;


import lombok.Data;

import java.util.List;

/**
 * 房源统计信息
 *
 * @author jaswine
 */
@Data
public class ReportHouseVO {



	/** 管理单位 */
	private String agency;

	/** 新签 */
	private double newRentArea;

	/** 续签 */
	private double oldRentArea;

	/** 月总面积 */
	private double totalMonthRentArea;

	/** 年总面积 */
	private double totalYearRentArea;

	/** 到期退租 */
	private double expireReduceRentArea;

	/** 提前结束退租 */
	private double aheadReduceRentArea;

	/** 月总面积 */
	private double totalMonthReduceRentArea;

	/** 年总面积 */
	private double totalYearReduceRentArea;


}
