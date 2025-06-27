package com.snowflake.basic.modules.common.bootstrapping.filter;

import com.snowflake.basic.modules.common.bootstrapping.context.TenantContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 租户过滤器，用于在请求中提取租户ID
 */
@Component
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 从请求参数中获取租户ID
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String tenantId = httpRequest.getHeader("X-Tenant-ID"); // 从请求头获取租户ID
        if (tenantId != null) {
            // 设置当前租户ID
            TenantContext.setCurrentTenant(Long.parseLong(tenantId));
        }
        try {
            // 继续请求处理
            chain.doFilter(request, response);
        } finally {
            // 清除当前租户ID,确保每个请求的线程安全和数据隔离。
            TenantContext.clear();
        }
    }
}