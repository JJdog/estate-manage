package com.lanswon.estate.bean.vo.report;

import lombok.Data;

/**
 * 资产大统计
 *
 * @author jaswine
 */
@Data
public class ReportHouseVO {

	/** 管理单位 */
	private Long aid;
	private String agency;

	/** 房产名称 */
	private Long hid;
	private String houseName;

	/** 房产有证面积 */
	private Double houseYzArea;
	/** 房产无证面积 */
	private Double houseWzArea;
	/** 房产总面积 */
	private Double houseTotalArea;

	/** 土产有证面积 */
	private Double landYzArea;
	/** 土产无证面积 */
	private Double landWzArea;
	/** 土产总面积 */
	private Double landTotalArea;

	/** 正常出租 */
	private Double normalRentArea;
	/** 长期无收益 */
	private Double longRentArea;
	/** 公共设施 */
	private Double publicRentArea;
	/** 车库 */
	private Double garageRentArea;
	/** 完全自用 */
	private Double selfRentArea;
	/** 小计 */
	private Double totalRentArea;


	/** 闲置 */
	private Double noRentArea;
	/** 出售未过户 */
	private Double selledNoProArea;

}
