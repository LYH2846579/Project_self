package com.atguigu.java_advanced_programming.jdk8plus.stream_api;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author LYHstart
 * @create 2021-09-06 16:58
 *
 * 1. Stream关注的是对数据的运算，与CPU打交道
 *    集合关注的是数据的存储，与内存打交道
 * 2.
 * 1. Stream关注的是对数据的运算，与CPU打交道
 * 集合关注的是数据的存储，与内存打交道
 * 2.
 * ① Stream 自己不会存储元素。
 * ② Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream.
 * ③ Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 *
 *
 * 3.Stream执行流程
 * ① Stream的实例化
 * ② 一系列的中间操作（过滤、映射、...)
 * ③ 终止操作
 *
 * 4.说明:
 * 4.1一个中间操作链，对数据源的数据进行处理
 * 4.2一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 *
 * 测试Stream的实例化
 */
public class StreamAPITest
{
    @Test   //创建stream方式一：通过集合
    public void test1()
    {
        List<Employee> employees = EmployeeData.getEmployees();
        //default Stream<E> stream():返回一个顺序流
        Stream<Employee> stream = employees.stream();
        //default Stream<E> parallelStream():返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();

    }

    @Test   //创建stream方式二：通过数组
    public void test2()
    {
        int[] arr = new int[]{1,2,3,4,5};
        //调用Array类的static <T> stream(T[] array):返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }

}
