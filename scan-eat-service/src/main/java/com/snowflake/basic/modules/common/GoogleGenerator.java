package com.snowflake.basic.modules.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class GoogleGenerator {
    // 生成的key长度( Generate secret key length)
    private static final int SECRET_SIZE = 10;

    private static final String SEED = "22150146801713967E8g";
    // Java实现随机数算法
    private static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    // 最多可偏移的时间
    private static final int window_size = 3; // default 3 - max 17

    public static String generateSecretKey() {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            return new String(bEncodedKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 这个format不可以修改，身份验证器无法识别二维码
     */
    public static String getQRBarcode(String user, String secret) {
        String format = "otpauth://totp/%s?secret=%s";
        return String.format(format, user, secret);
    }

    /**
     * 根据user和secret生成二维码的密钥
     */
    public static String getQRBarcodeURL(String user, String secret) {
        int width = 200; // 图片宽度
        int height = 200; // 图片高度

        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // 生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(String.format("otpauth://totp/%s?secret=%s", user, secret), BarcodeFormat.QR_CODE, width, height, hints);

            // 将 BitMatrix 转换为 BufferedImage，并写入 ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            // 将 ByteArrayOutputStream 中的数据转换为 Base64 字符串
            return "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkCode(String secret, String code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verify_code(decodedKey, t + i);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            if (code.equals(addZero(hash))) {
                return true;
            }
        }
        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

    private static String addZero(long code) {
        return String.format("%06d", code);
    }
}
