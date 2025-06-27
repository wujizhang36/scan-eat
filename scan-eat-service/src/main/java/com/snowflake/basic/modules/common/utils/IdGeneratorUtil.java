package com.snowflake.basic.modules.common.utils;

/**
 * 雪花模型+时间戳的方式 生成9位数字
 * 不同的节点分配不同的 Worker ID，Worker ID 必须保持唯一，最多可以有 31 个不同的 Worker ID
 *
 *  
 * @date 2025/2/8
 **/
public class IdGeneratorUtil {
    private static final long TWEPOCH = 1700000000000L; // 自定义起始时间戳（建议设置为某个固定过去时间）
    private static final long WORKER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    private static final long WORKER_ID = 1L; // 修改为你的机器编号，0 ~ 31

    public static synchronized long generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID.");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (WORKER_ID << WORKER_ID_SHIFT)
                | sequence;
    }

    public static String generateId(int digits) {
        long id = generateId();
        String encoded = Base62.encode(id);

        // 截断或填充
        if (encoded.length() > digits) {
            return encoded.substring(encoded.length() - digits); // 保留末尾
        } else {
            return String.format("%" + digits + "s", encoded).replace(' ', '0');
        }
    }

    private static long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
    class Base62 {
        private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

        public static String encode(long value) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(BASE62[(int)(value % 62)]);
                value /= 62;
            } while (value > 0);
            return sb.reverse().toString();
        }
    }
}
