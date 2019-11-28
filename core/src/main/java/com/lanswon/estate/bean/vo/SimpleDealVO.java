package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 合同分页信息
 *
 * @author jaswine
 */
@Data
public class SimpleDealVO {


	@ApiModelProperty(value = "id")
	private long id;

	@ApiModelProperty(value = "合同编号")
	private String dealSerial;

	@ApiModelProperty(value = "合同名称")
	private String dealName;

	/** 合同类型 */
	private String dealType;

	@ApiModelProperty(value = "房源名称")
	private String resourceName;

	@ApiModelProperty(value = "出租人")
	private String lessor;

	@ApiModelProperty(value = "承租人")
	private String renter;

	@ApiModelProperty(value = "承租人id")
	private long renterId;

	@ApiModelProperty(value = "房源id")
	private long resourceId;

	@ApiModelProperty(value = "地址")
	private String location;

	/** 合同存在状态 */
	private String dealExistStatus;

	private int dealReviewStatusCode;
	/** 合同审核状态 */
	private String dealReviewStatus;

	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date endTime;

}
