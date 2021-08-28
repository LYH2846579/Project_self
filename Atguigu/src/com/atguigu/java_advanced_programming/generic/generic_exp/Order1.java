package com.atguigu.java_advanced_programming.generic.generic_exp;

/**
 * @author LYHstart
 * @create 2021-08-27 19:00
 */
public interface Order1<T>
{
    //interface默认静态变量与静态方法
    String name = "Jurry";

    public static final int age = 17;

    void fun();

    public abstract void show();
}
