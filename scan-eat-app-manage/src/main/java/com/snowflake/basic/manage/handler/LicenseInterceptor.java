package com.snowflake.basic.manage.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 许可认证
 *
 * @date 2025/2/6
 **/
@Slf4j
@Component
public class LicenseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在请求处理之前进行拦截操作
        // 这里可以进行许可验证等操作
        // 如果验证通过，返回 true，允许请求继续执行；否则返回 false，中断请求
        log.info("LicenseInterceptor:preHandle:" + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 请求处理之后但是视图渲染之前调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 整个请求处理完毕后调用，即视图渲染完毕后调用
    }
}
