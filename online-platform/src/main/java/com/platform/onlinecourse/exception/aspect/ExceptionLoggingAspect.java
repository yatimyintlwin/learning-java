package com.platform.onlinecourse.exception.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

    @Around("execution(* com.platform.onlinecourse.service.impl.*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.trace("Method arguments: {}", Arrays.toString(args));
        log.debug("About to execute method: {}", methodName);
        log.info("Entering method: {}", methodName);

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - start;
            log.debug("Method {} executed in {} ms", methodName, duration);
            log.info("Completed method: {} - Returned: {}", methodName, result);

            return result;

        } catch (Throwable ex) {
            log.warn("Exception in method: {}", methodName);
            log.error("Exception details", ex);
            throw ex;

        } finally {
            log.info("Exiting method: {}", methodName);
        }
    }
}
