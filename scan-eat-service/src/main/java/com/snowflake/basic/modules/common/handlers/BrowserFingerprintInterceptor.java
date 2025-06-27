package com.snowflake.basic.modules.common.handlers;

import com.snowflake.basic.modules.common.BrowserFingerprint;
import com.snowflake.basic.modules.common.utils.BrowserFingerprintUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class BrowserFingerprintInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<BrowserFingerprint> context = new ThreadLocal<>();

    public static BrowserFingerprint getCurrentFingerprint() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        BrowserFingerprint fingerprint = BrowserFingerprintUtils.resolve(request);
        context.set(fingerprint);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        clear();
    }
}