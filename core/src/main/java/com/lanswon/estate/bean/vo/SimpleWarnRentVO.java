package com.lanswon.estate.bean.vo;


import lombok.Data;


/**
 * 合同到期预警信息
 *
 * @author jaswine
 */
@Data
public class SimpleWarnRentVO {

	/** 合同编号 */
	private String dealSerial;

	/** 合同名称 */
	private String dealName;

	/** 房源名称 */
	private String resourceName;

	/** 租户 */
	private String renter;

	/** 缴费金额 */
	private double mustCharge;

	/** 月份 */
	private String rentMonth;

	/** 剩余天数 */
	private int lastDay;



}
