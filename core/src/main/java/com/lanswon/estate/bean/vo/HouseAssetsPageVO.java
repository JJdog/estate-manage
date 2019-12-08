package com.lanswon.estate.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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

	/** 房产证号 */
	private String houseId;

	/** 名称id */
	private Long houseNameId;
	/** 房屋资产名称 */
	private String houseName;

	/** 房屋所有人id */
	private long fkOwnerId;

	/** 地址 */
	private String location;

	/** 房屋所有人 */
	private String owner;

	/** 共有情况id */
	private long houseShareId;

	/** 共有情况 */
	private String houseShare;

	/** 管理单位id */
	private String agencyId;

	/** 管理单位 */
	private String agency;

	/** 房屋性质id */
	private long houseNatureId;

	/** 房屋性质 */
	private String houseNature;

	/** 计划用途id */
	private long houseUsageId;

	/** 计划用途 */
	private String houseUsage;

	/** 总层数 */
	private long totalLevel;

	/** 建筑面积 */
	private double buildArea;

	/** 套内面积 */
	private double realArea;

	/** 其他面积 */
	private double otherArea;

	private double yzArea;

	private double wzArea;

	/** 土地资产id */
	private long landAssetsId;

	/** 地号 */
	private String landNum;


	// todo
	/** 土地使用年限 */
	private int landUseYear;

	/** 备注 */
	private String remark;

	/** 登记时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date registerTime;

}
