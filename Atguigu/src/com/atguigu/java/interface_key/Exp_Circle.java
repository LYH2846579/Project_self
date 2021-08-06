package com.atguigu.java.interface_key;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-08-05 10:29
 */
public class Exp_Circle
{
    @Test
    public void test1()
    {
        Circle c1 = new Circle(1.2);
        Circle c2 = new Circle((double)2);      //不支持自动类型转换
        ComparableCircle c = new ComparableCircle(1.2);
        int temp;
        temp = c.compareTo(c1);
        System.out.println(temp);
        temp = c.compareTo(c2);
        System.out.println(temp);
        c.compareTo("123");                   //抛出异常
    }
}

interface CompareObject
{
    //若返回值为0，代表相等；若为正数，则代表当前对象大；若为负数，则代表当前对象小；
    public int compareTo(Object o);
}

class Circle
{
    private Double radius;

    //constructor
    public Circle() {
    }
    public Circle(Double radius)
    {
        this.radius = radius;
    }
    //get & set


    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}

class ComparableCircle extends Circle implements CompareObject
{
    public ComparableCircle(Double radius)
    {
        super(radius);
    }

    @Override
    public int compareTo(Object o) {
        if(this == o)
            return 0;
        else if(o instanceof Circle)
        {
            Circle c = (Circle)o;
            return this.getRadius().compareTo(c.getRadius());
        }
        else
            throw new RuntimeException("传入的数据类型不匹配!");
    }
}