package com.lanswon.estate.bean.vo.doc;


import lombok.Data;

import java.util.Date;

/**
 * 合同文件模版
 *
 * @author jaswine
 */
@Data
public class DealTemp {


	/** 合同编号 */
	private String dealSerial;
	/** 甲方 */
	private String lessor;
	/** 乙方 */
	private String renter;
	/** 地址 */
	private String location;
	/** 面积 */
	private Double area;
	/** 用途 */
	private String resourceUsage;
	/** 租赁时长 */
	private Integer rentYear;
	/** 租赁开始时间 */
	private String rentStart;
	private Date startTime;
	/** 租赁结束时间 */
	private String rentEnd;
	private Date endTime;
	/** 免租内容 */
	private String freeRent;
	/** 月租金 */
	private String monthMoney;
	/** 支付方式 */
	private String payType;
	/** 保证金 */
	private String deposit;
	/** 补充内容 */
	private String extraInfo;
}
