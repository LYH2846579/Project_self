package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-10 7:37
 *
 * 以继承Thread类的方式进行实现
 * 多线程练习，出现了读重复数据的情况
 */
public class Exp_Thread             //会出现不同线程卖出同一张票的情况!
{
    @Test       //三个窗口实现买票功能
    public void test1()
    {
        Window w1 = new Window("线程一");
        Window w2 = new Window("线程二");
        Window w3 = new Window("线程三");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread
{
    private static int ticket = 100;

    public Window(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(true)
        {
            if(ticket > 0)
            {
                System.out.println(getName()+":买票，票号为:"+ticket);
                ticket--;
            }
            else
            {
                break;
            }
        }
    }
}












