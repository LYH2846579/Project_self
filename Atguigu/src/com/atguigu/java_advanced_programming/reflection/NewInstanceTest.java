package com.atguigu.java_advanced_programming.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author LYHstart
 * @create 2021-09-04 18:56
 *
 * 通过反射，创建运行时类的对象
 * 理解反射的动态性
 */
public class NewInstanceTest
{
    @Test
    public void test1() throws IllegalAccessException, InstantiationException
    {
        Class<Person> clazz = Person.class;
        /*
        newInstance():调用此方法，创建运行时类的对象，内部调用了运行时类的构造器

        要想此方法正常的创建运行时类的对象，要求：
        1.运行时类必须提供空参构造器
        2.空参的构造器的访问权限必须足够。通常为:public

        在javabean中要求提供一个public的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */

        Person obj = clazz.newInstance();
        System.out.println(obj);
    }

    @Test
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        int num = new Random().nextInt(3); //0,1,2
        String classPath = "";
        switch (num)
        {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.atguigu.java_advanced_programming.reflection.Person";
                break;
        }

        Object obj = getInstance(classPath);
        System.out.println(obj);

    }

    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

    /*
    java.lang.Object@621be5d1
    Sat Sep 04 19:46:46 CST 2021
    Person{name='null', age=0}
     */

}
