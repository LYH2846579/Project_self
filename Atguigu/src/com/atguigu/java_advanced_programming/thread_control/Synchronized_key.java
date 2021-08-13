package com.atguigu.java_advanced_programming.thread_control;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-11 9:21
 *
 * 处理同步问题的两种解决方案
 *
 * 这里以实现Runnable接口的方式对多线程进行创建
 * (继承Thread类的方式受制于单继承的限制，接口方式更加灵活)
 * (相同点：Thread类也是实现Runnable接口)
 *
 * 方式一：同步代码块
 * synchronized(同步监视器)
 * {
 *     //需要被同步的代码
 * }
 * 说明:1.操作共享数据的代码，即为需要被同步的代码
 *     2.共享数据:多个线程共同操作的变量。比如: ticket就是共享数据。
 *     3.同步监视器，俗称:锁。任何一个类的对象，都可以充当锁。
 *     要求:多个线程必须要共用同一把锁。    ->    该对象必须唯一
 *     4.在JAVA中通过同步机制，解决线程安全问题
 *     5.同步的方式,解决了线程的安全问题。---好处
 *       操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。---劣势
 *
 * 方式二：同步方法
 * 如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *
 *
 */
public class Synchronized_key
{
    public static void main(String[] args)
    {
        Window2 w = new Window2();

        Thread t1 = new Thread(w,"线程一：");
        Thread t2 = new Thread(w,"线程二：");
        Thread t3 = new Thread(w,"线程三：");

        t1.start();         //同一线程若多次调用start()方法
        t2.start();         //Exception in thread "main" java.lang.IllegalThreadStateException
        t3.start();
    }

    @Test                   //Run ... with Coverage 可以解决问题
    public void test1()
    {
        Window3 w1 = new Window3("线程一：");
        Window3 w2 = new Window3("线程二：");
        Window3 w3 = new Window3("线程三：");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window2 implements Runnable
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
            synchronized(this)      //填写this就可  //※三个线程对象共享一个Window2对象
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
}

class Window3 extends Thread
{
    private static int ticket = 100;
    public static Object o = new Object();  //使用类变量作为监视器

    public Window3(String name) {
        super(name);
    }

    //重写run方法
    @Override
    public void run() {
        while (true)
        {
            //synchronized(o)      //以继承的方式实现Thread子类对象，监视器必须注意※
            synchronized (Window2.class)    //使用类作为监视器!
            {
                if(ticket > 0)
                {
                    System.out.println(getName()+":"+ticket);
                    ticket--;
                }
                else
                    break;
            }
        }
    }
}
