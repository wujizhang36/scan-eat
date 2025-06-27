package com.snowflake.basic.modules.common.utils;

import com.snowflake.basic.modules.common.BrowserFingerprint;
import jakarta.servlet.http.HttpServletRequest;

public class BrowserFingerprintUtils {

    public static BrowserFingerprint resolve(HttpServletRequest request) {
        BrowserFingerprint fingerprint = new BrowserFingerprint();

        fingerprint.setIp(getClientIp(request));
        fingerprint.setUserAgent(request.getHeader("User-Agent"));
        fingerprint.setLanguage(request.getHeader("Accept-Language"));
        fingerprint.setReferer(request.getHeader("Referer"));

        // 如果前端传了 fingerprintId（建议用 JS 采集后传来）
        String fid = request.getHeader("X-Fingerprint-ID");
        if (fid == null || fid.isEmpty()) {
            fid = request.getParameter("browserFingerprint");// "fingerprintId"
        }
        fingerprint.setBrowserFingerprint(fid);

        return fingerprint;
    }

    private static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
