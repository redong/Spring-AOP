package com.springbootseries.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rendong on 12/2/17.
 */
@Aspect
@Component
public class ServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.springbootseries.service.*.*(..))")
    public void logging() {

    }

    @Around("logging()")
    public Object logParams(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        Object[] args = proceedingJoinPoint.getArgs();
        List<String> argsList = new ArrayList<>();

        for (Object arg: args) {
            argsList.add(arg.toString());
        }

        logger.info(signature + "\n" + " ( " + String.join(",", argsList) + " ) ");

        return proceedingJoinPoint.proceed();
    }
}
