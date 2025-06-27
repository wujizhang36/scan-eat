package com.snowflake.basic.modules.common.bootstrapping.flyweight;

/**
 * 租户接口，定义租户的基本操作
 *
 * <p>可以在这里添加类的详细描述，包括如何使用该类。</p>
 */
public interface ITenant {
    void addSubTenant(ITenant ITenant); // 添加子租户

    ITenantDataDictionary getDataDictionary(); // 获取当前租户设置

    ITenantDataDictionary getEffectiveDataDictionary(); // 获取有效的设置（包括继承的设置）
}
