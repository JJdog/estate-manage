package com.lanswon.commons.core.serial;

public enum SerialEnum {

	/**
	 * uuid
	 */
	UUID,
	/**
	 * 时间序列(年)
	 * 2019
	 */
	TIME_YEAR,
	/**
	 * 时间序列(年到月)
	 * 201912
	 */
	TIME_YEAR_MONTH,
	/**
	 * 时间序列(年到天)
	 * 20191230
	 */
	TIME_YEAR_DAY,
	/**
	 * 时间序列(年到小时)
	 * 20191230164356
	 */
	TIME_YEAR_HOUR,
	/**
	 * 时间序列(年到分钟)
	 * 20191230164356
	 */
	TIME_YEAR_MINUTE,
	/**
	 * 时间序列(年到秒)
	 * 20191230164356
	 */
	TIME_YEAR_SECOND,

	/**
	 * 前缀+时间序列(年)
	 * 2019
	 */
	PREFIX_TIME_YEAR,
	/**
	 * 前缀+时间序列(年到月)
	 * Q_201912
	 */
	PREFIX_TIME_YEAR_MONTH,
	/**
	 * 前缀+时间序列(年到天)
	 * Q_20191230
	 */
	PREFIX_TIME_YEAR_DAY,
	/**
	 * 前缀+时间序列(年到小时)
	 * Q_0191230164356
	 */
	PREFIX_TIME_YEAR_HOUR,
	/**
	 * 前缀+时间序列(年到分钟)
	 * Q_20191230164356
	 */
	PREFIX_TIME_YEAR_MINUTE,
	/**
	 * 前缀+时间序列(年到秒)
	 * Q_20191230164356
	 */
	PREFIX_TIME_YEAR_SECOND,


	/**
	 * 时间序列(年)+后缀
	 * 2019_H
	 */
	TIME_YEAR_SUFFIX,
	/**
	 * 时间序列(年到月)+后缀
	 * 201912_H
	 */
	TIME_YEAR_MONTH_SUFFIX,
	/**
	 * 时间序列(年到天)+后缀
	 * 20191230_H
	 */
	TIME_YEAR_DAY_SUFFIX,
	/**
	 * 时间序列(年到小时)+后缀
	 * 20191230164356_H
	 */
	TIME_YEAR_HOUR_SUFFIX,
	/**
	 * 时间序列(年到分钟)+后缀
	 * 20191230164356_H
	 */
	TIME_YEAR_MINUTE_SUFFIX,
	/**
	 * 时间序列(年到秒)+后缀
	 * 20191230164356_H
	 */
	TIME_YEAR_SECOND_SUFFIX,


	/**
	 * 前缀+时间序列(年)+后缀
	 * Q_2019_H
	 */
	PREFIX_TIME_YEAR_SUFFIX,
	/**
	 * 前缀+时间序列(年到月)+后缀
	 * Q_201912_H
	 */
	PREFIX_TIME_YEAR_MONTH_SUFFIX,
	/**
	 * 前缀+时间序列(年到天)+后缀
	 * Q_20191230_H
	 */
	PREFIX_TIME_YEAR_DAY_SUFFIX,
	/**
	 * 前缀+时间序列(年到小时)+后缀
	 * Q_20191230164356_H
	 */
	PREFIX_TIME_YEAR_HOUR_SUFFIX,
	/**
	 * 前缀+时间序列(年到分钟)+后缀
	 * Q_20191230164356_H
	 */
	PREFIX_TIME_YEAR_MINUTE_SUFFIX,
	/**
	 * 前缀+时间序列(年到秒)+后缀
	 * Q_20191230164356_H
	 */
	PREFIX_TIME_YEAR_SECOND_SUFFIX;

	SerialEnum() {
	}
}
