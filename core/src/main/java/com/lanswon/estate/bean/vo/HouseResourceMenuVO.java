package com.lanswon.estate.bean.vo;

import lombok.Data;

/**
 * 房源下拉菜单
 *
 * @author jaswine
 */
@Data
public class HouseResourceMenuVO {

	/** id */
	private Long id;

	/** 房源名称 */
	private String resourceName;

	/** 房源面积 */
	private Double resourceArea;

	/** 指导价 */
	private Double guideRentCharge;

}
