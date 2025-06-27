package com.snowflake.basic.modules.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 本类包含使用 AES 和 RSA 算法进行数据加密和解密的方法。
 *
 * <p>主要功能包括：</p>
 * <ul>
 *     <li>使用 AES 加密和解密字符串。</li>
 *     <li>使用 RSA 加密和解密 AES 密钥。</li>
 *     <li>生成 RSA 密钥对。</li>
 *     <li>生成随机 AES 密钥。</li>
 * </ul>
 *
 * <p>使用示例：</p>
 * <pre>
 * KeyPair keyPair = CryptoUtils.generateRSAKeyPair();
 * SecretKey aesKey = CryptoUtils.generateAESKey();
 * String encryptedText = CryptoUtils.encryptWithAES("Hello", aesKey);
 * </pre>
 */
public class CryptoUtils {

    // 使用 AES 加密文本
    public static String encryptWithAES(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 使用 AES 解密文本
    public static String decryptWithAES(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    // 使用 RSA 加密 AES 密钥
    public static String encryptAESKeyWithRSA(SecretKey secretKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(secretKey.getEncoded()));
    }

    // 使用 RSA 解密 AES 密钥
    public static SecretKey decryptAESKeyWithRSA(String encryptedAESKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedKey = cipher.doFinal(Base64.getDecoder().decode(encryptedAESKey));
        return new SecretKeySpec(decodedKey, "AES");
    }

    // 生成 RSA 密钥对
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    // 生成 AES 密钥
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 可以使用 192 或 256 位
        return keyGen.generateKey();
    }

    // 将公钥转换为字符串
    public static String publicKeyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 将私钥转换为字符串
    public static String privateKeyToString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // 从字符串恢复公钥
    public static PublicKey stringToPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
    }

    // 从字符串恢复私钥
    public static PrivateKey stringToPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
    }

    // 将 AES 密钥转换为字符串
    public static String aesKeyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // 从字符串恢复 AES 密钥
    public static SecretKey stringToAESKey(String key) {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return new SecretKeySpec(keyBytes, "AES");
    }

}