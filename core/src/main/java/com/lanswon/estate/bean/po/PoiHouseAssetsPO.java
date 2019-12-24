package com.lanswon.estate.bean.po;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 房产导入信息
 *
 * @author jaswine
 */
@Data
@TableName(value = "house_assets")
public class PoiHouseAssetsPO extends BasePojo {

	/** 房产名称 */
	@ExcelProperty(value= "房产名称*")
	@TableField(exist = false)
	private String assetsName;
	private Long fkHouseNameId;


	/** 房产权id */
	@ExcelProperty(value= "房产证号*")
	private String houseId;


	@ExcelProperty(value= "产权人名称*")
	@TableField(exist = false)
	@NotNull(message = "产权人不可为空")
	private String owner;
	/** 房产所有权人 */
	private Long fkOwnId;

	/** 地址 */
	@ExcelProperty(value= "地址")
	private String location;

	@ExcelProperty(value= "共有情况")
	@TableField(exist = false)
	@NotNull(message = "共有情况不可为空")
	private String houseShare;
	/** 共有情况 */
	private Long fkHouseShareId;

	/** 管理单位名称 */
	@ExcelProperty(value= "管理单位*")
	@TableField(exist = false)
	@NotNull(message = "管理单位不可为空")
	private String agencyName;
	/** 管理单位id */
	private Long fkAgencyId;


	@ExcelProperty(value= "房产性质")
	@TableField(exist = false)
	private String houseNature;

	/** 房产性质 */
	private Long fkHouseNature;

	@ExcelProperty(value= "规划用途")
	@TableField(exist = false)
	private String houseUsage;
	/** 规划用途 */
	private Long fkHouseUsage;


	/** 总层数 */
	@ExcelProperty(value= "总层数")
	private Long totalLevel;

	@ExcelProperty(value= "有证面积*")
	private Double yzArea;

	@ExcelProperty(value= "无证面积*")
	private Double wzArea;

	/** 建筑面积 */
	@ExcelProperty(value= "建筑面积")
	private double buildArea;


	/** 套内面积 */
	@ExcelProperty(value= "套内面积")
	private double realArea;


	/** 其他面积 */
	@ExcelProperty(value= "其他面积")
	private double otherArea;

	@ExcelProperty(value= "土地证号*")
	@TableField(exist = false)
	private String landNo;
	/** 土地信息 */
	private Long fkLandAssetsId;


	/** 登记时间 */
	@ExcelProperty(value= "登记时间")
	private Date registerTime;


	/** 备注 */
	@ExcelProperty(value= "备注")
	private String remark;

}
