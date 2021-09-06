package com.atguigu.java_advanced_programming.jdk8plus.Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author LYHstart
 * @create 2021-09-06 14:39
 *
 * Lambda表达式的使用
 *
 * 1.举例：(o1,o2) -> Integer.compare(o1,o2);
 * 2.格式:
 *      -> :Lambda操作符 或 箭头操作符
 *      ->左边：Lambda形参列表(就是接口中抽象方法的形参列表)
 *      ->右边：Lambda体(就是重写的抽象方法的方法体)
 *
 * 3.Lambda表达式的使用：（6种情况）
 *
 *  总结：
 *  -> 左边：Lambda形参列表中的参数类型可以省略(若存在类型推断);如果形参列表只有一个参数的时候，小括号可以省略
 *  -> 右边：Lambda体应当使用{}包裹;若Lambda体只有一条执行语句(可能是return语句)，则{}和return可以同时省略
 *
 *
 * 4.Lambda表达式的本质：作为接口的一个实例
 *  附：接口中有且仅有一个抽象方法!
 *
 * 5.这里接口一般来说需要有一个实现类，但是为了简洁采取接口指向的匿名实现类来进行操作
 */
public class LambdaTest1
{
    @Test   //语法格式一：无参，无返回值 -> Runnable
    public void test1()
    {
        Runnable r1 = new Runnable()
        {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        r1.run();

        //Lambda
        Runnable r2 = () -> {
            System.out.println("World");
        };
        r2.run();
    }

    @Test   //语法格式二：有一个参数，没有返回值
    public void test2()
    {
        Consumer<String> con = new Consumer<String>()
        {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("Hello World!");

        //Lambda              //(String s) 也可
        Consumer<String> con1 = (s) ->
        {
            System.out.println(s);
        };
        con1.accept("Hello China!");
    }

    @Test   //语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为"类型推断"
    public void test3()
    {
        Comparator<Integer> com1 = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(com1.compare(12, 13));

        //Lambda    //当用{}包住的时候，return不可以省略
        Comparator<Integer> com2 = (o1,o2) -> {
            return Integer.compare(o1,o2);
        };

        System.out.println(com2.compare(13, 14));

        //类型推断格式
        ArrayList<String> list = new ArrayList<>();
        int[] arr = {1,2,3};
    }

    @Test   //语法格式四：Lambda若只需要一个参数的时候，参数的小括号可以省略
    public void test4()
    {
        //以Consumer为例   -> Consumer的中文意思是消费者，意即通过传递进一个参数来对参数进行操作。
        Consumer<String> con1 = new Consumer<String>()
        {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("Hello");

        //Lambda
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("World!");
    }

    @Test   //语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
    public void test5()
    {
        Comparator<Integer> com1 = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("Hello");
                System.out.println("World!");
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(com1.compare(12, 23));
        System.out.println("*******************");

        //Lambda
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println("Hello");
            System.out.println("World!");
            return Integer.compare(o1,o2);
        };
        System.out.println(com2.compare(12, 23));
    }

    @Test   //语法格式六：当Lambda体只有一条语句时，return和大括号若有，都可以省略
    public void test6()
    {
        //以Consumer为例
        Consumer<String> con1 = new Consumer<String>()
        {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("Hello");

        //Lambda
        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("World!");
    }
}
