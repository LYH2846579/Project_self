package com.atguigu.java.template_method.experiments;

/**
 * @author LYHstart
 * @create 2021-08-03 20:01
 */
public class HourlyEmployee extends Employee
{
    private double wage;
    private int hour;

    public HourlyEmployee(String name, String number, MyDate birthday,double wage,int hour)
    {
        this.setName(name);
        this.setNumber(number);
        this.setBirthday(birthday);

        this.wage = wage;
        this.hour = hour;
    }

    @Override
    double earnings() {
        return wage*hour;
    }

    @Override
    public String toString() {
        return "Type:"+this.getClass()+"name:"+this.getName()+" number:"+getNumber()+" birthday"+getBirthday();
    }
}
