package com.lanswon.estate.bean.vo;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(value = "月份")
	private String rentMonth;


	@ApiModelProperty(value = "应收款")
	private int mustCharge;


	@ApiModelProperty(value = "实收款")
	private int actualCharge;


	@ApiModelProperty(value = "欠款")
	private int arrears;

	/** 租金状态 */
	private String rentStatus;

}
