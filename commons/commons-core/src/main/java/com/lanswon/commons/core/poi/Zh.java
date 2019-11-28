package com.lanswon.commons.core.poi;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

@ExcelTarget(value = "Zh")
public class Zh implements Serializable {

	@Excel(name = "日期",isImportField = "true_st",importFormat = "yyyy.MM.dd",orderNum = "0")
	private Date qi;

	@Excel(name = "健走（步数）",isImportField = "true_st ",orderNum = "1")
	private int walk;

	@Excel(name = "骑行（公里）",isImportField = "true_st ",orderNum = "2")
	private double bike;

	public Date getQi() {
		return qi;
	}

	public void setQi(Date qi) {
		this.qi = qi;
	}

	public int getWalk() {
		return walk;
	}

	public void setWalk(int walk) {
		this.walk = walk;
	}

	public double getBike() {
		return bike;
	}

	public void setBike(double bike) {
		this.bike = bike;
	}
}
