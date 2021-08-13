package com.atguigu.java_advanced_programming.thread_control.singletemp_thread;

/**
 * @author LYHstart
 * @create 2021-08-11 13:45
 *
 * 使用同步机制将单例模式中的懒汉式修改为线程安全的
 *
 */
public class BankTest
{
}

//懒汉式
class Bank
{
    //私有的构造器
    private Bank()
    {  }

    //私有的静态类属性
    private static Bank b = null;
    //公有返回的函数   -->     存在线程安全问题
//    public static Bank getInstance()
//    {   //方式一：效果稍差
//        synchronized (Bank.class)
//        {
//            if(b == null)
//                b = new Bank();
//            return b;
//        }
    public static Bank getInstance()
    {   //方式二：效果较好※
        if(b == null)
        {
            synchronized (Bank.class)
            {
                if(b == null)
                    b = new Bank();
            }
        }
        return b;
    }

}
