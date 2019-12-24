package com.lanswon.estate.bean.pojo;


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

/**
 * 房源POJO
 *
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "house_resource")
@ApiModel(value = "房源POJO")
public class HouseResource extends BasePojo {


	@ApiModelProperty(value = "房产id",required = true)
	@NotNull(message = "房产不可为空")
	private long fkHouseAssetsId;

	@NotNull(message = "管理单位不可为空")
	@ApiModelProperty(value = "管理单位id",required = true)
	private long fkAgencyId;

	@NotNull(message = "房源类型不可为空")
	@ApiModelProperty(value = "房源类型id",required = true)
	private long fkResourceTypeId;

	@NotBlank(message = "园区信息不可为空")
	@ApiModelProperty(value = "园区",required = true)
	private String park;

	@ApiModelProperty(value = "楼栋号")
	private String buildNo;

	@ApiModelProperty(value = "楼层")
	private String buildLevel;

	@ApiModelProperty(value = "房间号")
	private String buildRoom;

	@ApiModelProperty(value = "房源面积", hidden = true)
	private Double resourceArea;

	@ApiModelProperty(value = "房源有证面积")
	private Double yzArea;

	@ApiModelProperty(value = "房源无证面积")
	private Double wzArea;

	@ApiModelProperty(value = "历史租金")
	private Double originRentCharge;

	@NotNull(message = "房源指导价不可为空")
	@ApiModelProperty(value = "指导价")
	private Double guideRentCharge;

	@ApiModelProperty(value = "出租状态")
	private int rentStatus;

	@ApiModelProperty(value = "出售状态")
	private int sellStatus;

	@ApiModelProperty(value = "备注")
	private String remark;


}
