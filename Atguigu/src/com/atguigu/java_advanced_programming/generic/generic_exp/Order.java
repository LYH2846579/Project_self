package com.atguigu.java_advanced_programming.generic.generic_exp;

/**
 * 自定义泛型类
 *
 * @author LYHstart
 * @create 2021-08-27 18:52
 */
public class Order<T>
{
    private String name;
    private int age;

    //类的内部结构就可以使用类的泛型
    private T orderT;

    public Order() {
    }
    public Order(String name, int age, T orderT) {
        this.name = name;
        this.age = age;
        this.orderT = orderT;
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
    //泛型
    public T getOrderT() {
        return orderT;
    }
    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", orderT=" + orderT +
                '}';
    }
}
