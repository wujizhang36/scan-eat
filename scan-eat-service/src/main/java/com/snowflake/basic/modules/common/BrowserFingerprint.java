package com.snowflake.basic.modules.common;

import lombok.Data;

/**
 * 浏览器指纹信息对象，用于收集和存储客户端识别信息
 */
@Data
public class BrowserFingerprint {

    /**
     * 浏览器指纹ID（由前端如 FingerprintJS 生成）
     */
    private String browserFingerprint; // fingerprintId

    /**
     * 客户端 IP 地址
     */
    private String ip;

    /**
     * 浏览器的 User-Agent 信息
     */
    private String userAgent;

    /**
     * 浏览器的语言设置（Accept-Language）
     */
    private String language;

    /**
     * 请求来源地址（Referer）
     */
    private String referer;

    // 构造方法
    public BrowserFingerprint() {
    }

    public BrowserFingerprint(String fingerprintId, String ip, String userAgent, String language, String referer) {
        this.browserFingerprint = fingerprintId;
        this.ip = ip;
        this.userAgent = userAgent;
        this.language = language;
        this.referer = referer;
    }
}