package com.atguigu.java.abstract_key;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-01 21:44
 *
 * 抽象类
 * ※   测试方法必须存在于public类中???
 */
public class AbstractTest
{
    @Test
    public void test1()
    {
        A a = new B();
        a.m1();
        a.m2();
    }
}

class Person
{
    String name;
    int age;
    //抽象类仍需要构造器（子类需要调用父类构造器）
    public Person()
    {

    }
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    public void eat()
    {
        System.out.println("人吃饭");
    }
    public void walk()
    {
        System.out.println("人走路");
    }
}
class Student extends Person
{

}

//抽象类
abstract class A
{
    abstract void m1();
    public void m2()
    {
        System.out.println("A类中定义的m2方法");
    }
}

class B extends A
{
    void m1()
    {
        System.out.println("B类中定义的m1方法");
    }
}


class abs_test
{

}
