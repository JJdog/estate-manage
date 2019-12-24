package com.lanswon.estate.bean.vo.report;


import com.lanswon.estate.bean.vo.report.comp.CompMustMoney;
import com.lanswon.estate.bean.vo.report.comp.CompRealArrears;
import com.lanswon.estate.bean.vo.report.comp.CompRealMoney;
import lombok.Data;

/**
 * 租赁面积报表VO
 *
 * @author jaswine
 */
@Data
public class ReportRentMoneyVO {

	/** 管理单位 */
	private String agency;
	private Long aid;

	/** 本月应收租金 */
	private Double monthMustMoney;
	/** 本年应收租金 */
	private Double yearMustMoney;

	/** 本月实收租金 */
	private Double monthRealMoney;
	/** 本年实收租金 */
	private Double yearRealMoney;

	/** 到账率 */
	private String rate;

	/** 本月收往年欠款 */
	private Double monthArrears;
	/** 本年收往年欠款 */
	private Double yearArrears;


}
