package com.atguigu.java_advanced_programming.generic.generic_exp;

/**
 * @author LYHstart
 * @create 2021-08-27 22:00
 */
public class SubOrder1<T> extends Order<T>
{
    private String name;
    private int age;

    private T order1T;

    public SubOrder1() {
    }
    //默认隐藏super(name,age,orderT)
    public SubOrder1(String name, int age, T order1T) {
        this.name = name;
        this.age = age;
        this.order1T = order1T;
    }
    public SubOrder1(String name, int age, T orderT, String name1, int age1, T order1T) {
        super(name, age, orderT);
        this.name = name1;
        this.age = age1;
        this.order1T = order1T;
    }

    public T getOrder1T() {
        return order1T;
    }
    public void setOrder1T(T order1T) {
        this.order1T = order1T;
    }

    public void fun()
    {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SubOrder1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", order1T=" + order1T +
                '}';
    }
}
