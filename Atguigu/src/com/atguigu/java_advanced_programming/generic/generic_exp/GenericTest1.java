package com.atguigu.java_advanced_programming.generic.generic_exp;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-08-27 18:55
 *
 * 如何自定义泛型结构：泛型类、泛型接口;泛型方法
 *
 * 1、关于自定义泛型类、泛型接口：
 *
 *
 * 附：SubOrder:子类继承父类，实例化父类泛型
 *    SubOrder2:子类继承父类，实例化父类泛型，子类自身声明子类泛型
 *    SubOrder1:子类继承父类泛型
 *
 *    ①当子类不实例化父类泛型时，子类对象必须使用父类泛型
 *    ②子类可以选择性实例化父类泛型，选择性继承父类泛型
 *
 *
 *  2、自定义泛型方法   -> Order类中copyFromArrayToList方法
 *    泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系!
 *    换句话说：泛型方法所属的类是不是泛型类都没有任何关系
 *    泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的，并非在实例化类时确定。
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
        //附：若父类中泛型类型已被指明，因此子类可以声明自己的泛型类型
        SubOrder2<Double> sub2 = new SubOrder2<>("Jack",17,new Integer(730),new Double(7));
        sub2.fun();
    }

    @Test
    public void test4()
    {
        //子类继承父类泛型?
        SubOrder1<Character> sub1 = new SubOrder1<>("Rose",15,'C');
        sub1.fun();
    }

    @Test
    public void test5()
    {
        //Order中静态方法调用
        Order<String> o = new Order<>();
        Integer[] integer = new Integer[]{2,1,3,4};
        List<Integer> integers = o.copyFromArrayToList(integer);

        //遍历
        Iterator iterator = integers.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
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
