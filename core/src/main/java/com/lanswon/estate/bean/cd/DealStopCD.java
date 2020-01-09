package com.lanswon.estate.bean.cd;

import lombok.Data;

import java.util.Date;

/**
 * 终止合同cd
 *
 * @author jaswine
 */
@Data
public class DealStopCD {

	/** 合同ID */
	private Long id;

	/** 终止时间 */
	private Date date;
}
