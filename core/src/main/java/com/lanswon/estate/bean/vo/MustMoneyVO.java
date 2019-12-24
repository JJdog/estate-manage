package com.lanswon.estate.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 应收资金
 *
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MustMoneyVO {

	/** 应收缘由 */
	private String mustReason;

	/** 应收时间 */
	private String mustDate;

	/** 应收金额 */
	private Double money;

	/** 应收状态(正常/不正常) */
	private String payStatus;

	/** 应收类型 */
	private String moneyType;
}
