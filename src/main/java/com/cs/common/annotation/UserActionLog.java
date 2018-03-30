package com.cs.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cs.common.entityenum.LogTypeEnum;


/**
 * 用户操作日志注解
 * @author llz
 *
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UserActionLog {
	//用户操作类型
	public LogTypeEnum userLogType() default LogTypeEnum.LOGIN;
	//操作详细描述
	public String description() default "";
}
