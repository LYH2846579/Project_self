package com.atguigu.java_advanced_programming.thread_control.wait_notify;

/**
 * @author LYHstart
 * @create 2021-08-13 10:26
 *
 * 例：使用两个线程交替打印：1-100
 *
 * 处理线程之间的通信问题：
 * wait()方法和notify()方法的使用
 * wait():使当前线程挂起并放弃CPU、同步资源并等待     [会释放已占用的锁]
 * notify():唤醒正在排队等待同步资源中优先级最高者结束等待
 * notifyAll():唤醒所有正在排队等待资源的线程结束等待
 *
 * 面试题：sleep()和wait()的异同
 * 1.相同点：一旦执行方法，都可以使得当前线程进入阻塞状态。且两者均会抛出异常
 * 2.不同点：1）两个方法声明的位置不同：Thread类中声明sleep(),Object类中声明wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用，wait()必须使用在同步代码块或同步方法中
 *          3)关于是否释放同步监视器：如果两个方法都是在同步代码块或者同步方法中，sleep()方法不会释放锁，wait()方法会释放锁
 *
 */
public class CommunciationTest
{
    public static void main(String[] args)
    {
        Number n = new Number();
        //实例化Thread类对象
        Thread t1 = new Thread(n,"线程一");
        Thread t2 = new Thread(n,"线程二");
        //运行
        t1.start();
        t2.start();
    }
}

//以实现接口形式实现
class Number implements Runnable
{
    private int num = 1;

    @Override
    public void run() {
        while (true)
        {
            //解决线程安全问题
            synchronized (this)
            {
                //唤醒另外一个线程
                notify();
                //输出结果
                System.out.println(Thread.currentThread().getName()+":"+num);
                //休眠
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                num++;}
            //线程阻塞
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //判断退出条件
            if(num > 100)
                break;
        }
    }
}