/*
 * @description
 * @version 1.0
 */

package com.snowflake.basic.modules.common.config;

import com.snowflake.basic.modules.common.handlers.DropdownValuesScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  
 * @description 执行扫描并获取带有@DropdownValues注解的字段
 */
@Configuration
public class DropdownValuesScannerConfig {
    @Value("com.snowflake.*")
    private String basePackage;
    @Bean
    public DropdownValuesScanner dropdownValuesScanner() {
        return new DropdownValuesScanner(basePackage);
    }

}
