package com.snowflake.basic.modules.common.enums;

public enum ActiveEnum {
    DISABLE(0, "DISABLE"), // 禁用
    ENABLE(1, "ENABLE"); // 启用

    private final int value;
    private final String describe;

    ActiveEnum(int value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public int getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }
}
