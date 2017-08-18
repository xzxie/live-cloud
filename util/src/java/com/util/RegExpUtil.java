package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegExpUtil {

	public static boolean isEmail(String email) {
		String regular = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isPhone(String phone) {
		String regular = "^1[34578]\\d{9}$";
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	public static boolean isPassword(String password) {
		String regular = "^[0-9a-zA-Z]{6,15}$";
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean isQQ(String qq) {
		if (StringUtils.isBlank(qq)) {
			return false;
		}
		if (qq.startsWith("qq:")) {
			return true;
		}
		return false;
	}
	
	public static boolean isWeixin(String weixin) {
		if (StringUtils.isBlank(weixin)) {
			return false;
		}
		if (weixin.startsWith("wx:")) {
			return true;
		}
		return false;
	}
	
	public static boolean isWeibo(String weibo) {
		if (StringUtils.isBlank(weibo)) {
			return false;
		}
		if (weibo.startsWith("wb:")) {
			return true;
		}
		return false;
	}
}
