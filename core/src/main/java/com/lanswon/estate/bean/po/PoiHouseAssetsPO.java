package com.lanswon.estate.bean.po;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Excel(name = "房产名称")
	private String assetsName;


	/** 房产权id */
	@Excel(name = "房产证号")
	private String houseId;


	@Excel(name = "产权人名称")
	@TableField(exist = false)
	@NotNull(message = "产权人不可为空")
	private String owner;

	/** 房产所有权人 */
	private long fkOwnId;

	/** 地址 */
	@Excel(name = "地址")
	private String location;

	@Excel(name = "共有情况")
	@TableField(exist = false)
	@NotNull(message = "共有情况不可为空")
	private String houseShare;

	/** 共有情况 */
	private long fkHouseShareId;

	/** 管理单位名称 */
	@Excel(name = "管理单位")
	@TableField(exist = false)
	@NotNull(message = "管理单位不可为空")
	private String agencyName;

	/** 管理单位id */
	private long fkAgencyId;


	@Excel(name = "房产性质")
	@TableField(exist = false)
	private String houseNature;

	/** 房产性质 */
	private long fkHouseNature;

	@Excel(name = "规划用途")
	@TableField(exist = false)
	private String houseUsage;

	/** 规划用途 */
	private long fkHouseUsage;


	/** 总层数 */
	@Excel(name = "总层数")
	private long totalLevel;


	/** 建筑面积 */
	@Excel(name = "建筑面积")
	private double buildArea;


	/** 套内面积 */
	@Excel(name = "套内面积")
	private double realArea;


	/** 其他面积 */
	@Excel(name = "其他面积")
	private double otherArea;

	@Excel(name = "土地证号")
	@TableField(exist = false)
	private String landNo;

	/** 土地信息 */
	private long fkLandAssetsId;


	/** 登记时间 */
	@Excel(name = "登记时间")
	@JsonFormat(pattern = "yyyy年MM月dd日")
	private Date registerTime;


	/** 备注 */
	@Excel(name = "备注")
	private String remark;

}
