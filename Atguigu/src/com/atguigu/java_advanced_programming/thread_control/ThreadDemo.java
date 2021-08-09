package com.atguigu.java_advanced_programming.thread_control;

/**
 * @author LYHstart
 * @create 2021-08-09 13:04
 */
public class ThreadDemo
{
    public static void main(String[] args)
    {
        MyThread1 m1 = new MyThread1();
        m1.start();
        MyThread m2 = new MyThread();
        m2.start();
        //匿名线程
        new Thread(){
            @Override
            public void run() {
                System.out.println("Hello,我是匿名线程类");
            }
        }.start();
    }
}

//素数类
class MyThread1 extends Thread
{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
        {
            boolean b = true;
            for (int j = i-1; j > 1; j--)
            {
                if(i % j == 0)
                    b = false;
            }
            if(b)
                System.out.println(i);
        }
    }
}

