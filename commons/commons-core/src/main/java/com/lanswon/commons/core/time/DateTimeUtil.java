package com.lanswon.commons.core.time;



import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期/时间工具类
 *
 * <p>
 *     提供对日期和时间的常规操作，基于Jdk1.8编写。Java8中新增日期和时间是符合{@code ISO-8601}标准的
 *     对时间格式返回进行了基本的封装，提拱了几种时间格式
 * </p>
 * <P>
 *     {@link java.util.Date}中对时间的操作的类不是安全的，Java8提供了新的日期时间类来操作
 * </P>
 *
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 * @see java.time.Duration
 *
 * @author Jaswine
 * @version 1.0
 *
 */
public final class DateTimeUtil {


	/*====================================时间类型转化============================================*/

	/**
	 * Date转化成为LocalDateTime
	 * @param date date
	 * @return localDateTime
	 */
	public static LocalDateTime convertDate2LocalDateTime(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * Date转化成为LocalDate
	 * @param date date
	 * @return localDate
	 */
	public static LocalDate convertDate2LocalDate(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Date转化成为LocalTime
	 * @param date date
	 * @return localTime
	 */
	public static LocalTime convertDate2LocalTime(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
	}

	public static Date convert2Date(LocalDateTime dateTime){
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date convert2Date(LocalDate date){
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}



	/*====================================时间计算============================================*/




	/*====================================时间格式化============================================*/

	public static String format(LocalDateTime dateTime,DateFormatEnum formatEnum){
		return DateTimeFormatter.ofPattern(formatEnum.getValue()).format(dateTime);
	}

	public static String format(LocalDate localDate,DateFormatEnum formatEnum){
		return DateTimeFormatter.ofPattern(formatEnum.getValue()).format(localDate);
	}



	/**
	 * 获得日期(默认:2018-10-10)
	 * @return yyyy-mm-dd
	 */
	public static String getDate(){
		return getDate(LocalDate.now(),DateFormatEnum.YYYY_MM_DD);
	}



	/**
	 * 获得日期 - 指定返回格式
	 * @param date 本地日期对象
	 * @param dateFormat 日期格式
	 * @return 对应格式的日期
	 * @throws UnsupportedTemporalTypeException 不支持的日期格式异常
	 */
	public static String getDate(LocalDate date,DateFormatEnum dateFormat) throws UnsupportedTemporalTypeException {
		return DateTimeFormatter.ofPattern(dateFormat.getValue()).format(date);
	}

	/**
	 * 获得日期时间(默认:2018-10-10 10:10:10)
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDateTime(){
		return getDateTime(LocalDateTime.now(),DateFormatEnum.YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * 获得日期时间 - 指定返回格式
	 * @param dateTime 本地日期时间
	 * @param dateFormat 日期时间格式
	 * @throws UnsupportedTemporalTypeException 不支持的日期时间格式
	 * @return 对应格式的日期时间
	 */
	public static String getDateTime(LocalDateTime dateTime,DateFormatEnum dateFormat) throws UnsupportedTemporalTypeException{
		return DateTimeFormatter.ofPattern(dateFormat.getValue()).format(dateTime);
	}

	/**
	 * 获得时间(默认:10:10：10)
	 * @return hh:mm:ss
	 */
	public static String getTime(){
		return getTime(LocalTime.now(),DateFormatEnum.HH_MM_SS);
	}

	/**
	 * 获得时间 - 指定返回格式
	 * @param time 时间
	 * @param dateFormatEnum 时间格式
	 * @throws UnsupportedTemporalTypeException 不支持的时间格式
	 * @return 对应格式的时间
	 */
	public static String getTime(LocalTime time,DateFormatEnum dateFormatEnum) throws UnsupportedTemporalTypeException{
		return DateTimeFormatter.ofPattern(dateFormatEnum.getValue()).format(time);
	}

	/**
	 * 返回指定月中的天
	 * @param date 日期
	 * @param day 指定天
	 * @return 指定日期
	 */
	public static Calendar getDayOfMonth(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),day);
		return calendar;
	}

	/**
	 * 获得date到月底的天数
	 * @param date 时间
	 * @return 天数
	 */
	public static int getDaysToEndOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) + 1;
	}

	/** 获得日期的月尾日期 */
	public static Calendar getEndDateOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	/** 格式化时间 */
	public static String formatDatetime(Date date,String format){
		return formatDatetime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), format);
	}

	/** 格式化时间 */
	public static String formatDatetime(LocalDateTime dateTime ,String format){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.CHINESE);
		return formatter.format(dateTime);
	}


	public static int getDaysToStartOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  calendar.get(Calendar.DAY_OF_MONTH)- calendar.getActualMinimum(Calendar.DAY_OF_MONTH) + 1;
	}

	public static long getDaysBtDate(Date d1,Date d2){
		LocalDateTime startDate = convertDate2LocalDateTime(d1);
		LocalDateTime endDate = convertDate2LocalDateTime(d2);
		return startDate.until(endDate, ChronoUnit.DAYS);
	}


	/*=======================转换==========================*/



	public static int getMonthsBtDate(Date d1,Date d2){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		int month = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		int year = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;


		return Math.abs(month + year);
	}

	public static int getMonthByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}


	public static Calendar addMonth(Date date,int i ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		return calendar;
	}

	public static boolean isFirstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH) == 1;
	}

	public static Integer getYearOfDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static Calendar addDayOfDate(Date date,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.get(Calendar.DAY_OF_MONTH),days);
		return calendar;
	}

}

