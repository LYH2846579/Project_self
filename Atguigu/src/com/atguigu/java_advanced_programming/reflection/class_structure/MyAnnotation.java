package com.atguigu.java_advanced_programming.reflection.class_structure;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @author LYHstart
 * @create 2021-09-05 10:09
 *
 * 自定义注解：参考SuppressWarnings
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME) //通过反射获取信息时，必须修改为运行时
public @interface MyAnnotation
{
    String value() default "Hello";
}
