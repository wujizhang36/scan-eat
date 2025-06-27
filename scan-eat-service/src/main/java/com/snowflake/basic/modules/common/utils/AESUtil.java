package com.snowflake.basic.modules.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    // 生成 AES 密钥
    public static String generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // AES 加密
    public static String encryptAES(String data, String aesKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // AES 解密
    public static String decryptAES(String encryptedData, String aesKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(decryptedBytes);
    }
}