package com.util.crypto;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.util.Constant;

public class Md5Util {

	private static final Logger logger = Logger.getLogger(Md5Util.class);
	
	/**
	 * 无盐md5算法
	 */
	public static String md5(String password) {
		return md5(password, "");
	}
	
	/**
	 * 加盐md5算法
	 * password = md5(md5(password)+secretkey+salt)
	 */
	public static String md5(String password, String salt) {
		if (StringUtils.isBlank(password)) {
			return "";
		}
		if (StringUtils.isBlank(salt)) {
			salt = Constant.password_default_salt;
		}
		return md5_jdk(md5_jdk(password)+ Constant.password_secretkey +salt);
	}
	
	/*
	 * jdk自带的md5加密算法
	 */
	private static String md5_jdk(String password) {
		StringBuffer buff = new StringBuffer(32);
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(password.getBytes("utf-8"));  
            
	        for (int i = 0; i < bytes.length; i++) {  
	        	buff.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));  
	        }  
		} catch(Exception e) {
			logger.error("password： " + password + " 加密失败!");
		}
		return buff.toString().toLowerCase();
	}
	
	
	// 随机生成6位数盐值
	public static String getSalt() {
		String uuid = UUID.randomUUID().toString();
		String salt = uuid.substring(0, 6);
		return salt;
	}
	
	
	public static void main(String[] args) {
		String password = "hengyang1990";
		System.out.println(Md5Util.md5(password));
	}
}
