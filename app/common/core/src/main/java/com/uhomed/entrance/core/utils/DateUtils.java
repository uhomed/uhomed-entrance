/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.uhomed.entrance.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 时间工具类
 * 
 * @ClassName: Pageable
 * @version 1.0 2013-8-8 下午02:01:23
 * @lastUpdateTime 2013-8-8 下午02:01:23
 */
public class DateUtils {

	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static void main(String[] args) {
//		Date now = new Date();
//		Date postTime = string2date("2015-04-13 16:00:00",TIME_FORMAT);
//		Long diff = dateDiffSenc(now,postTime);
//		System.out.println(now);
//		System.out.println(postTime);
//		long hour = diff / 60 /60;
//		long mi = diff / 60 - (hour * 60) ;
//		long s = diff - (hour * 60 * 60) - (mi * 60);
//		System.out.println(hour + ":" + mi +":" + s);
	}
	
	/**
	 * 创建一个时间
	 * 
	 * @return yyyy-mm-dd
	 * @author lim
	 */
	public static java.util.Date createDate() {
		return new Date();
	}

	/**
	 * 方法说明：给日期加上一天
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addDay(java.util.Date date) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	/**
	 * 方法说明：给日期加上一月
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addMonth(java.util.Date date, int month) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}
	
	/**
	 * 给日期添加分钟
	 * @param date
	 * @param month
	 * @return
	 */
	public static java.util.Date addMinute(java.util.Date date, int minute) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	public static java.util.Date setMinute(java.util.Date date, int minute) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	/**
	 * 方法说明：
	 * @param begin
	 * @param k
	 * @return
	 */
	public static String addDate(Date date, int k) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, k);
		return format(cal.getTime());
	}

	/**
	 * 方法说明：给日期加上指定天数
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addCertainDays(java.util.Date date,
			Integer days) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	public static java.util.Date addHour(java.util.Date date,
			Integer hour) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		return cal.getTime();
	}
	
	public static java.util.Date setHour(java.util.Date date,Integer hour){
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, hour);
		return cal.getTime();
	}

	/**
	 * 方法说明：给日期加上指定年数
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addCertainYears(java.util.Date date,
			Integer years) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法说明：给日期加上指定年数
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date subtractCertainYears(java.util.Date date,
			Integer years) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -years);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法说明：根据时间获取日期
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getDay(java.util.Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		String s = new java.text.SimpleDateFormat(DATE_FORMAT).format(cal
				.getTime());
		SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sf.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 
	 * 方法说明：根据时间获取月初与月末日期
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date[] getMonth(java.util.Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
		java.util.Date begin = cal.getTime();
		cal.set(java.util.Calendar.DAY_OF_MONTH, lastDay);
		java.util.Date end = cal.getTime();
		return new java.util.Date[] { begin, end };
	}

	/**
	 * 
	 * 方法说明：根据一个时间获得这个时间对应的周开始和结束
	 * 
	 * @return
	 */
	public static String[] getDayInWeekBeginAndEnd(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
		String begin, end;
		if (week == 0) {
			begin = addDate(date, -6);
			end = format(date);
		} else {
			int days = 7 - week;
			begin = addDate(date, -(week - 1));
			end = addDate(date, days);
		}
		return new String[] { begin, end };
	}

	/**
	 * 
	 * 方法说明：得到"yyyy-MM-dd"格式的日期字符串
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getToday() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return new java.text.SimpleDateFormat(DATE_FORMAT).format(cal
				.getTime());
	}

	/**
	 * 方法说明：得到 "yyyy年m月d日" 格式的日期字符串
	 * 
	 * @return
	 */
	public static String getCNToday() {
		Calendar calendar = GregorianCalendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR));
		sb.append("年");
		sb.append(calendar.get(Calendar.MONTH) + 1);
		sb.append("月");
		sb.append(calendar.get(Calendar.DAY_OF_MONTH));
		sb.append("日");
		return sb.toString();

	}

	/**
	 * 方法说明：
	 * @return 2014-09-15 11:49:52
	 * @throws ParseException
	 */
	public static String getCurrentTime() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return new java.text.SimpleDateFormat(TIME_FORMAT).format(cal
				.getTime());
	}
	
	public static String getCurrentTime(String parent){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return new java.text.SimpleDateFormat(parent).format(cal
				.getTime());
	}
	
	/**
	 * 
	 * 方法说明：2个时间进行比较，并把比较结果转成小时/分钟/秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long[] sec2Hour(java.util.Date date, java.util.Date date2) {
		return sec2Hour(date.getTime(), date2.getTime());
	}

	/**
	 * 
	 * 方法说明：2个时间进行比较，并把比较结果转成小时/分钟/秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long[] sec2Hour(long start, long end) {
		long times = end - start;
		Long totalSec = (Long) times / 1000;
		Long hour = totalSec / (60 * 60);
		Long min = (totalSec - hour * 60 * 60) / 60;
		Long sec = totalSec - hour * 60 * 60 - min * 60;
		return new Long[] { hour, min, sec };
	}

	/**
	 * 
	 * 方法说明：根据时间获取"yyyy-MM-dd"格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getDay(String date) {
		SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * 方法说明：返回系统当前时间的月最初和月最某信息
	 * 
	 * @return
	 */
	public static java.util.Date[] getDateMonth() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
		java.util.Date begin = cal.getTime();
		cal.set(java.util.Calendar.DAY_OF_MONTH, lastDay);
		java.util.Date end = cal.getTime();
		return new java.util.Date[] { begin, end };
	}

	/**
	 * 
	 * 方法说明：
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	/**
	 * 
	 * 方法说明：格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DATE_FORMAT);
	}

	/**
	 * 
	 * 方法说明：格式化日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse(String dateStr) {
		return parse(dateStr, DATE_FORMAT);
	}

	/**
	 * 
	 * 方法说明：格式化日期，并指定格式
	 * 
	 * @param dateStr
	 * @param formate
	 * @return
	 */
	public static Date parse(String dateStr, String formate) {
		SimpleDateFormat sf = new SimpleDateFormat(formate);
		try {
			return sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法说明：判断时间是否是简单的格式(yyyy-MM-dd)还是yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSimpleDate(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		return cal.get(java.util.Calendar.HOUR_OF_DAY) == 0
				&& cal.get(java.util.Calendar.MINUTE) == 0
				&& cal.get(java.util.Calendar.SECOND) == 0;
	}

	/**
	 * 
	 * 方法说明：判断时间date1是否在时间date2之前
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateBefore(Date date1, Date date2) {
		return date1.before(date2);
	}

	/**
	 * 
	 * 方法说明：判断时间date1是否等于date2或在date2之前
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateBeforeOrEqual(Date date1, Date date2) {
		return date1.before(date2) || date1.equals(date2);
	}

	/**
	 * 
	 * 方法说明：判断时间date是否大于今天 true 大于等于今天 false 小于今天
	 * 
	 * @param today
	 * @param publicTime
	 * @return
	 */
	public static boolean greaterOrEqualThanToday(Date date) {
		String today = getToday();
		String _date = format(date, DATE_FORMAT);
		return _date.compareTo(today) >= 0 ? true : false;
	}

	/**
	 * 
	 * 方法说明：将指定时间重置为当天凌晨时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date moveBeginOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();
	}

	/**
	 * 
	 * 方法说明：将指定时间重置为当天深夜时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date moveLastOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 
	 * 方法说明：获得日期对应的周信息 注意:日期减一是为了中国的习惯 礼拜一到礼拜天为一周 默认是礼拜天-礼拜六
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.DAY_OF_MONTH, -1);
		return cal.get(java.util.Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 
	 * 方法说明：将字符串转化成Date
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static Date convertString2Date(String value, String format) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			date = df.parse(value);
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 
	 * 方法说明：两个时间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long dateDiffDay(Date date1, Date date2) {
		long dayFrom = date1.getTime() / (1000l * 60 * 60 * 24);
		long dayTo = date2.getTime() / (1000l * 60 * 60 * 24);
		return dayTo - dayFrom;
	}
	
	/**
	 * @param date1 小
	 * @param date2 大
	 * @return
	 */
	public static Long dateDiffHour(Date date1,Date date2){
		long dayFrom = date1.getTime() / (1000l * 60 * 60);
		long dayTo = date2.getTime() / (1000l * 60 * 60);
		return dayTo - dayFrom;
	}
	
	/**
	 * 最小为0
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long dateDiffHourMinZERO(Date date1,Date date2){
		long diff = dateDiffHour(date1, date2);
		return diff < 0 ? 0 : diff;
	}
	
	public static Long dateDiffMinute(Date date1,Date date2){
		long dayFrom = date1.getTime() / (1000l * 60);
		long dayTo = date2.getTime() / (1000l * 60);
		return dayTo - dayFrom;
	}
	
	public static Long dateDiffYear(Date date1,Date date2){
		long dayFrom = date1.getTime() / (1000l * 60 * 60 * 24 * 365);
		long dayTo = date2.getTime() / (1000l * 60 * 60 * 24 * 365);
		return dayTo - dayFrom;
	}
	
	/**
	 * 
	 * 方法说明：两个时间相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long dateDiffSenc(Date date1, Date date2) {
		long dayFrom = date1.getTime() / (1000l);
		long dayTo = date2.getTime() / (1000l);
		return dayTo - dayFrom;

	}

	/**
	 * 比较两个时间大小，date1>=date2返回true，反之返回false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {
		long day1 = date1.getTime();
		long day2 = date2.getTime();
		if (day1 >= day2)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * 方法说明：计算2个时间的间隔数据 如2010-1-1 2010-1-3 则2着相隔3天
	 * 
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static int calculateIntervalDay(Date data1, Date data2) {
		long begin = data1.getTime();
		int interval = (int) ((data2.getTime() - begin) / (24 * 3600 * 1000));
		return (interval + 1);
	}

	/**
	 * 
	 * 方法说明：替换原日期的时间为指定时间(HH:mm)
	 * 
	 * @param date
	 *            ,time(HH:mm)
	 * @return
	 */
	public static java.util.Date moveNewTime(java.util.Date date, String time) {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		Date tempDate;
		Calendar tempCalendar = Calendar.getInstance();
		try {
			tempDate = sf.parse(time);
			tempCalendar.setTime(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, tempCalendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, tempCalendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();
	}

	/**
	 * 
	 * 方法说明：给日期加上指定小时
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addCertainTime(java.util.Date date,
			double hours) {
		int s = (int) (hours * 60 * 60);
		int hour = s / 3600;
		int min = (s - hour * 3600) / 60;
		int sec = s - hour * 3600 - min * 60;
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		cal.add(Calendar.MINUTE, min);
		cal.add(Calendar.SECOND, sec);
		return cal.getTime();
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;
		String formatStyle = "";
		String[] u = { "天", "月", "年" };
		if (stype == 0) {
			formatStyle = DATE_FORMAT;
		} else if (stype == 1) {
			formatStyle = "yyyy-MM";
		} else {
			formatStyle = "yyyy";
		}

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 0) {
				c1.add(Calendar.DATE, 1); // 比较月份，月份+1
			} else if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.YEAR, 1); // 比较天数，年+1
			}
		}

		System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":"
				+ n);
		return n;
	}

	/**
	 * 两个时间点之间的所有日期
	 * 
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static ArrayList<Date> dateList(String date1, String date2, int stype) {
		String formatStyle = "";
		if (stype == 0) {
			formatStyle = DATE_FORMAT;
		} else if (stype == 1) {
			formatStyle = "yyyy-MM";
		} else {
			formatStyle = "yyyy";
		}
		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		ArrayList<Date> list = new ArrayList<Date>();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			list.add(c1.getTime()); // 这里可以把间隔的日期存到数组中 打印出来

			if (stype == 0) {
				c1.add(Calendar.DATE, 1); // 比较月份，月份+1
			} else if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.YEAR, 1); // 比较天数，年+1
			}
		}

		return list;
	}

	/**
	 * 将长整型数字转换为日期格式的字符串
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String convert2String(long time, String format) {
		if (time > 0l) {
			if (StringUtils.isBlank(format)) {
				format = DateUtils.TIME_FORMAT;
			}
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	/**
	 * 转换
	 * @param date
	 * @param parent
	 * @return
	 */
	public static Date string2date(String date,String parent) {
		SimpleDateFormat format = new SimpleDateFormat(parent);
		Date dateTime = null;
		try {
			dateTime = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date string2date(String date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Date dateTime = null;
		try {
			dateTime = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	/**
	 * 取得两个日期间隔毫秒数（日期1-日期2）
	 * 
	 * @param form
	 *            日期1
	 * @param to
	 *            日期2
	 * 
	 * @return 间隔毫秒数
	 */
	public static long getDiffMilliSeconds(Date form, Date to) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(form);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(to);
		return (cal1.getTimeInMillis() - cal2.getTimeInMillis());
	}
	
	/**
	 * 是否包含在范围时间
	 * @param start
	 * @param end
	 * @param value
	 * @return
	 */
	public static boolean isTimeRenge(Date start,Date end,Date value){
	    if((value.after(start) && value.before(end)) || start.getTime() == value.getTime() || end.getTime() == value.getTime()){
	    	return true;
	    }
		return false;
	}
	
	/**
	 * 获取当前时间小时
	 * <pre>0~23</pre>
	 * @return
	 */
	public static int getHour(){
		return getHour(new Date());
	}
	
	/**
	 * 获取指定时间的小时
	 * <pre>0~23</pre>
	 * @param date
	 * @return
	 */
	public static int getHour(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    return hour;
	}
	
	
}
