package com.snowflake.basic.modules.common.config;

import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class DruidConfig {

//    @Bean
//    public ServletRegistrationBean<StatViewServlet> druidServlet() {
//        ServletRegistrationBean<StatViewServlet> bean =
//                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//        // 登录账号密码（默认页面需要登录）
//        bean.addInitParameter("loginUsername", "admin");
//        bean.addInitParameter("loginPassword", "123456");
//
//        // 允许访问的 IP（为空或设置为“127.0.0.1”）
//        bean.addInitParameter("allow", ""); // 所有都允许
//        // 拒绝访问的 IP
//        // bean.addInitParameter("deny", "192.168.1.100");
//
//        return bean;
//    }

//    @Bean
//    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
//        FilterRegistrationBean<WebStatFilter> bean =
//                new FilterRegistrationBean<>(new WebStatFilter());
//
//        bean.setUrlPatterns(Collections.singletonList("/*"));
//        bean.addInitParameter("exclusions",
//                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//
//        return bean;
//    }
}