package com.lanswon.estate.bean.vo.page;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.estate.bean.vo.DepositReturnVO;
import com.lanswon.estate.bean.vo.DetailHouseResourceVO;
import com.lanswon.estate.bean.vo.MustMoneyVO;
import com.lanswon.estate.bean.vo.TransFlowVO;
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


	private Long id;

	/** 合同编号 */
	private String dealSerial;

	/** 合同类型 */
	private Integer dealTypeCode;
	private String dealType;

	/** 支付方式 */
	private Integer payTypeCode;
	private String payType;

	/** 地址 */
	private String location;

	/** 承租人 */
	private Long renterId;
	private String renter;

	/** 出租人 */
	private Long lessorId;
	private String lessor;

	/** 租赁时长 */
	private Integer rentMonth;

	/** 免租时长 */
	private Integer freeRentMonth;
	/** 免租到期时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date freeEndTime;

	/** 是否新租 */
	private Integer isNewRentCode;
	private String isNewRent;


	/** 是否有优惠 */
	private Integer isHaveDiscountCode;
	private String isHaveDiscount;

	/** 优惠内容 */
	private String discount;

	/** 合同存在状态 */
	private Integer dealExistStatusCode;
	private String dealExistStatus;

	/** 合同审核状态 */
	private Integer dealReviewStatusCode;
	private String dealReviewStatus;

	/** 开始时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	/** 结束时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;

	/** 附加内容 */
	private String extraInfo;


	/** 房源信息 */
	List<DetailHouseResourceVO> houseResourceDetail;

	/** 应收资金 */
	List<MustMoneyVO> mustMoney;

	/** 收款明细 */
	List<TransFlowVO> transFlows;

	/** 保证金退还 */
	List<DepositReturnVO> depositReturns;


}
