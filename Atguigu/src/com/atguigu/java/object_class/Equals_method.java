package com.atguigu.java.object_class;

/**
 * @author LYHstart
 * @create 2021-07-30 20:11
 * 对自定义类的equals()方法进行重写
 */
public class Equals_method
{
    public static void main(String[] args)
    {
        User u1 = new User("Tom",18);
        User u2 = new User("Tom",18);
        System.out.println("u1 == u2:"+(u1 == u2));         //false
        System.out.println("u1.equals(u2):"+u1.equals(u2)); //true
    }
}

class User
{
    String name;
    int age;

    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    //重写其equals()方法
    public boolean equals(Object obj)
    {
        //判断是否为同一个对象
        if(this == obj)
            return true;
        //判断是否属于同一个类
        if(obj instanceof User)
        {
            //强制类型转换
            User u = (User)obj;
            if(u.age == this.age && this.name.equals(u.name))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}

