package com.springbootseries.config.aop;

import com.springbootseries.exception.DemoException;
import com.springbootseries.vo.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by rendong on 12/2/17.
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Around("execution(public com.springbootseries.vo.ResultBean *(..))")
    public ResultBean loggingAspect(final ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) proceedingJoinPoint.proceed();
            logger.info("TIME: " + (System.currentTimeMillis() - startTime));
            logger.info("RESPONSE: " + result);
        } catch (Throwable e) {
            result = exceptionHandler(proceedingJoinPoint, e);
        }

        return result;
    }

    private ResultBean<?> exceptionHandler(ProceedingJoinPoint proceedingJoinPoint, Throwable e) {
        ResultBean<?> result = new ResultBean<>();
        result.setStatus(ResultBean.Status.ERROR);

        logger.error(proceedingJoinPoint.getSignature() + " ERROR", e);

        if (e instanceof DemoException) {
            result.setMsg(e.getLocalizedMessage());
        } else {
            result.setMsg("INTERNAL ERROR");
            // you could also email exception info to yourself
        }

        return result;

    }


}
