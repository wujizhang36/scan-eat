package com.snowflake.basic.modules.common.handlers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceHolder implements ApplicationContextAware {

    private static MessageSource messageSource;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        MessageSourceHolder.messageSource = applicationContext.getBean(MessageSource.class);
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }
}
