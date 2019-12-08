package com.lanswon.estate.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 房产信息
 * @author jaswine
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "house_assets")
@ApiModel(value = "新增房产信息")
public class HouseAssets extends BasePojo {


	@ApiModelProperty(value = " 房产名称 ",required = true)
	@NotNull(message = "房产名称不可为空")
	private long fkHouseNameId;


	@ApiModelProperty(value = " 房产证id ",required = true)
	@NotNull(message = "房产证id不可为空")
	@NotBlank(message = "房产证id不可为空")
	private String houseId;


	@ApiModelProperty(value = " 房产所有权人 ",required = true)
	@NotNull(message = "房产证id不可为空")
	private long fkOwnId;


	@ApiModelProperty(value = " 地址 ")
	private String location;


	@ApiModelProperty(value = " 共有情况 ")
	private long fkHouseShareId;


	@ApiModelProperty(value = " 管理单位 ")
	private long fkAgencyId;


	@ApiModelProperty(value = " 房产性质 ")
	private long fkHouseNature;


	@ApiModelProperty(value = " 规划用途 ")
	private long fkHouseUsage;


	@ApiModelProperty(value = " 总层数 ")
	private long totalLevel;


	@ApiModelProperty(value = " 建筑面积 ")
	private double buildArea;


	@ApiModelProperty(value = " 套内面积 ")
	private double realArea;


	@ApiModelProperty(value = " 其他面积 ")
	private double otherArea;

	@ApiModelProperty(value = "有证面积",required = true)
	@NotNull(message = "有证面积不可为空")
	@Min(value = 1,message = "有证面积不可为空")
	private Double yzArea;

	@ApiModelProperty(value = "无证面积",required = true)
	@NotNull(message = "无证面积不可为空")
	private double wzArea;


	@ApiModelProperty(value = "土地信息")
	private long fkLandAssetsId;


	@ApiModelProperty(value = " 登记时间 ")
	private Date registerTime;


	@ApiModelProperty(value = " 备注 ")
	private String remark;


}