package com.felfel.springsecurity.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;



@Component
@Aspect
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Before("execution(* com.felfel.springsecurity.controller.HelloController.*(..))")
    public void logBeforeHelloMethods(JoinPoint jp) {
        logger.info(" executing controller method : {}", jp.getSignature().getName());
    }

    @Before("execution(* com.felfel.springsecurity.controller.UserController.*(..))")
    public void logBeforeUserMethod(JoinPoint jp) {
        logger.info(" executing controller method : {}", jp.getSignature().getName());
    }

    @Before("execution(* com.felfel.springsecurity.controller.StudentController.*(..))")
    public void logBeforeStudentMethod(JoinPoint jp) {
        logger.info(" executing controller method : {}", jp.getSignature().getName());
    }

}
