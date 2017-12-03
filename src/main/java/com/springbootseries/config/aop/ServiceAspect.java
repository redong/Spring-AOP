package com.springbootseries.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by rendong on 12/2/17.
 */
@Aspect
@Component
public class ServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("execution(* com.springbootseries.service.*.*(..))")
    public void loggingAspect() {}

    @Before("loggingAspect()")
    public void logParams(final JoinPoint joinPoint) throws Throwable {
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "returnValue", pointcut = "loggingAspect()")
    public void logReturnValue(Object returnValue) throws Throwable {
        logger.info("RESPONSE : " + returnValue);
    }
}
