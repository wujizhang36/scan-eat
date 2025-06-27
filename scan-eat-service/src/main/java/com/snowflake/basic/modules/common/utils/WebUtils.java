package com.snowflake.basic.modules.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

public class WebUtils {

    private static final int LANGUAGE_PART_INDEX = 0;
    private static final int LOCALE_PART_INDEX = 1;
    private static final String LOCALE_DELIMITER = "[-_]";

    /**
     * 获取请求的 Accept-Language 值。
     *
     * @return Accept-Language 值
     */
    public static String getAcceptLanguage() {
        HttpServletRequest request = getCurrentRequest();
        if (request != null) {
            return request.getHeader("Accept-Language");
        }
        return null;
    }

    /**
     * 获取当前请求对象。
     *
     * @return HttpServletRequest 对象
     */
    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 解析 Accept-Language 值为 Locale 对象。
     *
     * @return Locale 对象
     */
    public static Locale parseAcceptLanguageToLocale() {
        String acceptLanguage = getAcceptLanguage();
        if (acceptLanguage != null) {
            String[] languageParts = acceptLanguage.split(",");
            if (languageParts.length > LANGUAGE_PART_INDEX) {
                String[] localeParts = languageParts[LANGUAGE_PART_INDEX].split(LOCALE_DELIMITER);
                if (localeParts.length > LOCALE_PART_INDEX) {
                    return new Locale(localeParts[0], localeParts[LOCALE_PART_INDEX]);
                } else if (localeParts.length > 0) {
                    return new Locale(localeParts[0]);
                }
            }
        }
        return Locale.getDefault();
    }
}

