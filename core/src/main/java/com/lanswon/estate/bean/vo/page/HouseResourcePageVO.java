package com.lanswon.estate.bean.vo.page;


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
	private Long fkAgencyId;
	private String agency;

	/** 地址 */
	private String location;

	/** 楼宇 */
	private String park;

	/** 楼号 */
	private String buildNo;

	/** 楼层 */
	private Integer buildLevel;

	/** 房号 */
	private String buildRoom;

	/** 房源面积 */
	private Double resourceArea;

	/** 有证面积 */
	private Double yzArea;

	/** 无证面积 */
	private Double wzArea;

	/** 历史租金 */
	private double originRentCharge;

	/** 指导价 */
	private double guideRentCharge;

	/** 房源类型 */
	private Long fkResourceTypeId;
	private String resourceType;

	/** 出租状态 */
	private int rentCode;
	private String rentStatus;

	/** 出售状态 */
	private int sellCode;
	private String sellStatus;

	/** 备注 */
	private String remark;


	/*==============房产===================*/
	/** 产证id */
	private Long assetsId;

	/** 房产名称 */
	private String assetsName;

	/** 房产证号 */
	private String houseNo;

	/** 房产总楼层 */
	private Integer houseTotalLevel;

	/** 房屋产权人 */
	private Long houseOwnerId;
	private String houseOwner;

	/*==============土产===================*/

	/** 地号 */
	private String landNum;

	/** 土地证号 */
	private String landNo;

	/** 房屋产权人 */
	private Long landOwnerId;
	private String landOwner;



}
