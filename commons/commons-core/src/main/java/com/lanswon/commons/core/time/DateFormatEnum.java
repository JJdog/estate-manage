package com.lanswon.commons.core.time;

/**
 * 日期/时间格式枚举类
 * @author Jaswine
 */
public enum DateFormatEnum {

	/**
	 * 2019
	 */
	YYYY("yyyy"),
	/**
	 * 10-01
	 */
	MM_DD("MM-dd"),
	/**
	 * 12:00
	 */
	HH_MM("HH:mm"),
	/**
	 * 12:00:00
	 */
	HH_MM_SS("HH:mm:ss"),
	/**
	 * 2018-10
	 */
	YYYY_MM("yyyy-MM"),
	/**
	 * 2018-10-01
	 */
	YYYY_MM_DD("yyyy-MM-dd"),
	/**
	 * 10-01 12:00
	 */
	MM_DD_HH_MM("MM-dd HH:mm"),
	/**
	 * 10-01 12:00:00
	 */
	MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
	/**
	 * 2018-10-01 12:00
	 */
	YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
	/**
	 * 2018-10-01 12:00:00
	 */
	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
	/**
	 * 2018-10-01 12:00:00:0000
	 */
	YYYY_MM_DD_HH_MM_SS_MS("yyyy-MM-dd HH:mm:ss:ms"),

	/**
	 * 10/01
	 */
	MM_DD_EN("MM/dd"),
	/**
	 * 2018/10
	 */
	YYYY_MM_EN("yyyy/MM"),
	/**
	 * 2018/10/01
	 */
	YYYY_MM_DD_EN("yyyy/MM/dd"),
	/**
	 * 10/01 12:00
	 */
	MM_DD_HH_MM_EN("MM/dd HH:mm"),
	/**
	 * 10/01 12:00:00
	 */
	MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
	/**
	 * 2018/10/01 12:00
	 */
	YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
	/**
	 * 2018/10/01 12:00:00
	 */
	YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
	/**
	 * 2018/10/01 12:00:00:0000
	 */
	YYYY_MM_DD_HH_MM_SS_MS_EN("yyyy/MM/dd HH:mm:ss:ms"),


	/**
	 * 10月01日
	 */
	MM_DD_CN("MM月dd日"),
	/**
	 * 2018年10月
	 */
	YYYY_MM_CN("yyyy年MM月"),
	/**
	 * 2018年10月01日
	 */
	YYYY_MM_DD_CN("yyyy年MM月dd日"),
	/**
	 * 10月01日 12:00
	 */
	MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
	/**
	 * 10月01日 12:00:00
	 */
	MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),
	/**
	 * 2018年10月01日 12:00
	 */
	YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
	/**
	 * 2018年10月01日 12:00:00
	 */
	YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),
	/**
	 * 2018年10月01日 12:00:00:0000
	 */
	YYYY_MM_DD_HH_MM_SS_MS_CN("yyyy年MM月dd日 HH:mm:ss:ms"),


	/**
	 * 1001
	 */
	MM_DD_LINK("MMdd"),
	/**
	 * 201810
	 */
	YYYY_MM_LINK("yyyyMM"),
	/**
	 * 20181001
	 */
	YYYY_MM_DD_LINK("yyyyMMdd"),
	/**
	 * 2018121314
	 */
	YYYY_MM_DD_HH_LINK("yyyyMMddHH"),
	/**
	 * 10011200
	 */
	MM_DD_HH_MM_LINK("MMddHHmm"),
	/**
	 * 1001120000
	 */
	MM_DD_HH_MM_SS_LINK("MMddHHmmss"),
	/**
	 * 201810011200
	 */
	YYYY_MM_DD_HH_MM_LINK("yyyyMMddHHmm"),
	/**
	 * 20181001120000
	 */
	YYYY_MM_DD_HH_MM_SS_LINK("yyyyMMddHHmmss"),

	/**
	 * 201810011200000000
	 */
	YYYY_MM_DD_HH_MM_SS_MS_LINK("yyyyMMddHHmmssms");



	private String value;

	DateFormatEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
