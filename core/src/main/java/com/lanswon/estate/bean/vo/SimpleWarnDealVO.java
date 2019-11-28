package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 合同到期预警信息
 *
 * @author jaswine
 */
@Data
public class SimpleWarnDealVO {

	/** id */
	private long id;

	/** 合同编号 */
	private String dealSerial;

	/** 合同名称 */
	private String dealName;

	/** 房产名称 */
	private String resourceName;

	/** 出租人 */
	private String lessor;

	/** 承租人 */
	private String renter;

	/** 承租人id */
	private long renterId;

	/** 房源id */
	private long resourceId;

	/** 地址 */
	private String location;

	/** 剩余天数 */
	private int lastDay;

	/** 开始时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	/** 结束时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;



}
