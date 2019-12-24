package com.lanswon.estate.bean.vo.report.comp;


import lombok.Data;

/**
 * 实收欠款
 *
 * @author jaswine
 */
@Data
public class CompRealArrears {

	/** 本月收往年欠款 */
	private Double monthArrears;
	/** 本年收往年欠款 */
	private Double yearArrears;
}
