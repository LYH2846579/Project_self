package com.atguigu.java_advanced_programming.thread_control.account_test;

/**
 * @author LYHstart
 * @create 2021-08-12 20:09
 *
 * 使用synchronized解决线程问题
 *  ①尝试使用synchronized:同步代码块进行处理
 *      (1)将需要处理的共享数据块使用synchronized包起来
 *      (2)将同步监视器设置为唯一的       ->    继承(类对象)
 */
public class AccountTest1
{
    public static void main(String[] args)
    {
        //创建一个账户
        Account1 acct = new Account1();
        //初始化账户
        acct.setAcct(0.0);
        Customer1 c1 = new Customer1("线程一:",acct);
        Customer1 c2 = new Customer1("线程二:",acct);

        c1.start();
        c2.start();
    }
}

class Account1
{
    private Double acct;

    public Double getAcct() {
        return acct;
    }

    public void setAcct(Double acct) {
        this.acct = acct;
    }
}

class Customer1 extends Thread
{
    private Account1 acct;

    public Customer1(Account1 acct) {
        this.acct = acct;
    }
    public Customer1(String name, Account1 acct) {
        super(name);
        this.acct = acct;
    }

    //重写方法

    @Override
    public void run()
    {
        //强制休眠
        try
        {
            sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //修改账户信息
        for (int i = 0; i < 3; i++)
        {
            synchronized (Customer.class)
            {
                double d = this.acct.getAcct();
                d += 1000;
                this.acct.setAcct(d);
                System.out.println(this.getName()+":"+this.acct.getAcct());
            }
        }
    }
}





