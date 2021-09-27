package com.atguigu.java_advanced_programming.thread_control.deadlock_thread;

/**
 * @author LYHstart
 * @create 2021-08-11 14:08
 * 演示死锁问题   deadlock
 *
 */
public class ThreadTest
{
    public static void main(String[] args)
    {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //匿名线程      继承形式实现
        new Thread(){
            @Override
            public void run() {
                synchronized (s1)
                {
                    s1.append("statement");
                    s2.append("1");

                    //线程阻塞
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    synchronized (s2)
                    {
                        s1.append("b");
                        s2.append("2");
                    }
                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }.start();

        //匿名线程      实现接口
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                synchronized (s2)
                {
                    s1.append("c");
                    s2.append("3");
                    //线程阻塞
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    synchronized (s1)
                    {
                        s1.append("d");
                        s2.append("1");
                    }
                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }){}.start();
    }
}
