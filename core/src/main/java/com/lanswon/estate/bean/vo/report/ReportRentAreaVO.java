package com.lanswon.estate.bean.vo.report;


import lombok.Data;

/**
 * 租赁面积报表VO
 *
 * @author jaswine
 */
@Data
public class ReportRentAreaVO {

	/** 管理单位 */
	private String agency;
	/** 本月新租面积 */
	private Double newRentArea;
	/** 本月续租面积 */
	private Double oldRentArea;
	/** 本月小计 */
	private Double totalMonthRentArea;
	/** 年度小计*/
	private Double totalYearRentArea;

	/** 本月合同到期退租 */
	private Double expireReduceRentArea;
	/** 本月提前结束退租 */
	private Double aheadReduceRentArea;
	/** 本月退租小计 */
	private Double totalMonthReduceRentArea;
	/** 年度退租小计 */
	private Double totalYearReduceRentArea;

	/** 本月出售未办证 */
	private Double totalSellNoPro;
	/** 本月出售已办证 */
	private Double totalSellHasPro;







}
