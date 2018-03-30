package com.cs.mvc.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect //声明该类是切面类
@Component//配置文件中启动自动扫描功能
public class LogAOP {


	@Pointcut("execution(* *.loginUI(..))")
    public void sleeppoint(){}
    
    @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("睡觉前要脱衣服!");
    }
    
    @AfterReturning("sleeppoint()")
    public void afterSleep(){
        System.out.println("睡醒了要穿衣服！");
    }

}