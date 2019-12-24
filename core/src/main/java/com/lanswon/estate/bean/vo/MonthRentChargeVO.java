package com.lanswon.estate.bean.vo;

import lombok.Data;

/**
 * 月份租金情况
 *
 * @author jaswine
 */
@Data
public class MonthRentChargeVO {

	/** id */
	private long id;

	/** 月份 */
	private String rentMonth;

	/** 应收款 */
	private int mustCharge;

	/** 实收款 */
	private int actualCharge;

	/** 欠款 */
	private int arrears;

	/** 租金状态 */
	private String rentStatus;

}
