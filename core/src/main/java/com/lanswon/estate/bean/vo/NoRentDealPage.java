package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 合同分页信息
 *
 * @author jaswine
 */
@Data
public class NoRentDealPage {


	/*=========合同==================*/

	private long id;

	/** 合同编号 */
	private String dealSerial;

	/** 出租人 */
	private String lessor;

	/** 承租人 */
	private long renterId;
	private String renter;

	/** 地址 */
	private String location;

	/** 租赁时长 */
	private int rentMonth;

	/** 免租时长 */
	private int freeRentMonth;

	/** 是否新租 */
	private int isNewRentCode;
	private String isNewRent;

	/** 合同类型 */
	private int dealTypeCode;
	private String dealType;

	/** 支付方式 */
	private int payTypeCode;
	private String payType;

	/** 合同存在状态 */
	private int dealExistStatusCode;
	private String dealExistStatus;

	/** 合同审核状态 */
	private int dealReviewStatusCode;
	private String dealReviewStatus;

	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;


	/** 房源名称 */
	private String resourceName;


}
