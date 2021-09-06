package com.atguigu.java_advanced_programming.jdk8plus.Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author LYHstart
 * @create 2021-09-06 15:39
 *
 * Java内置的四大核心函数式接口 -> 位于java.util.function下
 *
 * 消费型接口:Consumer<T>   ->  void accept(T t);
 * 供给型接口:Supplier<T>   ->  T get();
 * 函数型接口:Function<T,R> ->  R apply(T t);
 * 断定型接口:Predicate<T>  ->  boolean test(T t);
 *
 * 附:函数式接口使用
 *  ① 一般创造一个函数，其中一个参数为函数式接口的实现对象
 *  ② 通过调用此函数创建一个接口对象使用接口功能
 */
public class LambdaTest2
{
    @Test   //消费型接口
    public void test1()
    {
        happyTime(500, new Consumer<Double>()
        {
            @Override
            public void accept(Double aDouble) {
                System.out.println("中彩票获得了:"+aDouble);
            }
        });
    }

    public void happyTime(double money, Consumer<Double> con)
    {
        con.accept(money);
    }


    @Test   //Lambda表达式写法
    public void test2()
    {
        happyTime(500,d -> {
            System.out.println("中彩票获得了:"+d);
        });
    }



    //断言接口
    @Test
    public void test3()
    {
        //一般写法
        List<String> list = Arrays.asList("北京","天津","南京","东京","西京","普京");
        List<String> list1 = filterString(list, new Predicate<String>()
        {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        for(String s:list1)
            System.out.println(s);
        System.out.println("**********");

        //Lambda
        List<String> list2 = filterString(list, s ->
        {
            return s.contains("京");
        });
        for(String s:list2)
            System.out.println(s);
    }
    //根据给定的规则，过滤集合中的字符串，此规则有Predicate决定
    public List<String> filterString(List<String> list, Predicate<String> pre)
    {
        ArrayList<String> filterList = new ArrayList<>();
        for(String s:list)
        {
            if(pre.test(s))
                filterList.add(s);
        }

        return filterList;
    }

}

