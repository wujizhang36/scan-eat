package com.snowflake.basic.modules.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * SpEL 解析器配置
 */
@Configuration
public class SpelConfig {
    @Bean
    public SpelExpressionParser spelExpressionParser() {
        return new SpelExpressionParser();
    }
}
