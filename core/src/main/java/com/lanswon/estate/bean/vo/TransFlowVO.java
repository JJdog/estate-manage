package com.lanswon.estate.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  交易流水VO
 *
 * @author jaswine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransFlowVO {


	/** 凭证号 */
	private String certNo;

	/** 收款缘由 */
	private String transReason;

	/** 收款时间 */
	private String moneyDate;

	/** 收款金额 */
	private  double money;

	/** 收款类型 */
	private String moneyType;

	/** 备注 */
	private String remark;
}
