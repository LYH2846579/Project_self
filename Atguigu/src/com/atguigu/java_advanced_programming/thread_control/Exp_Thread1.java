package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-10 11:47
 *
 * 以实现Runnable接口的方式进行实现
 *  附：①仍存在重票问题，读脏数据
 *     ②默认继承于一个对象，票数非静态!
 *
 *  原理：实际上一个卖票窗口，三个黄牛从窗口拿票贩卖
 *
 */
public class Exp_Thread1
{
    @Test
    public void test1()         //单元测试存在问题 ①运行异常 ②读取异常
    {
        Window1 w1 = new Window1();
        Thread t1 = new Thread(w1);
        t1.setName("Thread1:");
        Thread t2 = new Thread(w1);
        t2.setName("Thread2:");
        Thread t3 = new Thread(w1);
        t3.setName("Thread3:");

        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {

        Window1 w1 = new Window1();
        Thread t1 = new Thread(w1);
        t1.setName("Thread1:");
        Thread t2 = new Thread(w1);
        t2.setName("Thread2:");
        Thread t3 = new Thread(w1);
        t3.setName("Thread3:");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window1 implements Runnable
{
    public int ticket = 100;

    @Override
    public void run() {
        while(true)
        {
            if(ticket > 0)
            {
                System.out.println(Thread.currentThread().getName()+":"+ticket);
                ticket--;
            }
            else
                break;
        }
    }
}
