package com.snowflake.basic.client.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 全局拦截器 - 国际化
 *
 *  
 * @date 2025/2/2
 **/
@Slf4j
public class LanguageInterceptor extends LocaleChangeInterceptor {

    /**
     * 在尝试从请求头获取区域信息，如果不存在则从请求参数获取。
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器
     * @return 操作是否成功
     * @throws ServletException ServletException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws ServletException {
        // 从请求头获取新的区域信息
        String newLocale = request.getHeader(getParamName());
        if (StringUtils.isBlank(newLocale)) {
            // 如果请求头中不存在区域信息，则调用父类的 preHandle 方法
            return super.preHandle(request, response, handler);
        }
        // 检查请求方法是否符合配置的HTTP方法
        if (checkHttpMethods(request.getMethod())) {
            // 获取当前请求的区域解析器
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("未找到 LocaleResolver：可能不是 DispatcherServlet 请求？");
            }
            try {
                // 尝试设置新的区域信息到区域解析器中
                localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
            } catch (IllegalArgumentException ex) {
                // 如果区域信息值无效，并且忽略无效区域信息为 true，则打印调试信息；否则抛出异常
                if (isIgnoreInvalidLocale()) {
                    log.debug("忽略无效的区域信息值 [" + newLocale + "]：" + ex.getMessage());
                } else {
                    throw ex;
                }
            }
        }
        return true;
    }

    /**
     * 检查当前请求方法是否符合配置的HTTP方法。
     *
     * @param currentMethod 当前请求方法
     * @return 是否符合配置的HTTP方法
     */
    private boolean checkHttpMethods(String currentMethod) {
        // 获取配置的HTTP方法
        String[] configuredMethods = getHttpMethods();
        // 如果没有配置，返回true
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        }
        // 检查当前请求方法是否在配置的HTTP方法中
        for (String configuredMethod : configuredMethods) {
            if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }
}
