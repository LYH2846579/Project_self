package com.atguigu.java_advanced_programming.thread_control.account_test;

/**
 * @author LYHstart
 * @create 2021-08-12 21:33
 *
 * 使用synchronized解决线程问题
 *  ②尝试使用synchronized:同步方法进行处理
 *      (1)将需要处理的代码使用synchronized包起来的方法进行实现,并在run()方法中进行调用
 *      (2)同步监视器默认为Class.class
 *  附：1.由于多线程存在的多对象，使得show()方法必须为静态方法，对应常量为静态常量
 */
public class AccountTest2
{
    public static void main(String[] args)
    {
        //创建一个账户
        Account2 acct = new Account2();
        //初始化账户
        acct.setAcct(0.0);
        Customer2 c1 = new Customer2("线程一:",acct);
        Customer2 c2 = new Customer2("线程二:",acct);

        c1.start();
        c2.start();
    }
}

//创建一个账户，在用户构造器中使用同一个账户
class Account2
{
    //包装类类型的变量无初始化值!
    private Double acct;

    public Double getAcct() {
        return acct;
    }

    public void setAcct(Double acct) {
        this.acct = acct;
    }
}
//继承Thread
class Customer2 extends Thread
{
    //继承实现多线程   ->   synchronized同步方法必须使用静态变量
    private static Account2 acct;

//    //继承实现多线程   ->   synchronized同步代码块必须使用静态监视器         //※
//    Object o = new Object();

    //构造器

    public Customer2(Account2 acct) {
        this.acct = acct;
    }

    public Customer2(String name, Account2 acct) {
        super(name);
        this.acct = acct;
    }

    @Override
    public void run() {
        show();
    }
    //继承实现多线程   ->   synchronized同步方法必须使用静态方法
    private static synchronized void show()        //同步监视器:Customer2.class  !!!
    {
        //存储三千元
        for (int i = 0; i < 3; i++)
        {
            double d = acct.getAcct();
            d += 1000;
            acct.setAcct(d);
            System.out.println(Thread.currentThread().getName()+":"+acct.getAcct());
        }
    }
}

