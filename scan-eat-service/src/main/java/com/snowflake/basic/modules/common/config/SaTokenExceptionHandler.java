package com.snowflake.basic.modules.common.config;

import cn.dev33.satoken.exception.SaTokenException;
import com.snowflake.basic.modules.common.enums.ExceptionMessageEnum;
import com.snowflake.basic.modules.common.handlers.MessageSourceHolder;
import com.snowflake.basic.modules.common.model.ApiResultModel;
import com.snowflake.basic.modules.common.model.helper.ApiResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Slf4j
@ControllerAdvice
public class SaTokenExceptionHandler {

    /**
     * 过期
     * 判断是否是 Token 过期异常
     * 11012 : token 无效
     * 11011 : 未能读取到有效 token
     * 10002 : 未能获取对应StpLogic，type=client
     * 11013 : Token已过期
     */
    private final static List<Integer> expired = Arrays.asList(10002, 11011, 11012, 11013);

    // 登录冲突导致被踢出
    private final static List<Integer> conflict = Arrays.asList(11014);

    /**
     * 判断是否是已被踢下线
     * 11015 : 已被踢下线
     */
    private final static List<Integer> terminated = Arrays.asList(11015);


    // 捕获 SaTokenException 异常
    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<ApiResultModel<String>> handleSaTokenException(SaTokenException e) {
        log.error("SaToken 错误代码:[{}]-{}", e.getCode(), e.getMessage());
        ApiResultModel<String> apiResult = ApiResultHelper.error(e.getCode(), ExceptionMessageEnum.ERROR_000000.getLanguageKey());

        if (expired.contains(e.getCode())) {
            // 您的登录已过期，请重新登录
            String msg = MessageSourceHolder.getMessageSource().getMessage(ExceptionMessageEnum.ERROR_100012.getLanguageKey(), null, new Locale("zh", "CN"));
            msg = msg + " " + MessageSourceHolder.getMessageSource().getMessage(ExceptionMessageEnum.ERROR_100012.getLanguageKey(), null, new Locale("en", "US"));
            apiResult = ApiResultHelper.error(e.getCode(), msg);
            return new ResponseEntity<>(apiResult, HttpStatus.UNAUTHORIZED);
        }

        if (conflict.contains(e.getCode())) {
            // 登录冲突导致被踢出
            String msg = MessageSourceHolder.getMessageSource().getMessage(ExceptionMessageEnum.ERROR_100016.getLanguageKey(), null, new Locale("zh", "CN"));
            apiResult = ApiResultHelper.error(e.getCode(), msg);
            return new ResponseEntity<>(apiResult, HttpStatus.UNAUTHORIZED);
        }

        if (terminated.contains(e.getCode())) { // 已被踢下线
            // 您的登录已过期，请重新登录
            String msg = MessageSourceHolder.getMessageSource().getMessage(ExceptionMessageEnum.ERROR_100013.getLanguageKey(), null, new Locale("zh", "CN"));
            msg = msg + " " + MessageSourceHolder.getMessageSource().getMessage(ExceptionMessageEnum.ERROR_100013.getLanguageKey(), null, new Locale("en", "US"));
            apiResult = ApiResultHelper.error(e.getCode(), msg);
            return new ResponseEntity<>(apiResult, HttpStatus.UNAUTHORIZED);
        }
        // 其他 SaToken 异常的处理
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
