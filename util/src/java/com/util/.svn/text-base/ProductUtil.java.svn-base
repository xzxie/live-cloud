package com.util;

public class ProductUtil {

	
	// 生成订单号：系统时间14位 + 用户ID后5位 + 随机数5位
	public static String geneTransNo(String userId) {
		String transNo = "";
		String current = DateUtil.getSysCurrentYearMonthDateStr(DateUtil.yyyyMMddHHmmss_series);//14位
		
		userId = getFixLengthStr(userId, 5);//5位
		
		String rand = (int) (Math.random()*10000) + "";
		rand = getFixLengthStr(rand, 5);//5位
		
		transNo = current + userId + rand;
		return transNo;
	}
	
	// 截取后N位或填充字符
	public static String getFixLengthStr(String str, int fixLength) {
		if (fixLength <= 0) {
			return "";
		}
		
		String squence = "";
		if (str.length() >= fixLength) {
			squence = str.substring(str.length()-fixLength);
		}
		
		int padding = fixLength-str.length();
		for (int i=0; i<padding; i++) {
			str = "0" + str; 
		}
		squence = str;
		
		return squence;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String squence = ProductUtil.getFixLengthStr("126", 5);
		System.out.println(squence);
		
		System.out.println(System.currentTimeMillis());
		
		String sysCurrent = DateUtil.getSysCurrentYearMonthDateStr(DateUtil.yyyyMMddHHmmss_series);
		System.out.println(sysCurrent);
	}
}
