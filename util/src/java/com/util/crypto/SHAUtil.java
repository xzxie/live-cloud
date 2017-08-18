package com.util.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * SHA-安全散列系列
 * SHA-0
 * SHA-1：用于TLS/SSL、PGP、SSH、S/MIME和IPsec
 * SHA-2：[SHA-224 / SHA-256 / SHA-384 / SHA-512]
 * SHA-3：Keccak
 * 
 * 依赖commons-codec-1.5.jar
 */
public class SHAUtil {

	private static final Logger logger = Logger.getLogger(SHAUtil.class);
	
	public static final String md5 = "MD5";
	public static final String sha1 = "SHA-1";
	public static final String sha256 = "SHA-256";
	public static final String sha384 = "SHA-384";
	public static final String sha512 = "SHA-512";
	
	/**
	 * 密码加密算法
	 */
	public static String digest(String password, String algorithm) {
		String newPass = "";
		if (StringUtils.isBlank(password)) {
			return newPass;
		}
		if (StringUtils.isBlank(password) || md5.equals(algorithm)) {
			newPass = DigestUtils.md5Hex(password);
		} else if (sha1.equals(algorithm)) {
			newPass = DigestUtils.sha1Hex(password);
		} else if (sha256.equals(algorithm)) {
			newPass = DigestUtils.sha256Hex(password);
		} else if (sha384.equals(algorithm)) {
			newPass = DigestUtils.sha384Hex(password);
		} else if (sha512.equals(algorithm)) {
			newPass = DigestUtils.sha512Hex(password);
		}
		return newPass;
	}
	
	/**
	 * 文件加密算法
	 */
	public static void digestFile(String filename, String algorithm) {
		byte[] b = new byte[1024 * 4];
		int len;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			fis = new FileInputStream(filename);
			while ((len = fis.read()) != -1) {
				md.update(b, 0, len);
			}
			byte[] digest = md.digest();
			StringBuffer fileNameBuffer = new StringBuffer(128).append(filename).append(".").append(algorithm);
			fos = new FileOutputStream(fileNameBuffer.toString());
			OutputStream encodedStream = new Base64OutputStream(fos);
			encodedStream.write(digest);
			encodedStream.flush();
			encodedStream.close();
		} catch (Exception e) {
			logger.error("read file inputstream failed", e);
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// 密码散列
		System.out.println(SHAUtil.digest("123456", md5));		//32
		System.out.println(SHAUtil.digest("123456", sha1));		//40
		System.out.println(SHAUtil.digest("123456", sha256));	//64
		System.out.println(SHAUtil.digest("123456", sha384));	//96
		System.out.println(SHAUtil.digest("123456", sha512));	//128
		
		// 文件散列
		String filename1 = "D:\\1.txt";
		String filename2 = "D:\\2.txt";
		String filename3 = "D:\\3.txt";
		String filename4 = "D:\\4.txt";
		String filename5 = "D:\\5.txt";
		SHAUtil.digestFile(filename1, md5);
		SHAUtil.digestFile(filename2, sha1);
		SHAUtil.digestFile(filename3, sha256);
		SHAUtil.digestFile(filename4, sha384);
		SHAUtil.digestFile(filename5, sha512);
	}
}
