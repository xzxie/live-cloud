package com.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class StringUtil {

	
	public static long ipToLong(String strIP) {
		if (strIP == null || strIP.length() == 0) {
			return 0L;
		}
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3]; // ip1*256*256*256+ip2*256*256+ip3*256+ip4
	}
	
	/**
	 * 将16进制值的字符串转换为byte数组(二进制)
	 */
	public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
	
	/**
	 * 将byte数组(二进制)转换为表示16进制值的字符串
	 */
	public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }
	
	/**
	 * BASE64 加密
	 */
	public static String encryptBase64(byte[] data) {
		return (new BASE64Encoder()).encodeBuffer(data);
	}
	
	/**
	 * BASE64 解密
	 */
	public static byte[] decryptBase64(String data) throws IOException {
		return new BASE64Decoder().decodeBuffer(data);
	}
	
	
	
	
	public static void main(String[] args) {
		
	}
}
