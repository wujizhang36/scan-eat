package com.snowflake.basic.modules.common.annotation;

import java.lang.annotation.*;

/**
 * 使用方法
 * @LimitCheck(
 *             prefix = "register:browser:",
 *             identifierSpEL = "#fingerprint",
 *             maxTimes = 3,
 *             expireMinutes = 1440
 *     )
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitCheck {

    /**
     * Redis key 前缀
     */
    String prefix();

    /**
     * 标识符 SpEL 表达式，比如 "#fingerprint" 或 "#request.ip"
     */
    String identifierSpEL();

    /**
     * 最大次数
     */
    int maxTimes();

    /**
     * 有效期（单位：分钟）
     */
    long expireMinutes();
}