package com.atguigu.java.polymorphism;

/**
 * @author LYHstart
 * @create 2021-07-28 10:57
 *
 * 面向对象特征之三：多态性
 * 1. 即编译时只能调用父类中声明的方法，运行的时候调用子类中重新声明的方法
 * 2. 多态使用前提：①继承关系  ②方法重写
 */
public class PersonTest
{
    public static void main(String[] args)
    {
        Person p1 = new Person();
        p1.eat();

        Man m = new Man();
        m.eat();

        //***************************
        //对象的多态性:父类的引用指向子类的对象    （C++）父类指针指向子类对象
        Person p2 = new Man();
        //多态的使用:调用子类重写父类的方法         ※只能调用Person中声明过的方法
        p2.eat();
    }
}
