package com.atguigu.java_advanced_programming.generic.generic_exp;

/**
 * @author LYHstart
 * @create 2021-08-27 19:19
 */

                  //子类泛型           //标明父类泛型
public class SubOrder2<F> extends Order<Integer>
{
    F order2F;

    public SubOrder2(F order2F) {
        this.order2F = order2F;
    }
    public SubOrder2(String name, int age, Integer orderT, F order2F) {
        super(name, age, orderT);
        this.order2F = order2F;
    }

    public void fun()
    {
        System.out.println("*****fun*****");
    }
}
