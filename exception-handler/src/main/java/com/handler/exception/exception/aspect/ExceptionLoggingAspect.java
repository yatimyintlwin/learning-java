//package com.handler.exception.exception.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class ExceptionLoggingAspect {
//
//    @Pointcut("execution(* com.handler.exception.service.impl.CustomerServiceImpl.*(..))")
//    public void customerServiceMethods() {}
//
//    @Before("customerServiceMethods()")
//    public void logBeforeMethod(JoinPoint joinPoint) {
//        log.info("Entering method: {}", joinPoint.getSignature().toShortString());
//    }
//
//    @AfterReturning(pointcut = "customerServiceMethods()", returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("Completed method: {} - Returned: {}", joinPoint.getSignature().toShortString(), result);
//    }
//
//    @AfterThrowing(pointcut = "customerServiceMethods()", throwing = "ex")
//    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
//        String method = joinPoint.getSignature().toShortString();
//        log.warn("Exception in method: {}", method);
//        log.error("Exception message: {}", ex.getMessage(), ex);
//    }
//
//    @After("customerServiceMethods()")
//    public void logAfterMethod(JoinPoint joinPoint) {
//        log.info("Exiting method: {}", joinPoint.getSignature().toShortString());
//    }
//}




package com.handler.exception.exception.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

    @Around("execution(* com.handler.exception.service.impl.CustomerServiceImpl.*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Entering method: {}", methodName);

        try {
            Object result = joinPoint.proceed();
            log.info("Completed method: {} - Returned: {}", methodName, result);
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
