package com.atguigu.java_advanced_programming.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * @author LYHstart
 * @create 2021-08-21 10:51
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,FIELD,METHOD,LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotations
{
    MyAnnotation[] value();
}
