package com.lanswon.estate.bean.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 地产导入
 *
 * @author jaswine
 */
@Data
@TableName(value = "land_assets")
public class PoiLandAssetsPO extends BasePojo {

	/** 房产名称 */
	@Excel(name = "土地证号*")
	@NotNull(message = "土地证号不可为空")
	@NotBlank(message = "土地证号不可为空")
	private String landNo;

	@Excel(name = "产权人名称*")
	@TableField(exist = false)
	@NotNull(message = "产权人不可为空")
	@NotBlank(message = "产权人不可为空")
	private String owner;
	/** 房产所有权人 */
	private Long fkOwnId;

	@Excel(name = "管理单位*")
	@TableField(exist = false)
	@NotNull(message = "管理单位不可为空")
	private String agencyName;
	/** 管理单位id */
	private Long fkAgencyId;

	/** 座落 */
	@Excel(name = "座落")
	private String assetsLocation;

	/** 地号 */
	@Excel(name = "地号")
	private String landNum;

	/** 图号 */
	@Excel(name = "图号")
	private String picNum;

	@Excel(name = "地类(用途)")
	@TableField(exist = false)
	private String landUsage;
	private Long fkLandUsageId;

	@Excel(name = "使用权类型")
	@TableField(exist = false)
	private String landNature;
	private Long fkLandNatureId;

	@Excel(name = "有证面积*")
	@NotNull(message = "有证面积不可为空")
	private  Double yzArea;

	@Excel(name = "无证面积*")
	private  Double wzArea;

	@Excel(name = "使用权面积")
	private Double assetsArea;

	@Excel(name = "独有面积")
	private Double selfArea;

	@Excel(name = "分摊面积")
	private Double shareArea;

	@Excel(name = "终止日期")
	private Date endTime;

	/** 备注 */
	@Excel(name = "备注")
	private String remark;
}
