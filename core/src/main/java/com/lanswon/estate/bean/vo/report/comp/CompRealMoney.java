package com.lanswon.estate.bean.vo.report.comp;

import lombok.Data;

/**
 * 组件-实收资金
 *
 * @author jaswine
 */
@Data
public class CompRealMoney {

	/** 本月实收租金 */
	private Double monthRealMoney;
	/** 本年实收租金 */
	private Double yearRealMoney;

}
