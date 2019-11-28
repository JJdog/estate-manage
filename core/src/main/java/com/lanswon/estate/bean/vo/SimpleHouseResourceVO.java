package com.lanswon.estate.bean.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 房源简单信息VO
 *
 * @author jaswine
 */
@Data
public class SimpleHouseResourceVO {

	private long id;

	private String resourceName;

	/** 产证id */
	private long assetsId;

	/** 产权名称 */
	private String assetsName;

	/** 管理单位 */
	private String manageUnit;

	/** 地址 */
	private String location;

	/** 房源面积 */
	private double resourceArea;

	@ApiModelProperty(value = "原来租金")
	private double originRentCharge;

	@NotNull(message = "房源指导价不可为空")
	@ApiModelProperty(value = "指导价")
	private double guideRentCharge;

	@ApiModelProperty(value = "实际租金")
	private double realRentCharge;

	private int rentCode;
	/** 出租状态 */
	private String rentStatus;

	private int sellCode;
	/** 出售状态 */
	private String sellStatus;

	/** 备注 */
	private String remark;


}
