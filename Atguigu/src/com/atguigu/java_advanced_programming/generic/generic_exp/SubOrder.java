package com.atguigu.java_advanced_programming.generic.generic_exp;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author LYHstart
 * @create 2021-08-27 19:14
 */
public class SubOrder extends Order<Integer>    //在继承的时候可以指明泛型对象
{                                                  //这意味着子类拥有自己的泛型<F>
    public SubOrder(String name, int age, Integer orderT) {
        super(name, age, orderT);
    }

    public void fun()
    {
        System.out.println(this.getName());
    }
}
