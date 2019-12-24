package com.lanswon.estate.bean.vo.report.comp;

import lombok.Data;

/**
 * 组件-应收
 *
 * @author jaswine
 */
@Data
public class CompMustMoney {

	/** 本月应收租金 */
	private Double monthMustMoney;
	/** 本年应收租金 */
	private Double yearMustMoney;

}
