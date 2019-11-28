package com.lanswon.estate.bean.po;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanswon.commons.web.pojo.BasePojo;
import lombok.Data;

import java.util.Date;

/**
 * 交易流水对象
 */
@Data
@TableName(value = "real_money")
@ExcelTarget(value = "PoiTransFlow")
public class PoiTransFlow extends BasePojo {

	@Excel(name = "房源坐落",orderNum = "1")
	@TableField(exist = false)
	private String houseLocation;

	@Excel(name = "管理单位",orderNum = "2")
	@TableField(exist = false)
	private String agencyName;

	@Excel(name = "乙方",orderNum = "3")
	@TableField(exist = false)
	private String renter;

	@Excel(name = "对应款项id",orderNum = "4")
	private Long fkMustMoneyId;

	@Excel(name = "收款代码",orderNum = "5")
	private String mustMoneyNum;

	@Excel(name = "收款时间",format = "yyyy-MM-dd",orderNum = "6")
	private Date moneyDate;

	@Excel(name = "收款缘由",orderNum = "7")
	private String moneyType;

	@Excel(name = "应收收款金额",orderNum = "8")
	private double mustMoney;

	@Excel(name = "实收金额",orderNum = "9")
	private double realMoney;



}
