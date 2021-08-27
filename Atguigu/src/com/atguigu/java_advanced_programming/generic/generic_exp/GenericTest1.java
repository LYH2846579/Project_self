package com.atguigu.java_advanced_programming.generic.generic_exp;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-27 18:55
 *
 * 如何自定义泛型结构：泛型类、泛型接口;泛型方法
 *
 * 1、关于自定义泛型类、泛型接口：
 *
 */
public class GenericTest1 implements Order1
{
    @Test
    public void test1()
    {
        //自定义泛型类
        Order<MyDate> order = new Order<>("Tom",17,new MyDate(2001,8,4));
        System.out.println(order);
    }

    @Test   //子类继承父类(泛型类)，需要指明父类泛型类型
    public void test2()
    {
        SubOrder sub1 = new SubOrder("Jurry",17,new Integer(730));
        //由于子类在继承带泛型的父类时，指明的泛型类型。则实例化子类对象时，不再需要指明泛型。
        sub1.fun();
    }

    //联想：要是在子类中再使用泛型。如SubOrder<F>会有何后果?
    @Test
    public void test3()
    {
        //附：由于父类中泛型类型已被指明，因此子类可以声明自己的泛型类型
        SubOrder2<Double> sub2 = new SubOrder2<>("Jack",17,new Integer(730),new Double(7));
        sub2.fun();
    }






    @Override
    public void fun() {
        System.out.println("Hello!");
    }

    @Override
    public void show() {
        this.fun();
    }
}
