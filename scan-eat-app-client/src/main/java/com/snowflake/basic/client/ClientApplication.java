package com.snowflake.basic.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.snowflake.basic"})
//@MapperScan(basePackages = {"com.snowflake.basic.modules.client.mapper", "com.snowflake.basic.modules.manage.mapper"})
public class ClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ClientApplication.class);
    }
}
