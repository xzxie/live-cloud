package com.util.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.util.StringUtil;


/**
 * 非对称加密：RSA
 * 
 */
public class RSAUtil {

	private static final Logger logger = Logger.getLogger(RSAUtil.class);
	
	public static final String KEY_RSA = "RSA";
	public static final String key_rsa_signature = "MD5WithRSA";
	public static final String key_rsa_publickey = "RSAPublicKey";
	public static final String key_rsa_privatekey = "RSAPrivateKey";
	
	/**
	 * 生成秘钥对
	 */
	public static Map<String, Object> init() {
		Map<String, Object> map = Maps.newHashMap();
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
			generator.initialize(1024);
			KeyPair keyPair = generator.generateKeyPair();
			// 公钥
			PublicKey publicKey = keyPair.getPublic();
			// 私钥
			PrivateKey privateKey = keyPair.getPrivate();
			map.put(key_rsa_publickey, publicKey);
			map.put(key_rsa_privatekey, privateKey);
		} catch (NoSuchAlgorithmException e) {
			logger.error("generator secret key pair failed.", e);
		}
		return map;
	}
	
	/**
	 * 用私钥对信息生成数字签名
	 * @param data 加密数据
	 * @param privateKey 私钥
	 */
	public static String sign(byte[] data, String privateKey) {
		String sign = "";
		try {
			// base64编码的私钥解密
			byte[] privateKeyBytes = StringUtil.decryptBase64(privateKey);
			// 构造PKCS8EncodedKeySpec对象
			PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(privateKeyBytes);
			// 指定的加密算法
			KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
			// 取私钥对象
			PrivateKey key = factory.generatePrivate(pkcs);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(key_rsa_signature);
			signature.initSign(key);
			signature.update(data);
			sign = StringUtil.encryptBase64(signature.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	/**
     * 校验数字签名
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verify(byte[] data, String publicKey, String sign) {
        boolean flag = false;
        try {
            // 解密由base64编码的公钥
            byte[] bytes = StringUtil.decryptBase64(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取公钥对象
            PublicKey key = factory.generatePublic(keySpec);
            // 用公钥验证数字签名
            Signature signature = Signature.getInstance(key_rsa_signature);
            signature.initVerify(key);
            signature.update(data);
            flag = signature.verify(StringUtil.decryptBase64(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    /**
     * 私钥加密
     * @param data 待加密数据
     * @param key 私钥
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) {
        byte[] result = null;
        try {
            byte[] bytes = StringUtil.decryptBase64(key);
            // 取得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 私钥解密
     * @param data 加密数据
     * @param key 私钥
     * @return
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) {
        byte[] result = null;
        try {
            // 对私钥解密
            byte[] bytes = StringUtil.decryptBase64(key);
            // 取得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 公钥解密
     * @param data 加密数据
     * @param key 公钥
     * @return
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) {
        byte[] result = null;
        try {
            // 对公钥解密
            byte[] bytes = 	StringUtil.decryptBase64(key);
            // 取得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PublicKey publicKey = factory.generatePublic(keySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param key 公钥
     * @return
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) {
        byte[] result = null;
        try {
            byte[] bytes = StringUtil.decryptBase64(key);
            // 取得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PublicKey publicKey = factory.generatePublic(keySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取公钥
     */
    public static String getPublicKey(Map<String, Object> map) {
        String str = "";
        try {
            Key key = (Key) map.get(key_rsa_publickey);
            str = StringUtil.encryptBase64(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取私钥
     */
    public static String getPrivateKey(Map<String, Object> map) {
        String str = "";
        try {
            Key key = (Key) map.get(key_rsa_privatekey);
            str = StringUtil.encryptBase64(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    
	
	public static void main(String[] args) {
		String privateKey = "";
        String publicKey = "";
        // 生成公钥私钥
        Map<String, Object> map = init();
        publicKey = getPublicKey(map);
        privateKey = getPrivateKey(map);
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);
        System.out.println("公钥加密--------私钥解密");
        String word = "你好，世界！";
        byte[] encWord = encryptByPublicKey(word.getBytes(), publicKey);
        String decWord = new String(decryptByPrivateKey(encWord, privateKey));
        System.out.println("加密前: " + word + "\n\r" + "解密后: " + decWord);
        System.out.println("私钥加密--------公钥解密");
        String english = "Hello, World!";
        byte[] encEnglish = encryptByPrivateKey(english.getBytes(), privateKey);
        String decEnglish = new String(decryptByPublicKey(encEnglish, publicKey));
        System.out.println("加密前: " + english + "\n\r" + "解密后: " + decEnglish);
        System.out.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = sign(encEnglish, privateKey);
        System.out.println("签名:\r" + sign);
        // 验证签名
        boolean status = verify(encEnglish, publicKey, sign);
        System.out.println("状态:\r" + status);
	}
}
