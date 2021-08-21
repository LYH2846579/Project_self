package com.atguigu.java_advanced_programming.annotation;

import com.sun.deploy.security.ValidationState;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author LYHstart
 * @create 2021-08-21 9:56
 *
 * 自定义注解
 */

@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,FIELD,METHOD,LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation
{
    String value() default "Hello";
}
