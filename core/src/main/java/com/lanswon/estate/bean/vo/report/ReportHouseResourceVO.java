package com.lanswon.estate.bean.vo.report;

import lombok.Data;

/**
 * 房源报表
 *
 * @author jaswine
 */
@Data
public class ReportHouseResourceVO {

	private String month;

	private int totalResourceNum;

	private double totalResourceArea;

	private double totalResourceYzArea;

	private double totalResourceWzArea;

	private int totalRentedResourceNum;

	private double totalRentedResourceArea;


}
