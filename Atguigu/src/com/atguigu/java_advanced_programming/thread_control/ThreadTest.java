package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-09 10:00
 *
 * 多线程
 * ① 多线程创建：（1）继承于Thread类
 *                  <1>创建一个继承于Thread类的子类
 *                  <2>重写Thread类的run()方法     --> 此线程的执行的操作声明在run()中
 *                  <3>创建Thread类的子类对象
 *                  <4>通过此对象调用start()方法
 */

//1.创建子类
class MyThread extends Thread
{
    //2.重写run()方法

    @Override
    public void run() {
        //输出一百以内的所有偶数
        for (int i = 0; i < 100; i++)
        {
            if(i%2 == 0)
                System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

public class ThreadTest
{
    @Test
    public void test1()
    {
        //创建子类对象
        MyThread t1 = new MyThread();

        //通过此对象调用start()
        t1.start();

        for (int i = 0; i < 100; i++)
        {
        }
        System.out.println("Hello1");
        System.out.println("Hello2");
        System.out.println("Hello3");
        System.out.println("Hello4");
        System.out.println("Hello5");
        System.out.println("Hello6");
        System.out.println("Hello7");
    }

    public static void main(String[] args) {
        //创建子类对象
        MyThread t1 = new MyThread();

        //通过此对象调用start():启用当前线程+调用当前线程的run()方法

        //问题一：不可以使用run()方法直接启动线程!
        t1.start();
        //t1.run();         //全部在main方法中执行，非多线程

        //问题二：再启动一个线程，不可以让已经start()的对象再次调用该方法，必须重新创建一个对象启动
        for (int i = 0; i < 100; i++)
        {
            if(i%2 == 0)
                System.out.println(Thread.currentThread().getName()+":"+i);
        }

    }
}
