package com.atguigu.java.method_rewrite;

/**
 * @author LYHstart
 * @create 2021-07-27 9:03
 * 方法的重写：
 * ① 父类返回值范围大于子类，子类限定符权限不能大于父类
 * ② 子类不可以重写父类的private方法
 * ③ static方法不可被重写
 * ④ 重写方法具有相同的方法名称和参数列表
 */
public class Student extends Person
{
    String major;

    public Student()
    {

    }
    public Student(String major)
    {
        this.major = major;
    }

    public void study()
    {
        System.out.println("学习的专业是:"+major);
    }

    //Overrides method
    public void eat()
    {
        System.out.println("学生应该是有营养的食物");
    }
}
