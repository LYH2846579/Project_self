package com.atguigu.java.subclass_object_instantiation;

/**
 * @author LYHstart
 * @create 2021-07-28 8:22
 */
public class Account
{
    private int id;
    private double balance;
    private double annuallnterestRate;

    //构造器
    public Account(int id, double balance, double annuallnterestRate)
    {
        this.id = id;
        this.balance = balance;
        this.annuallnterestRate = annuallnterestRate;
    }

    public int getId()
    {
        return id;
    }

    public double getBalance()
    {
        return balance;
    }

    public double getAnnuallnterestRate()
    {
        return annuallnterestRate;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setAnnuallnterestRate(double annuallnterestRate)
    {
        this.annuallnterestRate = annuallnterestRate;
    }

    public double getMonthlyInterest()
    {
        return annuallnterestRate/12;
    }

    public void withdrow(double amount)
    {
        if(amount > this.balance)
            System.out.println("您的余额已不足");
        else
        {
            balance -= amount;
            System.out.println("取款"+amount+"元成功!");
        }
    }

    public void deposit(double amount)
    {
        balance += amount;
        System.out.println("存款"+amount+"元成功!");
    }
}
