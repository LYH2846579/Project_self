package com.atguigu.java_advanced_programming.generic.generic_exp;



/**
 * @author LYHstart
 * @create 2021-08-27 18:03
 */
public class Employee implements Comparable<Employee>
{
    private String name;
    private int age;
    private MyDate myDate;

    public Employee() {
    }
    public Employee(String name, int age, MyDate myDate) {
        this.name = name;
        this.age = age;
        this.myDate = myDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public MyDate getMyDate() {
        return myDate;
    }
    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    //泛型实现compareTo
    @Override
    public int compareTo(Employee o) {
        return (this.getName().compareTo(o.getName()));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", myDate=" + myDate +
                '}';
    }
}
