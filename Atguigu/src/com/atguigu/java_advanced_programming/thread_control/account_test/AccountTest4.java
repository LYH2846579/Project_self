package com.atguigu.java_advanced_programming.thread_control.account_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LYHstart
 * @create 2021-08-13 9:55
 *
 * 使用JDK5.0之后新增的Lock锁对银行账户操作进行安全隐患解决
 * 附：通过实现ReentrantLock锁的方式进行实现
 *      解决接口实现类
 */
public class AccountTest4
{
    public static void main(String[] args)
    {
        //创建实现类所需参数对象           //※Double包装类无默认初始化，必须手动初始化!
        Account4 acct = new Account4();
        acct.setAcct(0.0);             //Double赋值必须为double，无自动类型转换
        //创建Runnable接口实现类对象
        Customer4 c = new Customer4(acct);
        //将实现类对象作为参数实例化Thread对象
        Thread t1 = new Thread(c,"线程一");
        Thread t2 = new Thread(c,"线程二");

        //操作数据
        t1.start();
        t2.start();
    }
}

class Account4
{
    private Double acct;

    public Double getAcct() {
        return acct;
    }
    public void setAcct(Double acct) {
        this.acct = acct;
    }
}

class Customer4 implements Runnable
{
    private Account4 acct;
    //Lock锁
    private final ReentrantLock lock = new ReentrantLock(true);

    public Customer4(Account4 acct) {
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
        //启动锁
        lock.lock();
        try
        {
            //存钱
            for (int i = 0; i < 3; i++)
            {
                double d = acct.getAcct();
                d += 1000;
                acct.setAcct(d);
                System.out.println(Thread.currentThread().getName()+":"+acct.getAcct());
            }
        } finally
        {
            //释放锁
            lock.unlock();
        }
    }
}
