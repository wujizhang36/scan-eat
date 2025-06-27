package com.snowflake.basic.modules.common.bootstrapping;

import com.snowflake.basic.modules.common.bootstrapping.flyweight.ITenantDataDictionary;

public interface TenantDataDictionaryService {

    // 模拟从数据库获取配置的方法
    ITenantDataDictionary fetchSettingsFromDatabase(Long tenantId);
}
