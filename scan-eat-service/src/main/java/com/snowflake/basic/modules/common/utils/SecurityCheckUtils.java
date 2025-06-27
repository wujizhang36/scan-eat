package com.snowflake.basic.modules.common.utils;

import com.snowflake.basic.modules.common.Constant;
import com.snowflake.basic.modules.common.enums.ExceptionMessageEnum;
import com.snowflake.basic.modules.common.enums.I18nEnum;
import com.snowflake.basic.modules.common.exception.BusinessException;
import com.snowflake.basic.modules.common.handlers.BrowserFingerprintInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 安全检查
 */
@Component
public class SecurityCheckUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static StringRedisTemplate staticRedisTemplate;

    @PostConstruct
    public void init() {
        staticRedisTemplate = stringRedisTemplate;
    }


    public static void doLoginTimesCheck(String accountName) {
        // 保存尝试登录次数到 Redis，2 分钟有效，防止无限次尝试登录
        String fingerprint = BrowserFingerprintInterceptor.getCurrentFingerprint().getBrowserFingerprint();
        fingerprint = StringUtils.isBlank(fingerprint) ? accountName : fingerprint;
        String redisKey = Constant.ClientCommonConst.BROWSER_FINGERPRINT_TRY_LOGIN_TOTAL + fingerprint;
        int registerTotal = 0;
        if (Boolean.TRUE.equals(staticRedisTemplate.hasKey(redisKey))) {
            String totalStr = staticRedisTemplate.opsForValue().get(redisKey);
            registerTotal = StringUtils.isNotBlank(totalStr) ? Integer.parseInt(totalStr) : 0;
            registerTotal++;
        }
        if (registerTotal >= 3) { // 当前客户端尝试登录次数超过3次
            throw new BusinessException(ExceptionMessageEnum.ERROR_100014, I18nEnum.BILINGUALISM); // 注册太频繁啦，请休息一下，稍后再试～
        }
        staticRedisTemplate.opsForValue().set(redisKey, registerTotal + "", Duration.ofMinutes(2)); // 2分钟过期
    }
}
