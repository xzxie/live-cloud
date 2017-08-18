package com.util.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.util.StringUtil;

/**
 * HMAC：散列(摘要)算法
 * 哈希消息授权码: Hash-based Message Authentication Code
 * 在消息摘要算法(MD5/SHA系列算法)基础上，使用密钥对消息摘要进行加密
 * 
 * 主要应用于身份验证
 * http://www.jianshu.com/p/3fe2add1eb42
 * 
 * 算法种类：
 * 		Hmac-MD5
 * 		Hmac-SHA1
 * 		Hmac-SHA256
 * 		Hmac-SHA384
 * 		Hmac-SHA512
 */

public class HmacUtil {
	
	public static final String HmacMD5 = "";
	public static final String HmacSHA1 = "HMAC-SHA1";
	public static final String HmacSHA256 = "HMAC-SHA256";
	public static final String HmacSHA384 = "HMAC-SHA384";
	public static final String HmacSHA512 = "HMAC-SHA512";

	/**
	 * 生成秘钥
	 */
	public static byte[] getSecretKey(String algorithm) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] key = secretKey.getEncoded();
		return key;
	}
	
	
	public static String encryptHmac(byte[] key, byte[] data, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(key, algorithm);
		
		Mac mac = Mac.getInstance(algorithm);
		mac.init(secretKey);
		
		byte[] encryptBytes = mac.doFinal(data);
		String encrypt = StringUtil.byte2hex(encryptBytes);
		
		return encrypt;
	}
	
	
	
	public static void main(String[] args) {
		try {
			String algorithm = "HmacMD5";
			byte[] data = "helloworld".getBytes();
			byte[] key = HmacUtil.getSecretKey(algorithm);
			String encrypt = HmacUtil.encryptHmac(key, data, algorithm);
			System.out.println(encrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
