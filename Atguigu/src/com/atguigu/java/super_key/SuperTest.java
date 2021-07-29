package com.atguigu.java.super_key;

/**
 * @author LYHstart
 * @create 2021-07-27 14:37
 * 1.super理解为：父类的
 * 2.可以适用于属性、方法、构造器
 * 3.属性:若子类中存在与父类同名的参数，可以使用super关键字进行调用,方法类似
 * 4.构造器:在子类的构造器中使用super(...)的方式进行调用父类构造器
 *          super构造器的使用必须声明在子类构造器的首行(※)
 *          若未指定super(...)，则默认存在super();            --> 若父类不存在空参构造器，则不允许使用super()
 */
public class SuperTest
{
    public static void main(String[] args)
    {
        //super调用属性
        Student s = new Student("网安",17);
        s.print();
    }
}
