package com.snowflake.basic.modules.common.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.function.Supplier;

@Service
public class LimitCheckService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void check(String redisKey, int maxTimes, long expireMinutes, Supplier<RuntimeException> exceptionSupplier) {
        int count = 0;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            String val = redisTemplate.opsForValue().get(redisKey);
            count = StringUtils.hasText(val) ? Integer.parseInt(val) : 0;
            count++;
        }

        if (count >= maxTimes) {
            throw exceptionSupplier.get();
        }

        redisTemplate.opsForValue().set(redisKey, String.valueOf(count), Duration.ofMinutes(expireMinutes));
    }
}