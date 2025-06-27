package com.snowflake.basic.modules.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
    private static ObjectMapper instance;

    private JacksonUtils() {}

    public static ObjectMapper getInstance() {
        if (instance == null) {
            synchronized (JacksonUtils.class) {
                if (instance == null) {
                    instance = new ObjectMapper();
                }
            }
        }
        return instance;
    }
}
