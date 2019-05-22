package com.incowiz.sample.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Autowired(required = false)
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.incowiz.sample.demo.web.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[Aspect Before] URL: " + request.getRequestURL() + "URI: " + request.getRequestURI());
        log.info("[Aspect Before] Join point kind : {}", joinPoint.getKind());
        log.info("[Aspect Before] Signature declaring type : {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("[Aspect Before] Signature name : {}", joinPoint.getSignature().getName());
        log.info("[Aspect Before] Arguments : {}", Arrays.toString(joinPoint.getArgs()));
        log.info("[Aspect Before] Target class : {}", joinPoint.getTarget().getClass().getName());
        log.info("[Aspect Before] This class : {}", joinPoint.getThis().getClass().getName());
    }

    @After("execution(* com.incowiz.sample.demo.web.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[Aspect After] The method {}() begins with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        log.info("[Aspect After] URL: " + request.getRequestURL() + "URI: " + request.getRequestURI());
    }

    @AfterReturning(
            pointcut = "execution(* com.incowiz.sample.demo.web.*.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[Aspect AfterReturning]The method {}() ends with {}",
                    joinPoint.getSignature().getName(),
                    result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.incowiz.sample.demo.web.*.*(..))",
            throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("[Aspect AfterThrowing] An exception {} has been throw in {}()",
                    e,
                    joinPoint.getSignature().getName());
    }
}
