package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	public static final String yyyyMMdd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyyMMddHHmmss_series = "yyyyMMddHHmmss";
	
	
	public static String getDate(String formatter) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	// 获取系统当前时间  (默认格式：yyyy-MM-dd HH:mm:ss)
	public static String getSysCurrentYearMonthDateStr() {
		return getSysCurrentYearMonthDateStr("");
	}
	
	// 获取系统当前时间
	public static String getSysCurrentYearMonthDateStr(String formatter) {
		if (StringUtils.isBlank(formatter)) {
			formatter = yyyyMMddHHmmss;
		}
		return getDate(formatter);
	}
	
	// 昨天
	public static String getYesterday(String formatter) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	// 一周前
	public static String getWeekAgo(String formatter) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -7);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	// 一月前(按30天计)
	public static String getMonthAgo(String formatter) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -30);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	
	// 工作时间(周1-周5 8:00-19:00)
	public static boolean isWorkTime() {
		boolean isWorkWeek = false;
		boolean isWorkHour = false;
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (week>=1 && week<=5) {
			isWorkWeek = true;
		}
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour>=8 && hour <=18) {
			isWorkHour = true;
		}
		
		boolean isWorkTime = isWorkWeek && isWorkHour;
		return isWorkTime;
	}
	
	
	
	public static void main(String[] args) {
		String yesterday = getYesterday(DateUtil.yyyyMMdd);
		System.out.println(yesterday);
		
		boolean isWorkTime = isWorkTime();
		System.out.println(isWorkTime);
	}
}
