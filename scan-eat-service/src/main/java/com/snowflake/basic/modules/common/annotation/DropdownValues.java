/*
 * @description
 * @version 1.0
 */

package com.snowflake.basic.modules.common.annotation;

import java.lang.annotation.*;

/**
 * @description 下拉注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DropdownValues {

    /**
     * @description 字符串类型
     */
    String[] value() default {};

    /**
     * @description 数字类型
     */
    int[] numerical() default {};

    /**
     * @description 支持范型对象数组
     */
    Class<?>[] generics() default {};

    /**
     * @description 下拉集合
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        DropdownValues[] value();
    }
}
