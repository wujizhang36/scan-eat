/*
 * @description
 * @version 1.0
 */

package com.snowflake.basic.modules.common.handlers;

import com.snowflake.basic.modules.common.annotation.DropdownValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DropdownValuesScanner {
    private String basePackage;

    public DropdownValuesScanner(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * @return 包含带有@DropdownValues注解的类名称的列表
     * @throws ClassNotFoundException 如果找不到类
     * @description 扫描指定包中的类，检查是否带有@DropdownValues注解，如果有，创建临时成员变量。
     *  
     */
    public List<String> scanDropdownValues() throws ClassNotFoundException, NoSuchFieldException {
        List<String> dropdownValuesList = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.setResourceLoader(new PathMatchingResourcePatternResolver());
        scanner.addIncludeFilter(new AssignableTypeFilter(Object.class) {
            @Override
            protected boolean matchClassName(String className) {
                try {
                    Class<?> clazz = Class.forName(className);
                    return hasDropdownValuesAnnotation(clazz);
                } catch (ClassNotFoundException e) {
                    // 处理类未找到异常
                }
                return false;
            }
        });

        // 遍历所有候选组件，查找带有@DropdownValues注解的类
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            Class<?> clazz = Class.forName(bd.getBeanClassName());
            if (hasDropdownValuesAnnotation(clazz)) {
                // 获取类中带有@DropdownValues注解的成员变量
                List<Field> dropdownFields = findDropdownFields(clazz);

                // 在类中创建临时成员变量
                for (Field field : dropdownFields) {
                    createTempField(clazz, field);
                }

                // 添加类名到结果列表
                dropdownValuesList.add(clazz.getName());
            }
        }

        return dropdownValuesList;
    }

    /**
     * @param clazz 要检查的类
     * @return 如果类带有@DropdownValues注解，返回true；否则返回false
     * @description 检查类是否带有@DropdownValues注解。
     *  
     */
    private boolean hasDropdownValuesAnnotation(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DropdownValues.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param clazz 要查找的类
     * @return 带有@DropdownValues注解的成员变量列表
     * @description 查找带有@DropdownValues注解的成员变量。
     *  
     */
    private List<Field> findDropdownFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> dropdownFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DropdownValues.class)) {
                dropdownFields.add(field);
            }
        }
        return dropdownFields;
    }

    /**
     * 在类中创建临时成员变量，用于处理@DropdownValues注解的字段。
     *
     * @param clazz 要创建临时成员变量的类
     * @param field 带有@DropdownValues注解的字段
     */
    private void createTempField(Class<?> clazz, Field field) throws NoSuchFieldException {
        // 在clazz中创建临时成员变量，不进行数据库映射
        // 例如，创建一个名为field.getName() + "Dropdown"的临时成员变量
        // 这里需要根据需求来创建和管理临时成员变量

//        // 获取字段的名称
//        String fieldName = field.getName();
//
//        // 构造临时字段的名称，例如，在字段名后面添加 "Dropdown" 后缀
//        String tempFieldName = fieldName + "Dropdown";
//
//        // 使用反射在类中创建一个新的临时字段
//        Field tempField = clazz.getDeclaredField(tempFieldName);
//
//        // 设置临时字段的可访问性，以允许修改它
//        tempField.setAccessible(true);
//
//        // 在对象中为临时字段设置一个默认值，例如，可以是 null
//        // 这里假设你的类有一个无参构造函数，你可以根据你的需求来初始化默认值
//        try {
//            Object instance = clazz.getDeclaredConstructor().newInstance();
//            tempField.set(instance, null); // 设置临时字段的值为 null
//        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException e) {
//            // 处理异常
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }
}
