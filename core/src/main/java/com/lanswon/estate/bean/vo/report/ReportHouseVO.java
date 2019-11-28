package com.lanswon.estate.bean.vo.report;


import lombok.Data;

import java.util.List;

/**
 * 房源统计信息
 *
 * @author jaswine
 */
@Data
public class ReportHouseVO {



	/** 管理单位 */
	private String agency;

	/** 每月情况 */
	List<MonthReportHouseVO> monthReportHouseVOS;


}
