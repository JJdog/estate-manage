package com.lanswon.estate.bean.vo;


import lombok.Data;


/**
 * 房源简单信息VO
 *
 * @author jaswine
 */
@Data
public class HouseResourcePageVO {

	/*==============房源===================*/

	private long id;

	/** 管理单位 */
	private String agency;

	/** 地址 */
	private String location;

	/** 房源面积 */
	private double resourceArea;

	/** 原来租金 */
	private double originRentCharge;

	/** 指导价 */
	private double guideRentCharge;

	/** 实际租金 */
	private double realRentCharge;

	/** 每平米月单价 */
	private double rentMoneyPerArea;

	private int rentCode;
	/** 出租状态 */
	private String rentStatus;

	private int sellCode;
	/** 出售状态 */
	private String sellStatus;

	/** 备注 */
	private String remark;


	/*==============房产===================*/
	/** 产证id */
	private long assetsId;

	/** 房产名称 */
	private String assetsName;

}
