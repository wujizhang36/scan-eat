package com.snowflake.basic.modules.common.bootstrapping;

import com.snowflake.basic.modules.common.bootstrapping.flyweight.AbstractTenant;
import com.snowflake.basic.modules.common.bootstrapping.flyweight.ITenant;
import com.snowflake.basic.modules.common.bootstrapping.flyweight.ITenantDataDictionary;

public class ConcreteTenant extends AbstractTenant {

    protected volatile ITenantDataDictionary tenantDataDictionary;

    public ConcreteTenant(Long tenantId, String name, ITenantDataDictionary tenantDataDictionary, ITenant parentTenant) {
        super(tenantId, name, tenantDataDictionary, parentTenant);
        this.tenantDataDictionary = tenantDataDictionary;
    }

    @Override
    public ITenantDataDictionary getDataDictionary() {
        return tenantDataDictionary;
    }

    @Override
    public ITenantDataDictionary getEffectiveDataDictionary() {
        return null;
    }
}