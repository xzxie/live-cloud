package com.util.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 对称加密：AES算法
 * Advanced Encryption Standard
 * 支持使用128、192和256位秘钥，并且以128位分组加密和解密数据
 */
public class AESUtil {

	public static final String PASSWORD = "AESAES";
	
	/**
	 * 对数据进行AES加密.
	 * @param data 待进行AES加密的数据
	 * @return 密文
	 */
	public final static String encrypt(String data) throws Exception {
		return new String(encrypt(data, PASSWORD));
	}

	/**
	 * 对用AES加密过的数据进行解密.
	 * @param data AES加密数据
	 * @return 明文
	 */
	public final static String decrypt(String data) throws Exception {
		return new String(decrypt(data.getBytes(), PASSWORD));
	}

	/**
	 * 加密
	 * @param content 需要被加密的字符串
	 * @param password 加密需要的密码
	 * @return 密文
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			// 创建AES的Key生产者
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出128位的key生产者
            keyGen.init(128, new SecureRandom(password.getBytes()));
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param content AES加密过过的内容
	 * @param password 加密时的密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			// 创建AES的Key生产者
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            // 明文
            return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		
		String data = "这个杀手不太冷";
		try {
			String encrypt = AESUtil.encrypt(data);
			System.out.println(encrypt);
			
			String decrypt = AESUtil.decrypt(encrypt);
			System.out.println(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
