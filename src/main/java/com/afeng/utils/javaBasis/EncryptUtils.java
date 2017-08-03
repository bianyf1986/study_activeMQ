package com.afeng.utils.javaBasis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @Description : 加密工具类：md5加密出来的长度是32位、sha加密出来的长度是40位
 * @author      : yfbian
 */
public class EncryptUtils {
	
	public static void main(String[] args) {
		System.out.println(md5AndSha("12313"));
		System.out.println(md5AndSha("12313").length());
	}

	/**
	 * 
	 * @Function :  md5
	 * @Desc     :  md5加密
	 * @Author   :  yfbian
	 * @param inputText 加密目标串
	 * @return 加密结果串
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}
	
	/**
	 * 
	 * @Function :  sha
	 * @Desc     :  sha加密
	 * @Author   :  yfbian
	 * @param inputText 加密目标串
	 * @return 加密结果串
	 */
	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}
	
	/**
	 * 
	 * @Function :  md5AndSha
	 * @Desc     :  md5和sha双加密
	 * @Author   :  yfbian
	 * @param inputText 加密目标串
	 * @return 加密结果串
	 */
	public static String md5AndSha(String inputText) {
		return sha(md5(inputText));
	}

	/**
	 * 
	 * @Function :  encrypt
	 * @Desc     :  md5或者sha-1加密
	 * @Author   :  yfbian
	 * @param inputText 要加密的内容
	 * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
	 * @return 加密结果串
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	/**
	 * 
	 * @Desc     :  返回十六进制字符串
	 * @param arr byte[]
	 * @return 十六进制字符串
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}
	
}