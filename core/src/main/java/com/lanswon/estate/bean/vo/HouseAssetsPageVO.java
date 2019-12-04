package com.lanswon.estate.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.commons.web.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 房屋产权信息VO
 *
 * @author jaswine
 */
@Data
@ApiModel(value = "房屋产权信息VO")
public class HouseAssetsPageVO {

	private Long id;

	@ApiModelProperty(value = "房产证号")
	private String houseId;

	@ApiModelProperty(value = "房屋资产名称")
	private String assetsName;

	@ApiModelProperty(value = "房屋所有人id")
	private long fkOwnerId;

	@ApiModelProperty(value = "地址")
	private String location;

	@ApiModelProperty(value = "房屋所有人")
	private String owner;

	@ApiModelProperty(value = "共有情况id")
	private long houseShareId;

	@ApiModelProperty(value = "共有情况")
	private String houseShare;

	@ApiModelProperty(value = "管理单位id")
	private String agencyId;

	@ApiModelProperty(value = "管理单位")
	private String agency;

	@ApiModelProperty(value = "房屋性质id")
	private long houseNatureId;

	@ApiModelProperty(value = "房屋性质")
	private String houseNature;

	@ApiModelProperty(value = "计划用途id")
	private long houseUsageId;

	@ApiModelProperty(value = "计划用途")
	private String houseUsage;

	@ApiModelProperty(value = "总层数")
	private long totalLevel;

	@ApiModelProperty(value = "建筑面积")
	private double buildArea;

	@ApiModelProperty(value = "套内面积")
	private double realArea;

	@ApiModelProperty(value = "其他面积")
	private double otherArea;

	private double yzArea;

	private double wzArea;

	@ApiModelProperty(value = "土地资产id")
	private long landAssetsId;

	@ApiModelProperty(value = "地号")
	private String landNum;


	// todo
	@ApiModelProperty(value = "土地使用年限")
	private int landUseYear;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "登记时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date registerTime;

}
