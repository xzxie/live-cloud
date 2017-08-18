package com.util.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.util.StringUtil;

/**
 * 对称加密：DES算法
 * 使用56位密钥,密钥长度都必须是8的倍数
 */
public class DESUtil {

	/** 加解密key **/
	private static final String PASSWORD_CRYPT_KEY = "kEHrDooxWHCWtfeSxvDvgqZq";
	
	/**
	 * 对数据进行DES加密.
	 * @param data 待进行DES加密的数据
	 * @return 返回经过DES加密后的数据
	 */
	public final static String decrypt(String data) throws Exception {
        return new String(decrypt(StringUtil.hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
    }
	
	/**
	 * 对用DES加密过的数据进行解密.
	 * @param data DES加密数据
	 * @return 返回解密后的数据
	 */
	public final static String encrypt(String data) throws Exception  {
        return StringUtil.byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
    }
	
	/**
	 * 加密
	 * @param datasource byte[]
	 * @param key byte[]
	 * @return byte[]
	 */
	private static byte[] encrypt(byte[] data, byte[] key) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(data);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param src byte[]
	 * @param key byte[]
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(key);
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(data);
	}
	
	
	
	public static void main(String[] args) {

		String text = "测试内容";
		// 密码，长度要是8的倍数
		String password = "12345678";

		try {
			String encrypt = DESUtil.encrypt(text);
			System.out.println("加密后：" + new String(encrypt));
			
			String decrypt = DESUtil.decrypt(encrypt);
			System.out.println("解密后：" + new String(decrypt));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
