package com.example.multisource.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*@Aspect
@Component
@Order(11)*/
public class ControllerAspect2 {

   @Before("execution(* com.example.multisource.controller..*.*(..))")
    public void before(JoinPoint point)
    {
        System.out.println("before2");
    }

    @After("execution(* com.example.multisource.controller..*.*(..))")
    public void after(JoinPoint point)
    {
        System.out.println("after2");
    }

    @Around("execution(* com.example.multisource.controller..*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around2");
        Object result=point.proceed();
        System.out.println("around2");
        return result;
    }

    @AfterReturning("execution(* com.example.multisource.controller..*.*(..))")
    public void afterReturning(JoinPoint point)
    {
        System.out.println("afterReturning2");
    }

    @AfterThrowing("execution(* com.example.multisource.controller..*.*(..))")
    public void afterThrowing(JoinPoint point)
    {
        System.out.println("afterThrowing2");
    }
}
