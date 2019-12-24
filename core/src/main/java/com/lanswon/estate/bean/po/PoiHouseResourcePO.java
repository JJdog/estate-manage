package com.lanswon.estate.bean.po;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 房源导入对象
 *
 * @author jaswine
 */
@Data
@TableName(value = "house_resource")
public class PoiHouseResourcePO extends BasePojo {

	@ExcelProperty(value= "房产证号*")
	@TableField(exist = false)
	@NotNull(message = "房产证号不可为空")
	private String houseSerial;
	private Long fkHouseAssetsId;

	@ExcelProperty(value= "管理单位*")
	@TableField(exist = false)
	@NotNull(message = "管理单位不可为空")
	private String agencyName;
	private Long fkAgencyId;

	@ExcelProperty(value= "楼宇")
	private String park;

	@ExcelProperty(value= "楼号")
	private String buildNo;

	@ExcelProperty(value= "楼层")
	private String buildLevel;

	@ExcelProperty(value= "房号")
	private String buildRoom;

	/** 房源面积 */
	private Double resourceArea;

	@ExcelProperty(value= "有证面积*")
	@NotNull(message = "房产证号不可为空")
	private Double yzArea;

	@ExcelProperty(value= "无证面积*")
	@NotNull(message = "房产证号不可为空")
	private Double wzArea;

	@ExcelProperty(value= "历史租金(元/M²/月)")
	private Double originRentCharge;

	@ExcelProperty(value= "指导租金(元/M²/月)*")
	@NotNull(message = "指导租金不可为空")
	private Double guideRentCharge;

	@ExcelProperty(value= "资产类型*")
	@TableField(exist = false)
	//@NotNull(message = "资产类型不可为空")
	private String resourceType;
	private Long fkResourceTypeId;

	@ExcelProperty(value= "出租状态*")
	@TableField(exist = false)
	private String rentStatusValue;
	private Integer rentStatus;

	@ExcelProperty(value= "出售状态*")
	@TableField(exist = false)
	private String sellStatusValue;
	private Integer sellStatus;

	@ExcelProperty(value= "备注")
	private String remark;

}
