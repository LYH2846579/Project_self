package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-09 19:27
 *
 * 测试Thread中常用的方法:
 * 1.start():启动当前线程，调用当前线程run()
 * 2.run():通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3.currentThread():静态方法，返回当前代码执行的线程
 * 4.getName():获取当前线程的名字
 * 5.setName():设置当前线程的名字
 * 6.yield():释放CPU占用权
 * 7.join():在线程a中调用线程b的join(),此时线程α就进入阻塞状态，直到线程b完全执行完以后，线程a才
 *            结束阻塞状态。
 * 8.stop():已过时。当执行此方法时，强制结束当前线程。
 * 9.sleep(Long millitime):让当前线程′睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前
 *            线程是阻塞状态。
 * 10.isAlive():判断当前线程是否存活
 */
public class ThreadMethod
{
    @Test
    public void test1()
    {
        MethodThread m1 = new MethodThread();
        m1.setName("线程一：");
        m1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程：");
        for (int i = 0; i < 100; i++)
        {
            if(i%2 == 0)
                System.out.println(Thread.currentThread().getName()+":"+i);
            if(i == 50)
            {
                try
                {                                   //m1.start();
                    m1.join();                      //在使用join命令之前,必须使得进程处于运行状态
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MethodThread extends Thread
{
    public MethodThread() {
    }
    public MethodThread(String name) {
        super(name);
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            if(i%2 == 0)
                System.out.println(Thread.currentThread().getName()+":"+i);
            //isAlive
            if(i == 4)
                System.out.println("The Thread isAlive:"+this.isAlive());
            //sleep
            try
            {
                sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            //yield
            if(i%20 == 0)
                yield();                //释放CPU占用权
        }
    }
}

