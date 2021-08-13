package com.atguigu.java_advanced_programming.thread_control.account_test;

/**
 * @author LYHstart
 * @create 2021-08-12 19:53
 *
 * 线程问题练习:
 * 银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 分析:
 * 1.是否是多线程问题?是，两个储户线程
 * 2.是否有共享数据?有，账户(或账户余额)
 * 3.是否有线程安全问题?有
 * 4.需要考虑如何解决线程安全问题?同步机制:有三种方式。 synchronized & Lock
 *
 * 最最原始的一种方式：不对线程做任何处理
 *  -> 会出现线程安全问题
 */
public class AccountTest
{
    public static void main(String[] args)
    {
        //创建一个账户
        Account acct = new Account();
        //初始化账户
        acct.setAcct(0.0);
        Customer c1 = new Customer("线程一:",acct);
        Customer c2 = new Customer("线程二:",acct);

        c1.start();
        c2.start();
    }
}

//创建一个账户，在用户构造器中使用同一个账户
class Account
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
class Customer extends Thread
{
    private Account acct;

    //构造器

    public Customer(Account acct) {
        this.acct = acct;
    }

    public Customer(String name, Account acct) {
        super(name);
        this.acct = acct;
    }

    @Override
    public void run() {
//        try           //当加上sleep之后，可以发现线程存在的问题
//        {
//            sleep(200);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
        //存储三千元
        for (int i = 0; i < 3; i++)
        {
            double d = this.acct.getAcct();
            d += 1000;
            this.acct.setAcct(d);
            System.out.println(this.getName()+":"+this.acct.getAcct());
        }
    }
}
