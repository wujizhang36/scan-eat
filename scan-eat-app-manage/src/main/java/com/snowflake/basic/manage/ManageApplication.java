package com.snowflake.basic.manage;

import cn.dev33.satoken.SaManager;
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
@MapperScan(basePackages = {"com.snowflake.basic.modules.client.mapper","com.snowflake.basic.modules.manage.mapper"})
public class ManageApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(ManageApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ManageApplication.class);
    }
}
