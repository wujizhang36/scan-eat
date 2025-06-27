package com.snowflake.basic.modules.common.enums;

public enum ActivityTypeEnum {
    PET("PET"), // 狗
    KENNEL("KENNEL"); // 犬舍

    private final String typeName;

    ActivityTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

}
