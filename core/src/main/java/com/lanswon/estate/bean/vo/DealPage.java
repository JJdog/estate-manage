package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.estate.bean.pojo.RentCharge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
public class DealPage {


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

	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;




	/** 房产信息 */
	List<DetailHouseResourceVO> houseResourceDetail;

	/** 应收资金 */
	List<MustMoneyVO> mustMoney;

	/** 收款明细 */
	List<TransFlowVO> transFlows;


}
