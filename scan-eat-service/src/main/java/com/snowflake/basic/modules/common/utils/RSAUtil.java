package com.snowflake.basic.modules.common.utils;


import com.snowflake.basic.modules.common.config.RsaKeyConfig;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

@Component
public class RSAUtil {

    // RSA 加密
    public static String encryptRSA(String data) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(
                new X509EncodedKeySpec(Base64.getDecoder().decode(RsaKeyConfig.PUBLIC_KEY))
        );

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // RSA 解密
    public static String decryptRSA(String encryptedData) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(RsaKeyConfig.PRIVATE_KEY))
        );

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(decryptedBytes);
    }
}
