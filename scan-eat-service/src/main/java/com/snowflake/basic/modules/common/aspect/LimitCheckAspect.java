package com.snowflake.basic.modules.common.aspect;

import com.snowflake.basic.modules.common.annotation.LimitCheck;
import com.snowflake.basic.modules.common.enums.ExceptionMessageEnum;
import com.snowflake.basic.modules.common.enums.I18nEnum;
import com.snowflake.basic.modules.common.exception.BusinessException;
import com.snowflake.basic.modules.common.handlers.LimitCheckService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LimitCheckAspect {

    @Autowired
    private LimitCheckService limitCheckService;

    @Autowired
    private SpelExpressionParser parser;

    // 获取 HttpServletRequest
    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(limitCheck)")
    public Object around(ProceedingJoinPoint joinPoint, LimitCheck limitCheck) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 创建方法的 SpEL 上下文
        EvaluationContext context = new MethodBasedEvaluationContext(
                joinPoint.getTarget(),
                method,
                joinPoint.getArgs(),
                new DefaultParameterNameDiscoverer()
        );

        // 将 HttpServletRequest 注入到 SpEL 上下文中
        context.setVariable("request", request);

        // 获取 SpEL 表达式值
        Expression exp = parser.parseExpression(limitCheck.identifierSpEL());
        String identifier = exp.getValue(context, String.class);

        String redisKey = limitCheck.prefix() + identifier;

        // 执行限流逻辑
        limitCheckService.check(redisKey, limitCheck.maxTimes(), limitCheck.expireMinutes(),
                () -> new BusinessException(ExceptionMessageEnum.ERROR_100015, I18nEnum.BILINGUALISM)); // 操作太频繁啦，请休息一下，稍后再试～

        return joinPoint.proceed();
    }
}

