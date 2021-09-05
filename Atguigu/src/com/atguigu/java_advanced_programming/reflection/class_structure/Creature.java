package com.atguigu.java_advanced_programming.reflection.class_structure;

import java.io.Serializable;

/**
 * @author LYHstart
 * @create 2021-09-05 10:01
 */
public class Creature<T> implements Serializable
{
    private char gender;
    public double weight;

    private void breath()
    {
        System.out.println("生物呼吸");
    }

    public void eat()
    {
        System.out.println("生物吃东西");
    }
}
