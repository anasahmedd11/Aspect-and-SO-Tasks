package com.example.demo.controllers;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    // To get file location, right click -> copy path reference -> copy reference
    @Before("execution(* com.example.demo.controllers.MyService.*(..))")
    public void beforeMyServiceMethods() {
        System.out.println("Aspect: Before MyService methods");
    }
}
