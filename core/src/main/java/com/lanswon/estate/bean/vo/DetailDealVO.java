package com.lanswon.estate.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 合同详细信息
 *
 * @author jaswine
 */
@Data
public class DetailDealVO {


	private Long id;
	/** 合同编号 */
	private String dealSerial;

	/** 出租人 */
	private String lessor;

	/** 承租人id */
	private long renterId;
	/** 承租人 */
	private String renter;


	/** 保证金 */
	private double deposit;
	/** 总月租金 */
	private double rentMonthly;
	/** 每平方米月单价(元/月/平方米) */
	private double rentMonthlyPerMeter;


	/** 支付方式Code*/
	private int payTypeCode;
	/** 支付方式 */
	private String payType;

	/** 租赁期限(月) */
	private int rentMonth;
	/** 免租月份 */
	private int freeRentMonth;

	/** 租赁类型code */
	private int isNewRentCode;
	/** 租赁类型 */
	private String isNewRent;

	/** 合同进行状态代码 */
	private int dealExistStatusCode;
	/** 合同进行状态*/
	private String dealExistStatus;

	/** 合同审核状态代码 */
	private int dealReviewStatusCode;
	/** 合同审核状态*/
	private String dealReviewStatus;

	/** 合同类型 */
	private int dealTypeCode;
	/** 合同类型 */
	private String dealType;

	/** 开始时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	/** 结束时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;



	/** 房源信息 */
	List<DetailHouseResourceVO> detailHouseResourceVO;



}
