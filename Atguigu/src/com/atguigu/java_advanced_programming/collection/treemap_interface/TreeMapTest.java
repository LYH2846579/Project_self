package com.atguigu.java_advanced_programming.collection.treemap_interface;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author LYHstart
 * @create 2021-08-25 8:50
 */
public class TreeMapTest
{
    //向TreeMap中添加key-value，要求key必须是由同一个类创建的对象
    //因为要按照key进行排序:自然排序、定制排序

    @Test
    public void test1()
    {
        //Person自然排序
        Map map = new TreeMap();

        map.put("AA",17);
        map.put("CC",18);
        map.put(new String("DD"),20);
        map.put("BB",19);

        System.out.println(map);
    }

    @Test
    public void test2()
    {
        //Student定制排序
        Map map = new TreeMap(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Student && o2 instanceof Student)
                {
                    Student s1 = (Student) o1;
                    Student s2 = (Student) o2;

                    return s1.getName().compareTo(s2.getName());
                }
                else
                    throw new RuntimeException("对象类型错误!");
            }
        });

        map.put("AA",17);
        map.put("CC",18);
        map.put(new String("DD"),20);
        map.put("BB",19);

        System.out.println(map);
    }
}

//自然排序
class Person implements Comparable
{
    private String name;
    private int age;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o == null)
            throw new RuntimeException("传入对象异常!");
        else if(o instanceof Person)
        {
            Person p = (Person) o;
            return p.getName().compareTo(this.getName());
        }
        else
            throw new RuntimeException("对象类型异常!");
    }
}

//定制排序
class Student
{
    private String name;
    private int age;

    public Student() {
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
