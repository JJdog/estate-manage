package com.lanswon.estate.bean.vo.report;


import lombok.Data;

/**
 * 租金报表VO
 *
 * @author jaswine
 */
@Data
public class ReportRentChargeVO {

	// 管理单位
	private String agency;

	// 月份
	private String month;

	// 应收款(总)
	private double mustRentCharge;

	// 实收款(总)
	private double realRentCharge;


	//private double mustRentCharge;

}
