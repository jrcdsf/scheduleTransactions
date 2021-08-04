package com.diletta.claropay.scheduling.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TransactionLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.diletta.claropay.scheduling.rest.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.diletta.claropay.scheduling.services.*.*(..))")
    private void forServicesPackage(){}

    @Pointcut("execution(* com.diletta.claropay.scheduling.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicesPackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method  = joinPoint.getSignature().toShortString();
        logger.info("=====>> @Before: calling method: " + method);
        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info("=====>> argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut="forAppFlow()", returning="theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("=====>> @AfterReturning: from method: " + theMethod);

        logger.info("=====>> result: " + theResult);

    }
}
