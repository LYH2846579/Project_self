package com.atguigu.java_advanced_programming.collection.SetExp;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author LYHstart
 * @create 2021-08-24 9:21
 */
public class SetTest
{
    @Test
    public void test1()
    {
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        //第一次打印p1.hashCode()
        System.out.println(p1.hashCode());

        set.add(p1);
        set.add(p2);
        p1.name = "CC";     //利用hash值进行查询删除
        boolean b = set.remove(p1);     //false
        System.out.println(b);
        System.out.println(set);
        set.add(new Person(1001,"CC"));
        System.out.println(set);
        set.add(new Person(1001,"AA"));
        System.out.println(set);
        //由于HashCode被改变，因此可以重复添加
        Person p3 = new Person(1001,"CC");
        set.add(p3);
        System.out.println(set);
        set.add(new Person(1001,"CC"));
        System.out.println(set);

        //第二次打印p1.hashCode()
        System.out.println(p1.hashCode());
    }
}
