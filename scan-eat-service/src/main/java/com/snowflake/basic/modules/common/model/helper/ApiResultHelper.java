/*
 * @description
 * @author System
 * @version 1.0
 */

package com.snowflake.basic.modules.common.model.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowflake.basic.modules.common.annotation.DropdownValues;
import com.snowflake.basic.modules.common.handlers.DropdownValueGenerator;
import com.snowflake.basic.modules.common.model.ApiResultModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口结果返回封装帮助工具类
 *
 * <p>
 * 该工具类操作的实体类是：{@link ApiResultModel}
 * </p>
 *
 * <p>
 * 我们提供了如下三个方法：
 * </p>
 *
 * <ul>
 * <li>success(S code, String message, T data)：成功，并返回的数据的方法</li>
 * <li>success(S code, String message)：成功，不返回数据</li>
 * <li>error(S code, String message)：失败</li>
 * </ul>
 *
 */
public class ApiResultHelper {

    /**
     * 成功，有数据返回，默认返回code为200
     *
     * @param data 数据
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static <T> ApiResultModel<T> success(T data) {
        return new ApiResultModel<>(HttpStatus.OK.value(), Boolean.TRUE, null, null, data);
    }

    // 适用于接收 ResponseEntity
    public static <T> ApiResultModel success(HttpStatusCode status, ResponseEntity responseEntity) {
        try {
            Object responseEntityError = "";
            if (ObjectUtils.isNotEmpty(responseEntity.getBody())) {
                ObjectMapper objectMapper = new ObjectMapper();
                // 尝试将输入数据解析为 Map 对象
                Map<String, Object> jsonMap = objectMapper.readValue(responseEntity.getBody() + "", Map.class);
                if (jsonMap.containsKey("Error")) {
                    responseEntityError = jsonMap.get("Error");
                }
            }
            if (ObjectUtils.isEmpty(responseEntityError)) {
                return new ApiResultModel(status.value(), status.isError(), responseEntityError + "", null, responseEntity);
            }
            return new ApiResultModel(status.value(), status.isError(), responseEntityError + "", null, null);
        } catch (JsonProcessingException e) {
            return new ApiResultModel(status.value(), status.isError(), "", null, responseEntity);
//            throw new RuntimeException(e);
        }
    }

    /**
     * @param data                   集合数据
     * @param dropdownValueGenerator 下拉值生成器
     * @param <T>                    数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     * @description 成功，带有下拉值的数据返回，默认返回code为200
     */
    public static <T> ApiResultModel<List<T>> success(List<T> data, DropdownValueGenerator dropdownValueGenerator) {
        // 创建一个用于存储带有下拉值的数据的列表
//        List<T> processedData = new ArrayList<>();
        // 遍历原始数据列表中的每个元素
//        for (T item : data) {
//            // 生成带有下拉值的新对象
//            T processedItem = generateDropdownValues(item, dropdownValueGenerator);
//            // 将新对象添加到带有下拉值的数据列表中
//            processedData.add(processedItem);
//        }
        Map<String, Object> dropdownValues = new HashMap<>();
        if (data.size() > 0) {
            dropdownValues.putAll(dropdownValueGenerator.generateDropdownValues(data.get(0)));
        }
        // 返回带有下拉值的数据列表
        return new ApiResultModel<>(HttpStatus.OK.value(), Boolean.TRUE, null, dropdownValues, data);
    }

    /**
     * @param data                   对象数据
     * @param dropdownValueGenerator 下拉值生成器
     * @param <T>                    数据类型
     * @return 返回结果封装 {@link ApiResultModel}
     * @description 成功，带有下拉值的数据返回，默认返回code为200
     */
    public static <T> ApiResultModel<T> success(T data, DropdownValueGenerator dropdownValueGenerator) {
        Map<String, Object> dropdownValues = new HashMap<>();
        if (ObjectUtils.isNotEmpty(data)) {
            dropdownValues.putAll(dropdownValueGenerator.generateDropdownValues(data));
        }
        // 返回带有下拉值的数据列表
        return new ApiResultModel<>(HttpStatus.OK.value(), Boolean.TRUE, null, dropdownValues, data);
    }

    /**
     * 成功，无数据返回，默认返回code为200
     *
     * @return 返回结果封装 {@link ApiResultModel}
     */
    public static ApiResultModel success() {
        return new ApiResultModel<>(HttpStatus.OK.value(), Boolean.TRUE, null, null, null);
    }


    public static ApiResultModel success(String message) {
        return new ApiResultModel<>(HttpStatus.OK.value(), Boolean.TRUE, message, null, null);
    }


    public static ApiResultModel error(Integer code, String message) {
        return new ApiResultModel<>(code, Boolean.FALSE, message, null, null);
    }

    /**
     * 生成带有下拉值的数据对象
     *
     * @param item                   原始数据对象
     * @param dropdownValueGenerator 下拉值生成器
     * @param <T>                    数据类型
     * @return 带有下拉值的数据对象
     */
    private static <T> T generateDropdownValues(T item, DropdownValueGenerator dropdownValueGenerator) {
        // 使用反射来获取对象的类
        Class<?> clazz = item.getClass();
        // 使用反射来获取对象的所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        // 创建一个 Map 用于存储下拉值
        Map<String, Object> dropdownValues = new HashMap<>();
        // 遍历成员变量，查找带有 @DropdownValues 注解的字段
        for (Field field : fields) {
            if (field.isAnnotationPresent(DropdownValues.class)) {
                // 获取字段的名称
                String fieldName = field.getName();
                // 使用 DropdownValueGenerator 来生成下拉值
                dropdownValues.putAll(dropdownValueGenerator.generateDropdownValues(item));
            }
        }

        // 根据业务逻辑将下拉值设置到原始对象中
        // 将存储下拉值的 Map 设置到对象中
        try {
            // 获取对象的 setter 方法
            Method setterMethod = clazz.getMethod("setDropdownValues", Map.class); // 假设有一个名为 setDropdownValues 的方法
            // 调用 setter 方法来设置下拉值
            setterMethod.invoke(item, dropdownValues);
        } catch (Exception e) {
            // 处理异常
        }

        // 返回带有下拉值的数据对象
        return item;
    }

}
