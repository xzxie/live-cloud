package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String yyyyMMdd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	
	
	
	/**
	 * 获取系统当前时间
	 */
	public static String getSysCurrentYearMonthDateStr(String formatter) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String dateStr = sdf.format(date);
		return dateStr;
	}
}
