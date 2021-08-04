package com.atguigu.java.template_method.experiments;

/**
 * @author LYHstart
 * @create 2021-08-03 10:10
 */
public class SalariedEmployee extends Employee
{
    private double monthlySalary;

    public SalariedEmployee(String name, String number, MyDate birthday,double monthlySalary)
    {
        this.setName(name);
        this.setNumber(number);
        this.setBirthday(birthday);

        this.monthlySalary = monthlySalary;
    }

    public SalariedEmployee(double monthlySalary) {

        this.monthlySalary = monthlySalary;
    }

    @Override
    double earnings() {
            return monthlySalary;
    }

    @Override
    public String toString()
    {
        return "Type:"+this.getClass()+"name:"+this.getName()+" number:"+getNumber()+" birthday"+getBirthday();
    }
}
