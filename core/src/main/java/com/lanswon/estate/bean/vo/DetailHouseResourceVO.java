package com.lanswon.estate.bean.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 房源详细信息
 *
 * @author jaswine
 */
@Data
public class DetailHouseResourceVO implements Serializable {

	/** id */
	private long id;

	/////房源信息////

	///** 房源名称 */
	//private String resourceName;

	/** 管理单位id */
	private long agencyId;

	/** 管理单位 */
	private String agency;

	/** 园区/楼宇*/
	private String park;

	/** 楼号 */
	private String buildNo;

	/** 楼层 */
	private int buildLevel;

	/** 房间号 */
	private String buildRoom;

	/** 房源总面积面积 */
	private double resourceArea;

	/** 房源总面积面积 */
	private double resourceYzArea;

	/** 房源总面积面积 */
	private double resourceWzArea;

	/** 原租金 */
	private double originRentCharge;

	/** 指导价 */
	private double guideRentCharge;

	/** 指导每平方米月租金 */
	private double guideRentMoneyPerArea;

	/** 实际租金 */
	private double realRentCharge;

	/** 每平方米月租金 */
	private double rentMoneyPerArea;

	/** 出租代码 */
	private int rentCode;

	/** 出租状态 */
	private String rentStatus;

	/** 出售代码 */
	private int sellCode;

	/** 出售状态 */
	private String sellStatus;

	/** 备注 */
	private String remark;

	/////房产信息///

	/** 房产id */
	private long assetsId;

	/** 房产名称 */
	private String assetsName;

	/** 房产所有人 */
	private String houseOwner;

	/** 房产总楼层 */
	private int totalLevel;

	/** 房产坐落 */
	private String houseLocation;

	/** 房产用途 */
	private String houseUsage;

	////土地信息///

	/** 土地使用权人 */
	private String landOwner;

	/** 土地坐落 */
	private String landLocation;

	/** 土地地号 */
	private String landNum;


}
