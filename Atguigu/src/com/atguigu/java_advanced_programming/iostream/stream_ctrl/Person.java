package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import java.io.Serializable;

/**
 * @author LYHstart
 * @create 2021-09-01 10:30
 */
public class Person implements Serializable
{

    //UID
    public static final long serialVersionUID = 421717171717171717L;

    private String name;
    private int age;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
