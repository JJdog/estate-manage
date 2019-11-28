package com.lanswon.estate.bean.cd;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 报表查询条件
 *
 * @author jaswine
 */
@Data
public class ReportCD {

	private Date startDate;

	private Date endStart;

	private List<Long> agency;

}
