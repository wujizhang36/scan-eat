package com.snowflake.basic.client.config;


import com.snowflake.basic.client.handler.LanguageInterceptor;
import com.snowflake.basic.client.handler.LicenseInterceptor;
import com.snowflake.basic.modules.common.handlers.BrowserFingerprintInterceptor;
import jakarta.annotation.Resource;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    LicenseInterceptor licenseInterceptor;

    /**
     * 语言参数名在请求头中，默认为 'locale'。
     */
    private static final String LANGUAGE_PARAM_NAME = LocaleChangeInterceptor.DEFAULT_PARAM_NAME;


    /**
     * 默认区域。
     *
     * @return 区域解析器
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }


    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        // 设置 i18n/language 为基本名称
        source.setBasenames("i18n/language");
        source.setDefaultEncoding("UTF-8");
        return source;
    }


    /**
     * 配置用于验证的本地验证工厂。
     *
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        factoryBean.setValidationMessageSource(this.messageSource());
        return factoryBean;

    }

    /**
     * 区域更改拦截器。
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LanguageInterceptor();
        interceptor.setParamName(LANGUAGE_PARAM_NAME);
        return interceptor;
    }

    /**
     * 注册区域更改拦截器。
     *
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BrowserFingerprintInterceptor());
        registry.addInterceptor(licenseInterceptor)
                .addPathPatterns("/**"); // 拦截所有接口
    }
}
