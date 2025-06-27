package com.snowflake.basic.modules.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * 国际化
 *
 * @date 2025/2/2
 **/
@Component
public class LanguageUtil {

    private static LocaleResolver locale;

    private final LocaleResolver localeResolver;

    public LanguageUtil(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
        this.locale = localeResolver; //NOPMD - suppressed AssignmentToNonFinalStatic - TODO explain reason for suppression
    }

    /**
     * 获取当前请求的区域语言
     *
     * @return 当前区域语言
     */
    public static Locale getCurrentLanguage() {
        // 从请求上下文中获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return locale.resolveLocale(attributes.getRequest());
        } else {
            return Locale.getDefault();
        }
    }

}
