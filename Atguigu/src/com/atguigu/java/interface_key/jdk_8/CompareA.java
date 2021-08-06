package com.atguigu.java.interface_key.jdk_8;

/**
 * @author LYHstart
 * @create 2021-08-05 10:56
 */
public interface CompareA
{
    public static void method1()
    {
        System.out.println("CompareA:北京");
    }
    public default void method2()
    {
        System.out.println("CompareA:上海");
    }
    default void method3()
    {
        System.out.println("CompareA:广州");
    }

}
