package com.lanswon.estate.bean.cd;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "房源资产查询条件")
public class HouseReportCD {

	/** 管理单位id */
	@ApiModelProperty(value = "管理单位id",required = true)
	private long agencyId;

	/** 统计开始时间 */
	@ApiModelProperty(value = "统计开始时间(没有指定则当年第一月)",required = true)
	private Date reportStartTime;

	/** 统计结束时间 */
	@ApiModelProperty(value = "统计结束时间(没有指定则当年最后一月)",required = true)
	private Date reportEndTime;

}
