package com.lanswon.estate.bean.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lanswon.commons.core.json.CustomDoubleSerialize;
import lombok.Data;

import java.util.Date;

/**
 * 保证金退还VO
 *
 * @author jaswine
 */
@Data
public class DepositReturnVO {


	/** 合同id */
	private Long fkDealId;

	/** 退还的钱 */
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double money;

	/** 退还时间 */
	private Date returnDate;

}
