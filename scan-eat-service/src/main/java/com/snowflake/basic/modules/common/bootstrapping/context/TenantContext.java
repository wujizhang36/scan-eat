package com.snowflake.basic.modules.common.bootstrapping.context;

/**
 * 租户上下文，用于存储当前线程的租户信息
 */
public class TenantContext {
    private static final ThreadLocal<Long> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(Long tenantId) {
        currentTenant.set(tenantId);
    }

    public static Long getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}