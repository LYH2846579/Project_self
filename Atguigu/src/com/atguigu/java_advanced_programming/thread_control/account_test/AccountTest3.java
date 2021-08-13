package com.atguigu.java_advanced_programming.thread_control.account_test;

/**
 * @author LYHstart
 * @create 2021-08-13 9:30
 *
 * 处理以接口的形式实现的多线程问题
 * 附：以同步代码块的方式实现的多线程处理只要将同步监视器设置为同一个对象即可
 *      (如：Account和Customer中，Customer构造器内涵Account)
 *
 * 因此，这里只练习使用同步方法对以接口的形式实现的多线程问题的解决方案
 *
 */
public class AccountTest3
{
    public static void main(String[] args)
    {
        Account3 acct = new Account3();
        acct.setAcct(0.0);              //必须是double类型

        Customer3 c = new Customer3(acct);

        Thread t1 = new Thread(c,"线程一");
        Thread t2 = new Thread(c,"线程二");    //注意参数顺序

        t1.start();
        t2.start();
    }
}

class Account3
{
    private Double acct;

    public Double getAcct() {
        return acct;
    }
    public void setAcct(Double acct) {
        this.acct = acct;
    }
}

//将该类对象作为参数赋给Thread类        //※由于该对象仅仅创建一个，因此不需要声明为静态方法
class Customer3 implements Runnable
{
    private Account3 acct;

    public Customer3(Account3 acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        //休眠
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        show();
    }

    private synchronized void show()    //此时同步监视器为Customer3.class
    {
        //存钱
        for (int i = 0; i < 3; i++)
        {
            double d = acct.getAcct();
            d += 1000;
            acct.setAcct(d);
            System.out.println(Thread.currentThread().getName()+":"+acct.getAcct());
        }
    }
}


