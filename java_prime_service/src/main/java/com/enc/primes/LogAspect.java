package com.enc.primes;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Aspect
@Configuration
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(value = "execution(* com.enc.primes.*.*(..))",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned", joinPoint);
    }

    @AfterThrowing(value = "execution(* com.enc.primes.*.*(..))",
            throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.info("Exception: {}; with message: {}; thrown by: {}; when called with: {};",
                e.getClass(),
                e.getMessage(),
                joinPoint,
                Arrays.toString(joinPoint.getArgs()));
    }

    @Around("@annotation(com.enc.primes.LogDuration)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long starttime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - starttime;
        logger.info("Function: {}; when called with: {}; executes in {} ms",
                joinPoint,
                Arrays.toString(joinPoint.getArgs()),
                duration);
        return result;
    }
}
