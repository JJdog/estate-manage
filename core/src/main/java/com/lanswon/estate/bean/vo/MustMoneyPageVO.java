package com.lanswon.estate.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MustMoneyPageVO {
	/** 房源地址 */
	private String houseLocation;

	/** 管理单位 */
	private String agency;

	/** 乙方 */
	private String renter;

	/** 应收款id */
	private Long payId;

	/** 应收时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date date;

	/** 应收缘由 */
	private String reason;

	/** 应收金额 */
	private double money;
}
