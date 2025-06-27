/*
 * @description
 * @author System
 * @version 1.0
 */

package com.snowflake.basic.modules.common.exception;

import com.snowflake.basic.modules.common.enums.ExceptionMessageEnum;
import com.snowflake.basic.modules.common.enums.I18nEnum;
import com.snowflake.basic.modules.common.handlers.MessageSourceHolder;
import com.snowflake.basic.modules.common.utils.WebUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author System
 * @description 业务处理异常
 */
@Getter
@Setter
@Slf4j
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected Integer errorCode;

    protected String errorKey;
    protected String errorMsg;

    protected ExceptionMessageEnum exceptionMessageEnum;

    public BusinessException() {
        super();
    }

    public BusinessException(SystemErrorInfoInterface systemErrorInfoInterface) {
        super(systemErrorInfoInterface.getMessage());
        this.errorCode = systemErrorInfoInterface.getCode();
        this.errorMsg = systemErrorInfoInterface.getMessage();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = 500;
        this.errorMsg = errorMsg;
    }

    // 用于处理国际化错误信息
    public BusinessException(MessageSource messageSource, ExceptionMessageEnum exceptionMessageEnum) {
        super(messageSource.getMessage(exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale()));
        this.errorMsg = messageSource.getMessage(exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale());
        this.errorCode = exceptionMessageEnum.getErrorCode();
    }

    public BusinessException(ExceptionMessageEnum exceptionMessageEnum) {
        super(MessageSourceHolder.getMessageSource().getMessage(
                exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale()));
        this.errorMsg = MessageSourceHolder.getMessageSource().getMessage(
                exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale());
        this.errorCode = exceptionMessageEnum.getErrorCode();
        this.exceptionMessageEnum = exceptionMessageEnum;
    }

    // 双语中异常信息
    public BusinessException(ExceptionMessageEnum exceptionMessageEnum, I18nEnum i18n) {
        super(MessageSourceHolder.getMessageSource().getMessage(
                exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale()));
        if (!i18n.equals(i18n.MONOLINGUALISM)) {
            this.errorMsg = MessageSourceHolder.getMessageSource().getMessage(
                    exceptionMessageEnum.getLanguageKey(), null, new Locale("zh", "CN"));
            errorMsg = errorMsg + " " + MessageSourceHolder.getMessageSource().getMessage(
                    exceptionMessageEnum.getLanguageKey(), null, new Locale("en", "US"));
        } else {
            this.errorMsg = MessageSourceHolder.getMessageSource().getMessage(
                    exceptionMessageEnum.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale());
        }
        this.errorCode = exceptionMessageEnum.getErrorCode();
        this.exceptionMessageEnum = exceptionMessageEnum;
    }

    public BusinessException(String errorMsg, ExceptionMessageEnum exceptionMessageEnum) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.exceptionMessageEnum = exceptionMessageEnum;
    }


    public BusinessException(Integer errorCode, String errorMsg) {
        super(String.join(":", errorCode.toString(), errorMsg));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg, ExceptionMessageEnum exceptionMessageEnum) {
        super(String.join(":", errorCode.toString(), errorMsg));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.exceptionMessageEnum = exceptionMessageEnum;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
