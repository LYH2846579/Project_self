package com.atguigu.java_advanced_programming.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * @author LYHstart
 * @create 2021-08-21 9:40
 *
 * Annotation使用示例
 * 示例一：生成文档相关注解
 * 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 *         @Override:限定重写父类方法，该注解只能用于方法
 *         @Deprecated:用于表示所修饰的元素(类、方法等)已过时
 *         @SuppressWarnings:抑制编译器警告
 * 示例三：跟踪代码依赖性，实现替代配置文件功能
 *
 * 3．如何自定义注解:参照@Suppresswarnings定义
 *       ①注解声明为:@interface
 *       ②内部定义成员，通常使用value表示
 *       ③可以指定成员的默认值，使用default定义
 *       ④如果自定义注解没有成员，表明是一个标识作用。
 *
 *       如果注解有成员，在使用注解时，需要指明成员的值。
 *
 * 4. jdk提供的4种元注解
 *    元注解:对现有的注解进行解释说明的注解
 *  Retention:指定所修饰的 Annotation 的生命周期: SOURCE\CLASS(默认行为)\RUNTIME
 *      只有声明为RUNTIME生命周期的注解，才能通过反射获取。
 *  Target
 *  Documented
 *  Inherited
 *
 * 5. 通过反射来获取有关信息
 *
 * 6.JDK8.0新特性
 *   可重复注解:①在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
 *            ②MyAnnotation的Target和Retention和MyAnnotations相同。
 *   类型注解:
 *          ELementType.TYPE_PARAMETER表示该注解能写在类型变量的声明语句中（如:泛型声明)。
 *          ELementType.TYPE_USE表示该注解能写在使用类型的任何语句中。
 */

public class AnnotationTest
{

    //可重复注解
    @MyAnnotation(value = "hi")
    @MyAnnotation(value = "aowu~")
    public static void main(String[] args)
    {
        @SuppressWarnings("unused")
        int num = 10;
    }


}

//类型注解
class Generic<@MyAnnotation T>
{
    public void show() throws @MyAnnotation RuntimeException
    {
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num = (@MyAnnotation int) 10L;
    }
}
