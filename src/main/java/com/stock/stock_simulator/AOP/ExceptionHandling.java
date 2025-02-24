package com.stock.stock_simulator.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandling {
    @AfterThrowing(
            pointcut = "execution(* com.stock.stock_simulator..*.*(..)) " +
                    "&& !within(com.stock.stock_simulator.AOP..*) " +
                    "&& !within(com.stock.stock_simulator.config.*)",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex){
        System.out.println("메소드: "
                + joinPoint.getSignature().getName()
                + "에서 예외발생: " + ex.getMessage());
    }
}
