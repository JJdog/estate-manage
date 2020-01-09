package com.lanswon.estate.bean.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;


import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * 地产导入
 *
 * @author jaswine
 */
@Data
@TableName(value = "deal")
public class PoiDealPO extends BasePojo {


	/** 合同编号 */
	@ExcelProperty(value = "合同编号")
	@NotNull(message = "合同号不可为空")
	private String dealSerial;

	/** 保证金 */
	@ExcelProperty(value = "保证金")
	@NotNull(message = "保证金不可为空")
	@TableField(exist = false)
	private Double deposit;

	/** 房源名称 */
	@ExcelProperty(value = "房源名称")
	@NotNull(message = "房源不可为空")
	@TableField(exist = false)
	private String resourceName;
	@TableField(exist = false)
	private Long resourceId;


	/** 承租人ID(乙方) */
	@ExcelProperty(value = "承租方")
	@TableField(exist = false)
	@NotNull(message = "承租方不可为空")
	private String renter;



	private Long fkRenterId;


	/** 合同类型 */
	@ExcelProperty(value = "合同类型")
	@TableField(exist = false)
	@NotNull(message = "合同类型不可为空")
	private String dealTypeValue;
	private Integer dealType;


	/** 支付方式 */
	@ExcelProperty(value = "支付方式")
	@TableField(exist = false)
	@NotNull(message = "支付方式不可为空")
	private String payTypeValue;
	private Integer payType;


	/** 是否新租(1.新租;2.续租) */
	@ExcelProperty(value = "新签/续签")
	@TableField(exist = false)
	@NotNull(message = "是否新租不可为空")
	private String isNewRentValue;
	private Integer isNewRent;


	/** 是否有优惠(1.有;2.没有) */
	@ExcelProperty(value = "是否有优惠")
	@TableField(exist = false)
	@NotNull(message = "是否有优惠不可为空")
	private String isHaveDiscountValue;
	private Integer isHaveDiscount;

	/** 优惠内容")*/
	@ExcelProperty(value = "优惠内容")
	private String discount;

	/** 开始时间 */
	@ExcelProperty(value = "开始时间")
	@NotNull(message = "合同开始时间不可为空")
	private Date startTime;

	/** 租的月数 */
	@ExcelProperty(value = "租赁时长(月)")
	@NotNull(message = "租赁时长不可为空")
	private Integer rentMonth;

	/** 结束时间 */
	private Date endTime;

	/** 免租时间(月为单位) */
	@ExcelProperty(value = "免租月数")
	@NotNull(message = "免租时间不可为空")
	private Integer freeRentMonth;

	/** 合同附加条款")*/
	@ExcelProperty(value = "附加条款")
	private  String extraInfo;


	/** 合同存在状态 */
	private Integer dealExistStatus;

	/** 合同审核状态 */
	private Integer dealReviewStatus;
}
