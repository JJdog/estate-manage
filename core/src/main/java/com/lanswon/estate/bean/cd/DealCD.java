package com.lanswon.estate.bean.cd;

import lombok.Data;

import java.util.Date;

/**
 * 合同查询条件
 *
 * @author jaswine
 */
@Data
public class DealCD extends BaseCd {


	/** 合同名称 */
	private String dealName;

	/** 出租人id */
	private long lessorId;

	/** 承租人id */
	private long renterId;

	/** 合同存在状态代码 */
	private int dealExistStatusCode;

	/** 合同审核状态代码 */
	private int dealReviewStatusCode;

	/** 合同类型 */
	private int dealTypeCode;

	/** 房源用处 */
	private long houseUsageId;

	/** 管理单位 */
	private long agencyId;

	/** 支付方式Code*/
	private int payTypeCode;


	/** 合同开始时间 */
	private Date startTime;
	/** 合同结束时间 */
	private Date endTime;


	/** min面积 */
	private double minResourceArea;
	/** max面积 */
	private double maxResourceArea;

	/** min租赁期限(月) */
	private int minRentMonth;
	/** max租赁期限(月) */
	private int maxRentMonth;

	/** min原价 元/(M²·月) */
	private double minOriginRentCharge;
	/** max原价 元/(M²·月) */
	private double maxOriginRentCharge;

	/** min指导价  元/(M²·月)*/
	private double minGuideRentCharge;
	/** max指导价  元/(M²·月)*/
	private double maxGuideRentCharge;

	/** min实际价 元/(M²·月) */
	private double minRealRentCharge;
	/** max实际价 元/(M²·月) */
	private double maxRealRentCharge;

}
