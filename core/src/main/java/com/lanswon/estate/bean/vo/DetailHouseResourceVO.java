package com.lanswon.estate.bean.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lanswon.commons.core.json.CustomDoubleSerialize;
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
	private Long id;

	/** 管理单位 */
	private Long agencyId;
	private String agency;

	/** 地址 */
	private String location;

	/** 楼宇*/
	private String park;
	private Long parkId;
	/** 楼号 */
	private String buildNo;
	/** 楼层 */
	private String buildLevel;
	/** 房间号 */
	private String buildRoom;

	/** 合同房源地址 */
	private String realLocation;

	/** 房源总面积面积 */
	private Double resourceArea;
	/** 有证面积 */
	private Double resourceYzArea;
	/** 无证面积 */
	private Double resourceWzArea;

	/** 历史租金 */
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double originRentCharge;
	/** 指导价 */
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double guideRentCharge;
	/** 合同价 */
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double realRentCharge;

	/** 房源用途 */
	private Integer resourceUsageCode;
	private String resourceUsage;

	/** 出租状态 */
	private Integer rentCode;
	private String rentStatus;

	/** 出售状态 */
	private Integer sellCode;
	private String sellStatus;

	/** 备注 */
	private String remark;

	/////房产信息///

	/** 房产id */
	private Long assetsId;

	/** 房产名称 */
	private String assetsName;

	/** 房产所有人 */
	private String houseOwner;

	/** 房产总楼层 */
	private Integer totalLevel;

	/** 房产坐落 */
	private String houseLocation;

	/** 房产用途 */
	private String houseUsage;

	////土地信息///

	/** 土地证号 */
	private String landNo;

	/** 土地使用权人 */
	private String landOwner;

	/** 土地坐落 */
	private String landLocation;

	/** 土地地号 */
	private String landNum;


}
