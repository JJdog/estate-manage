package com.lanswon.commons.core.time;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期/时间工具类
 *
 * <p>
 *     提供对日期和时间的常规操作，基于Jdk1.8编写。Java8中新增日期和时间是符合{@code ISO-8601}标准的
 *     对时间格式返回进行了基本的封装，提拱了几种时间格式
 * </p>
 * <P>
 *     {@link java.util}中对时间的操作的类不是安全的，Java8提供了新的日期时间类来操作
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
	 * 获得date到月底的天数
	 * @param date 时间
	 * @return 天数
	 */
	public static int getDaysToEndOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDaysToStartOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  calendar.get(Calendar.DAY_OF_MONTH)- calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

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

