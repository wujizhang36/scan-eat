package com.snowflake.basic.modules.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.snowflake.basic.modules.common.Constant;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 配置Jackson的序列化/反序列化策略 # 全局配置(MvcConfiguration)
 */
//@Configuration
public class JacksonConfiguration {
//    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_TIME_FORMAT)));

        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(Constant.DateTime.DEFAULT_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
        return objectMapper;
    }
}