package com.platform.onlinecourse.exception.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

    @Around("execution(* com.platform.onlinecourse.service.impl.*.*(..))") 
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Entering method: {}", methodName);

        try {
            Object result = joinPoint.proceed();
            log.info("Completed method: {}", methodName);
            log.debug("Completed method: {} - Returned: {}", methodName, result);
            return result;
        } catch (Throwable ex) {
            log.error("Exception in method: {} with arguments {}. Exception: {}",
                    methodName, joinPoint.getArgs(), ex.getMessage(), ex);
            throw ex;

        } finally {
            log.info("Exiting method: {}", methodName);
        }
    }
}
