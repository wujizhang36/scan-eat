package com.snowflake.basic.modules.common.enums;

public enum UserTypeEnum {

    M(0, "MANAGE"),
    C(1, "CLIENT");

    private final int typeCode;
    private final String describe;

    UserTypeEnum(int typeCode, String describe) {
        this.typeCode = typeCode;
        this.describe = describe;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getDescribe() {
        return describe;
    }
}
