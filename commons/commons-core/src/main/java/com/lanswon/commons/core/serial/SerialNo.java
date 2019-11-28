package com.lanswon.commons.core.serial;



import com.lanswon.commons.core.time.DateFormatEnum;
import com.lanswon.commons.core.time.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 序列号生成工具类
 * <P>
 *     支持多种序列号生成的方式
 *     <ul>
 *         <li>UUID风格</li>
 *         <li>前缀+时间+后缀(随机字母):xulie20181210abkc</li>
 *         <li>时间+后缀(随机字母):20181210abkc</li>
 *         <li>时间+后缀(数字):201812120001</li>
 *     </ul>
 * </P>
 * <p>
 *     使用了builder模式
 * </p>
 * @author Jaswine
 */
public  class SerialNo {

	private SerialNo(){}

	/**
	 * 初始化序列号
	 * @param serialEnum 序列号风格
	 * @return 序列号
	 */
	public static Builder builder(SerialEnum serialEnum){
		return new Builder(serialEnum);
	}


	public static class Builder {

		///////REQUIRED///////
		/**
		 * 序列风格
		 */
		private SerialEnum serialEnum;

		///////OPTIONAL///////
		/**
		 * 前缀
		 */
		private String prefix;
		/**
		 * 后缀
		 */
		private String suffix;

		Builder(SerialEnum serialEnum) {
			this.serialEnum = serialEnum;
		}

		public Builder withPrefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

		public Builder withSuffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		public String generateSerial() {

			switch (this.serialEnum) {
				case UUID:
					return UUID.randomUUID().toString().replace("-", "");
				case TIME_YEAR:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY);
				case TIME_YEAR_MONTH:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_LINK);
				case TIME_YEAR_DAY:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_LINK);
				case TIME_YEAR_HOUR:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_LINK);
				case TIME_YEAR_MINUTE:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_LINK);
				case TIME_YEAR_SECOND:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_SS_LINK);
				case PREFIX_TIME_YEAR:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY);
				case PREFIX_TIME_YEAR_MONTH:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_LINK);
				case PREFIX_TIME_YEAR_DAY:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_LINK);
				case PREFIX_TIME_YEAR_HOUR:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_LINK);
				case PREFIX_TIME_YEAR_MINUTE:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_LINK);
				case PREFIX_TIME_YEAR_SECOND:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_SS_LINK);
				case TIME_YEAR_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY) + this.suffix;
				case TIME_YEAR_MONTH_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_LINK) + this.suffix;
				case TIME_YEAR_DAY_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_LINK) + this.suffix;
				case TIME_YEAR_HOUR_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_LINK) + this.suffix;
				case TIME_YEAR_MINUTE_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_LINK) + this.suffix;
				case TIME_YEAR_SECOND_SUFFIX:
					return DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_SS_LINK) + this.suffix;
				case PREFIX_TIME_YEAR_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY) + this.suffix;
				case PREFIX_TIME_YEAR_MONTH_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_LINK) + this.suffix;
				case PREFIX_TIME_YEAR_DAY_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_LINK) + this.suffix;
				case PREFIX_TIME_YEAR_HOUR_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_LINK) + this.suffix;
				case PREFIX_TIME_YEAR_MINUTE_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_LINK) + this.suffix;
				case PREFIX_TIME_YEAR_SECOND_SUFFIX:
					return this.prefix + DateTimeUtil.getDateTime(LocalDateTime.now(), DateFormatEnum.YYYY_MM_DD_HH_MM_SS_LINK) + this.suffix;
				default:
					return "";
			}
		}
	}
}
