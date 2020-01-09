package com.lanswon.estate.bean.vo.dmenu;

import lombok.Data;

@Data
public class HouseResourceDmenu {

	private Long id;

	private String name;

	/** 房源面积 */
	private Double resourceArea;

	/** 指导价 */
	private Double guideRentCharge;
}
