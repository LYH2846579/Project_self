package com.atguigu.java.final_key;

/**
 * @author LYHstart
 * @create 2021-08-01 19:26
 *
 * final关键字使用
 *
 */
public class FinalTest
{
    //final修饰变量
    final int WIDTH = 0;
    final int LEFT;
    final int RIGHT;
//    final int Down;

    //代码块中赋值
    {
        LEFT = 1;
    }
    //构造器中赋值
    public FinalTest()
    {
        RIGHT = 2;
    }
    //必须保证每一个构造器中都有一个赋值语句 -> 防止出现没有赋值的情况
    public FinalTest(int n)
    {
        RIGHT = 3;
    }

    //方法赋值
//    public void setDown(int down)
//    {
//        this.Down = down;
//    }
//    public static void setDown(int down)
//    {
//        Down = down;
//    }
}

final class FinalA
{

}
//Cannot inherit from final 'com.atguigu.java.final_key.FinalA'
class FinalB //extends FinalA
{

}
