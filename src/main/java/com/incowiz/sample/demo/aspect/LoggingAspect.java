package com.incowiz.sample.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
        log.info("The method {}() begins with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        log.info("URL: " + request.getRequestURL() + "URI: " + request.getRequestURI());
    }
}
