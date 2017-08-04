package com.afeng.utils.javaBasis.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Util {

	//KEY 需要16位
    private static final String KEY = "afengutilappli01";
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class);
    
    public static void main(String[] args) {
		System.out.println(Base64Util.encrypt("你是"));
		System.out.println(Base64Util.decrypt("GkmB7oWokBtjmieMTZfbEg=="));
	}
    
    /**
     * 加密字符串
     */
    public static String encrypt(String value) {
        try {
            byte[] encrypted = getEncryptCipher().doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            String errmsg = "加密异常:" + ex;
            LOGGER.error(errmsg,ex);
        }
        return null;
    }

    /**
     * 解密字符串
     */
    public static String decrypt(String encrypted) {
        try {
            byte[] original = getDecryptCipher().doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            String errmsg = "解密异常:" + ex;
        }

        return null;
    }

    /**
     * 加密
     */
    private static Cipher getEncryptCipher() throws Exception{
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return encryptCipher;
    }

    /**
     * 解密
     */
    private static Cipher getDecryptCipher() throws Exception{
        IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        return decryptCipher;
    }
    
}
