package com.snowflake.basic.manage.handler;

import com.snowflake.basic.modules.common.config.SaTokenExceptionHandler;
import com.snowflake.basic.modules.common.enums.ExceptionMessageEnum;
import com.snowflake.basic.modules.common.exception.BusinessException;
import com.snowflake.basic.modules.common.model.ApiResultModel;
import com.snowflake.basic.modules.common.model.helper.ApiResultHelper;
import com.snowflake.basic.modules.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 *
 * @date 2025/2/3
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends SaTokenExceptionHandler {


    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 处理自定义异常 BusinessException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResultModel<String>> handleCustomException(BusinessException ex) {
        // 日志记录器
        log.error("[{}]-{}", ex.getErrorCode(), ex.getMessage());
        // 创建 ApiResultModel 对象
        ApiResultModel<String> apiResult = ApiResultHelper.error(ex.getErrorCode(), ex.getMessage());
        // 返回带有错误消息和状态码的 ResponseEntity
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResultModel<String>> handleSystemException(Exception ex) {
        log.error("{}", ex);
        // 创建 ApiResultModel 对象
        ApiResultModel<String> apiResult = ApiResultHelper.error(ExceptionMessageEnum.ERROR_000000.getErrorCode(), messageSource.getMessage(ExceptionMessageEnum.ERROR_000000.getLanguageKey(), null, WebUtils.parseAcceptLanguageToLocale()));
        // 返回带有错误消息和状态码的 ResponseEntity
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}