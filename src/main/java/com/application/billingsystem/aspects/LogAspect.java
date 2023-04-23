package com.application.billingsystem.aspects;

import com.application.billingsystem.annotations.BillingInfo;
import com.application.billingsystem.annotations.FileHandlerInfo;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @After("@annotation(billingInfo)")
    public void logBillingInformation(JoinPoint joinPoint, BillingInfo billingInfo){
        var value = billingInfo.value();
        var className = joinPoint.getSignature().getDeclaringTypeName();
        log.info(" Information from the class: {}, with value: {} ", className, value);
    }

    @After("@annotation(fileHandlerInfo)")
    public void logFileInfo(JoinPoint joinPoint, FileHandlerInfo fileHandlerInfo){
        var value = fileHandlerInfo.value();
        var className = joinPoint.getSignature().getDeclaringTypeName();
        log.info(" {}, {}", className, value);
    }
}
