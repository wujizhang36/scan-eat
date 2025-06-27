package com.snowflake.basic.modules.common.bootstrapping.flyweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTenant implements ITenant {

    protected final Long tenantId; // 租户id

    protected volatile String name; // 租户名称
    protected volatile ITenantDataDictionary tenantDataDictionary; // 当前租户的数据字典
    protected final List<ITenant> subITenants = Collections.synchronizedList(new ArrayList<>());  // 子租户列表
    protected volatile ITenant parentITenant; // 父租户

    public AbstractTenant(Long tenantId, String name, ITenantDataDictionary tenantDataDictionary, ITenant parentITenant) {
        this.tenantId = tenantId;
        this.name = name;
        this.tenantDataDictionary = tenantDataDictionary;
        this.parentITenant = parentITenant;
    }

    @Override
    public void addSubTenant(ITenant ITenant) {
        subITenants.add(ITenant); // 添加子租户到列表
    }
}
