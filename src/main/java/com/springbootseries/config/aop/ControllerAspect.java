package com.springbootseries.config.aop;

import com.springbootseries.exception.DemoException;
import com.springbootseries.vo.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) proceedingJoinPoint.proceed();
            logger.info(proceedingJoinPoint.getSignature() + " time: " + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(proceedingJoinPoint, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint proceedingJoinPoint, Throwable e) {
        ResultBean<?> result = new ResultBean<>();

        if (e instanceof DemoException) {
            result.setMsg(e.getLocalizedMessage());
            result.setStatus(ResultBean.Status.ERROR);
        } else {
            logger.error(proceedingJoinPoint.getSignature() + " error", e);
            result.setMsg("Internal error");
            result.setStatus(ResultBean.Status.ERROR);
            // you also can email exception info to yourself
        }

        return result;

    }


}
