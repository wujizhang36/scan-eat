package com.snowflake.basic.modules.common.bootstrapping;

import com.snowflake.basic.modules.common.bootstrapping.flyweight.AbstractTenant;
import com.snowflake.basic.modules.common.bootstrapping.flyweight.ITenantDataDictionary;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本类只用于多租户数据处理
 *
 * <p>主要功能包括：</p>
 * <ul>
 *     <li>配置层级关系</li>
 *     <li>报表等</li>
 * </ul>
 *
 * <p>使用示例：</p>
 * <pre>
 * tenantFlyweightHandler.getTenant(TenantContext.getCurrentTenant()).getDataDictionary().getDataDictionary(key);
 * </pre>
 */
@Component
public class TenantFlyweightHandler {

    private final Map<Long, AbstractTenant> tenantCache = new ConcurrentHashMap<>();
    //    private final TenantDataDictionaryService tenantDataDictionaryService;
//
//    public TenantFlyweightHandler(TenantDataDictionaryService tenantDataDictionaryService) {
//        this.tenantDataDictionaryService = tenantDataDictionaryService;
//    }

    // 获取租户配置，如果不存在则从数据库加载
    public AbstractTenant getTenant(Long tenantId) {
        return tenantCache.computeIfAbsent(tenantId, this::loadTenantFromDatabase);
    }

    // 模拟从数据库加载租户的方法
    private AbstractTenant loadTenantFromDatabase(Long tenantId) {
        // 模拟从数据库获取租户的设置,目前只需要加载平台基础配置
        ITenantDataDictionary dataDictionary = null; //tenantDataDictionaryService.fetchSettingsFromDatabase(tenantId);
        return new ConcreteTenant(tenantId, "Tenant " + tenantId, dataDictionary, null); // 模拟创建具体租户实例
    }

}
