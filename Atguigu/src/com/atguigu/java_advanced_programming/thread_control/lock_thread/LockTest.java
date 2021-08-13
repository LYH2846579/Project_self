package com.atguigu.java_advanced_programming.thread_control.lock_thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LYHstart
 * @create 2021-08-11 18:43
 *
 * 解决线程安全问题三：Lock锁          --- JDK5.0新增
 */
public class LockTest
{
    public static void main(String[] args)
    {
        Window w = new Window();

        Thread t1 = new Thread(w,"线程一:");
        Thread t2 = new Thread(w,"线程二:");
        Thread t3 = new Thread(w,"线程三:");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable
{
    private int ticket = 100;           //当票数值设定在10000时交互开始
                                        //true  -> 公平进入
    private ReentrantLock lock = new ReentrantLock();       //ctrl+p    显示参数提示

    @Override
    public void run() {
        while(true)
        {
            try
            {
                //调用锁定方法Lock()
                lock.lock();

                if(ticket > 0)
                {
                    try
                    {
                        Thread.sleep(100);      //sleep值设定为1可以更好的观察交互
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket--;
                }
                else
                    break;
            }finally
            {
                //解除锁定
                lock.unlock();
            }
        }
    }
}
