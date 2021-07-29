package com.atguigu.java.instanceof_key;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

/**
 * @author LYHstart
 * @create 2021-07-28 10:57
 *
 * 面向对象特征之三：多态性
 * 1. 即编译时只能调用父类中声明的方法，运行的时候调用子类中重新声明的方法
 * 2. 多态使用前提：①继承关系  ②方法重写
 *
 * 3.instanceof 关键字
 *  判断属于那种数据类型
 *  ①必须满足继承关系
 *  ②判断之后进行强制类型转换，避免出现异常（ClassCastException）
 *  ③不相关的两个类型之间不可以进行强制类型转换(转换之后没啥用，直接报错)
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

        Man M = (Man)p2;

        System.out.println("*********************************");
        System.out.println("p1:"+p1);
        System.out.println("p2:"+p2);
        System.out.println("M:"+M);
        System.out.println("*********************************");

        //对象类型判别
        if(p2 instanceof Person)
            System.out.println("instanceof:p2 -> Person");

        //多态逆转，向下转型
        if(p2 instanceof Man)
        {
            //强制类型转换
            Man m1 = (Man)p2;
            System.out.println("Man!");
        }
        else if(p2 instanceof Woman)
        {
            Woman m2 = (Woman)p2;

        }

        System.out.println(p2);
    }
}
