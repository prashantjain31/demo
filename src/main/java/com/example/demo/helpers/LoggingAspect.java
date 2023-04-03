package com.example.demo.helpers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.example.demo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
//        System.out.println(joinPoint.getSignature());
//        System.out.println(methodName);
//        System.out.println(joinPoint.getTarget());
//        System.out.println(joinPoint.getTarget().getClass());
//        System.out.println(className);
        log.info("Executing " + className + "." + methodName);
    }
	
	@After("execution(* com.example.demo.service.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Finished executing " + className + "." + methodName);
    }
}
