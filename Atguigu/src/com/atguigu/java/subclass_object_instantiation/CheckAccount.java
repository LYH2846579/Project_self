package com.atguigu.java.subclass_object_instantiation;

/**
 * @author LYHstart
 * @create 2021-07-28 8:41
 */
public class CheckAccount extends Account
{
    private double overdraft;

    public CheckAccount(int id, double balance, double annuallnterestRate,double overdraft)
    {
        //没有空参构造器，调用指定构造器
        super(id,balance,annuallnterestRate);
        this.overdraft = overdraft;
    }

    public void withdrow(double amount)
    {
        if(amount <= this.getBalance())
        {
            setBalance(getBalance()-amount);
            System.out.println("取款"+amount+"元成功!");
            System.out.println("余额为:"+getBalance());
        }
        else
        {
            if(getBalance()+overdraft >= amount)
            {
                double temp = getBalance()+overdraft-amount;
                setBalance(0);
                overdraft = temp;
                System.out.println("透支取款成功，剩余透支限额为:"+overdraft);
                System.out.println("余额为:"+getBalance());
            }
            else
            {
                System.out.println("您的余额已不足,无法进行透支");
                System.out.println("余额为:"+getBalance());
            }
        }
    }
}
