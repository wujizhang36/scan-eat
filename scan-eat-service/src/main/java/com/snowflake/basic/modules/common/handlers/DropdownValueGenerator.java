/*
 * @description
 * @version 1.0
 */

package com.snowflake.basic.modules.common.handlers;

import com.snowflake.basic.modules.common.annotation.DropdownValues;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  
 * @description 下拉值 生成器
 */
@Component
public class DropdownValueGenerator<T> {

    public Map<String, List<?>> generateDropdownValues(T entity) {
        Map<String, List<?>> response = new ConcurrentHashMap<>();

        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(DropdownValues.class)) {
                continue;
            }
            DropdownValues dropdownAnnotation = field.getAnnotation(DropdownValues.class);
            String[] dropdownValues = dropdownAnnotation.value();
            int[] numericalValues = dropdownAnnotation.numerical();
            Class<?>[] genericsValues = dropdownAnnotation.generics();
            String fieldName = field.getName();
            if (dropdownValues.length > 0) {
                // 在这里生成你需要的临时变量
                // 示例：生成一个字符串类型的临时变量
                List<?> list = Arrays.asList(dropdownValues);
                response.put(fieldName, list);
            } else if (numericalValues.length > 0) {
                response.put(fieldName, Arrays.asList(numericalValues));
            } else {
                List<Object> list = new ArrayList<>();
                for (Class<?> genericValue : genericsValues) {
                    list.add(enumToMap(genericValue));
                }
                // 将字段名和类型数组添加到响应中
                response.put(fieldName, list);
            }

        }

        return response;
    }

    /**
     * @description 枚举转map
     *  
     */
    private List<Object> enumToMap(Class<?> enumClass) {
        LinkedHashMap <String, Object> enumMap = new LinkedHashMap<>();
        @SuppressWarnings("unchecked")
        Enum<?>[] enumConstants = (Enum<?>[]) enumClass.getEnumConstants();
        for (Enum<?> enumConstant : enumConstants) {
            Map<String, Object> temp = beanToMap(enumConstant);
            if (temp.containsKey("declaringClass")) {
                temp.remove("declaringClass");
            }
            enumMap.put(enumConstant.name(), temp);
        }
        List<Object> resultList = new ArrayList<>();
        if (!enumMap.isEmpty()) {
            enumMap.forEach((k, v) -> {
                resultList.add(v);
            });
        }
        return resultList;
    }

    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> transMaps = new ConcurrentHashMap<>();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.keySet().forEach(it -> transMaps.put(String.valueOf(it), beanMap.get(it)));
        return transMaps;
    }

}
