package com.lanswon.estate.bean.vo;


import lombok.Data;

import java.util.List;

/**
 * POI结果对象
 *
 * @author jaswine
 */
@Data
public class PoiResultVO {

	/** 导入名称 */
	private String poiName;

	/** 总条数 */
	private int totalRow;

	/** 成功数 */
	private int successRow;

	/** 失败数 */
	private int errorRow;

	/** 失败行 */
	private List<String> errorRows;
}
