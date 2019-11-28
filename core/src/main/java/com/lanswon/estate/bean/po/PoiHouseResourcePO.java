package com.lanswon.estate.bean.po;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;



/**
 * 房源导入对象
 *
 * @author jaswine
 */
@Data
@TableName(value = "house_resource")
public class PoiHouseResourcePO extends BasePojo {



	@Excel(name = "管理单位")
	@TableField(exist = false)
	private String agencyName;

	@Excel(name = "园区/楼宇")
	private String park;

	@Excel(name = "楼号")
	private String buildNo;

	@Excel(name = "楼层")
	private Long buildLevel;

	@Excel(name = "房号")
	private String buildRoom;

	@Excel(name = "房产证号")
	@TableField(exist = false)
	private String houseSerial;

	@Excel(name = "房源总面积")
	private double resourceArea;

	@Excel(name = "原来租金")
	private double originRentCharge;

	@Excel(name = "指导租金")
	private double guideRentCharge;

	@Excel(name = "实际租金")
	private double realRentCharge;

	@Excel(name = "出租状态",replace = {"未出租_0","已出租_1"})
	private int rentStatus;

	@Excel(name = "出售状态",replace = {"未出售_0","已出售_1"})
	private int sellStatus;

	@Excel(name = "备注")
	private String remark;


	private Long fkHouseAssetsId;


	private Long fkAgencyId;




}
