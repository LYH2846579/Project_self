package com.atguigu.java_advanced_programming.thread_control;

/**
 * @author LYHstart
 * @create 2021-08-10 11:28
 *
 * 多线程实现方式二：以实现类（Runnable接口）为参数实例化Thread对象，调用该对象的start方法
 */
public class Thread_Runnable
{
    public static void main(String[] args) {
        RunThread1 r1 = new RunThread1();
        //以实现类为参数实例化Thread对象
        Thread t1 = new Thread(r1);
        //start():①启动线程 ②调用当前线程的run()方法 -> 调用Runnable类型的target的run()方法
        t1.start();
        //线程重命名
        t1.setName("RunThread1:");

        //创建一个同样功能的线程
        Thread t2 = new Thread(r1);
        t2.setName("RunThread2:");
        t2.start();
    }
}

//类实现Runnable接口
class RunThread1 implements Runnable
{
    //重写run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
        {
            if(i%2 == 0)            //获取当前线程名称
                System.out.println(Thread.currentThread().getName()+i);
        }
        //System.out.println(getClass());
    }
}
