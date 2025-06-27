package com.snowflake.basic.modules.common.bootstrapping.flyweight;

import com.snowflake.basic.modules.common.pojo.TenantDataDictionary;

/**
 * 具体享元类。
 *
 * <p>租户设置类，封装租户的具体设置属性。</p>
 *
 */
public interface ITenantDataDictionary{
    /**
     * 获取配置值
     *
     * @param key
     * @return
     */
    String getDataDictionaryByKey(String key);

    TenantDataDictionary getDataDictionaryById(Long id);

    String getDemo(String str);
}
