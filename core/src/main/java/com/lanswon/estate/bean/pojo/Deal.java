package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * 合同pojo
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "deal")
@ApiModel(value = "合同POJO",description = "数据库实体")
public class Deal extends BasePojo {

	@ApiModelProperty(value = "合同名称",hidden = true)
	private String dealName;

	@ApiModelProperty(value = "合同编号",hidden =true)
	private String dealSerial;

	@NotNull(message = "合同类型不可为空")
	@ApiModelProperty(value = "合同类型",required = true)
	private Long dealType;


	@NotNull(message = "房源不可为空")
	@ApiModelProperty(value = "房源id",required = true)
	@TableField(exist = false)
	private Set<Long> fkHouseResourceId;

	@NotNull(message = "租赁人不可为空")
	@ApiModelProperty(value = "承租人ID(乙方)",required = true)
	private long fkRenterId;

	@NotNull(message = "支付方式不可为空")
	@ApiModelProperty(value = "支付方式",allowableValues = "1,3,6,12",required = true)
	private int payType;

	@NotNull(message = "保证金不可为空")
	@ApiModelProperty(value = "保证金",required = true)
	private double deposit;

	@NotNull(message = "合同开始时间不可为空")
	@ApiModelProperty(value = "开始时间",required = true)
	private Date startTime;

	@NotNull(message = "租赁时长不可为空")
	@ApiModelProperty(value = "租的月数",required = true)
	private int rentMonth;

	@NotNull(message = "是否新租不可为空")
	@ApiModelProperty(value = "是否新租(1.新租;2.续租)",required = true)
	private int isNewRent;

	@ApiModelProperty(value = "结束时间",hidden = true)
	private Date endTime;

	@NotNull(message = "免租时间不可为空")
	@ApiModelProperty(value = "免租时间(月为单位)",required = true)
	private int freeRentMonth;

	@ApiModelProperty(value = "合同审核状态",hidden = true)
	private int dealReviewStatus;

	@ApiModelProperty(value = "合同存在状态",hidden = true)
	private int dealExistStatus;

}
