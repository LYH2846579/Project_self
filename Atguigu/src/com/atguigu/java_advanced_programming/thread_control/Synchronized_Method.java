package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-11 12:10
 */
public class Synchronized_Method
{
    public static void main(String[] args)
    {
        Window4 w = new Window4();

        Thread t1 = new Thread(w,"线程一：");
        Thread t2 = new Thread(w,"线程二：");
        Thread t3 = new Thread(w,"线程三：");

        t1.start();         //同一线程若多次调用start()方法
        t2.start();         //Exception in thread "main" java.lang.IllegalThreadStateException
        t3.start();
    }
}



class Window4 implements Runnable
{
    private int ticket = 100;
    Object o = new Object();        //三个线程均以一个对象作为参数,所以可以以o作为监视器

    //    @Override             //会出现重复买票和错票
//    public void run() {
//        while (true)
//        {
//            if(ticket > 0)
//            {
//                System.out.println(Thread.currentThread().getName()+":"+ticket);
//                ticket--;
//            }
//            else
//                break;
//        }
//    }
    @Override
    //使用synchronized代码块解决   -> 括起操控共同数据的代码   ->  ticket为共同数据
    public void run() {
        while (true)
        {
            show();
            if(ticket <= 0)
                break;
        }
    }
    private synchronized void show()     //默认监视器:this             //↓直接被类调用
    {                                   //附：若在继承方式实现中，需要将方法声明为静态方法，
                                              // 此时监视器为:类(如Window4.class)
        //synchronized(this)
        {
            if(ticket > 0)
            {
                System.out.println(Thread.currentThread().getName()+":"+ticket);
                ticket--;
            }
        }
    }
}