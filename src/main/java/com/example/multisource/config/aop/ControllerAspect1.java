package com.example.multisource.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
public class ControllerAspect1 {

    @Around("execution(* com.example.multisource.controller..*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around1");
        Object result=point.proceed();
        System.out.println("around1");
        return result;
    }

    @Before("execution(* com.example.multisource.controller..*.*(..))")
    public void before(JoinPoint point)
    {
        System.out.println("before1");
    }

    @After("execution(* com.example.multisource.controller..*.*(..))")
    public void after(JoinPoint point)
    {
        System.out.println("after1");
    }

    @AfterReturning("execution(* com.example.multisource.controller..*.*(..))")
    public void afterReturning(JoinPoint point)
    {
        System.out.println("afterReturning1");
    }

    @AfterThrowing("execution(* com.example.multisource.controller..*.*(..))")
    public void afterThrowing(JoinPoint point)
    {
        System.out.println("afterThrowing1");
    }


}
