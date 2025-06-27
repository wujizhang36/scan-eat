package com.snowflake.basic.modules.common.utils;

public class NumericIdGeneratorUtil {
    private static final long TWEPOCH = 1700000000000L; // 自定义起始时间戳
    private static final int MAX_SEQUENCE = 9999;
    private static long lastTimestamp = -1L;
    private static int sequence = 0;

    public static synchronized long generateId(int digits) {
        if (digits < 6 || digits > 18) {
            throw new IllegalArgumentException("Digits must be between 6 and 18.");
        }

        long timestamp = System.currentTimeMillis();

        if (timestamp == lastTimestamp) {
            sequence++;
            if (sequence > MAX_SEQUENCE) {
                timestamp = waitNextMillis(timestamp);
                sequence = 0;
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        // 组合时间戳与序列号
        String base = String.valueOf(timestamp) + String.format("%04d", sequence);

        // 截断或补齐至指定长度
        if (base.length() > digits) {
            base = base.substring(base.length() - digits);
        } else {
            base = String.format("%0" + digits + "d", Long.parseLong(base));
        }

        return Long.parseLong(base);
    }

    private static long waitNextMillis(long lastTimestamp) {
        long now = System.currentTimeMillis();
        while (now <= lastTimestamp) {
            now = System.currentTimeMillis();
        }
        return now;
    }
}
