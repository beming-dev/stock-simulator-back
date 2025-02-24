package com.stock.stock_simulator.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.stock.stock_simulator..*.*(..)) " +
            "&& !within(com.stock.stock_simulator.AOP..*) " +
            "&& !within(com.stock.stock_simulator.config.*)")
    public void logBefore(JoinPoint joinPoint){
//        System.out.println("메소드 호출 전: " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName());
    }
}
