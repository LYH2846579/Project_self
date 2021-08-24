package com.atguigu.java_advanced_programming.collection.set_interface;

import org.junit.Test;

import java.util.*;

/**
 * @author LYHstart
 * @create 2021-08-23 19:27
 *
 * 1.Set接口的框架:
 *   /----Collection接口:单列集合，用来存储一个一个的对象
 *      /----set接口:存储无序的、不可重复的数据-->高中讲的"集合”
 *          /----HashSet:作为Set接口的主要实现类;线程不安全的;可以存储null值
 *              /----LinkedHashSet:作为HashSet的子类;遍历其内部数据时，可以按照添加的顺序输出
 *          /----TreeSet:可以按照添加对象的指定属性，进行排序。
 *
 * 2.Set中没有新定义的方法，全部继承Collection中的方法
 *
 *
 */
public class SetTest
{
    /*
    一、Set:存储无序的、不可重复的数据
        以HashSet为例
    1.无序性:不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的.

    2.不可重复性:保证添加的元素按照equals()判断时，不能反回true.即：相同的元素只能添加一个.



 * 二、添加元素过程，以HashSet为例:
 * 我们向HashSet中添加元素a,首先调用元素α所在类的hashCode()方法，计算元素a的哈希值，
 * 此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为:索引位置)，
 * 判断数组此位置上是否已经有元素:
 *    如果此位置上没有其他元素，则元素α添加成功。--->情况1
 *    如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值:
 *          如果hash值不相同，则元素α添加成功。--->情况2
 *          如果hash值相同，进而需要调用元素α所在类的equlas()方法:
 *              equals()返回true,元素α添加失败
 *              equaLs()返回false,则元素a添加成功。--->情况3
 *
 *     对于添加成功的情况2和情况3而言:元素α与已经存在指定索引位置上数据以链表的方式存储。
 *     jdk 7 :元素a放到数组中，指向原来的元素。
 *     jdk 8 ︰原来的元素在数组中，指向元素a
 *     总结:七上八下      HashSet底层：数组+链表的结构

     */

    @Test       //输出顺序
    public void test1()
    {
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add("AA");
        set.add(new String("CC"));
        set.add(new Person("Tom",12));
        set.add(456);

        //遍历
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

    @Test
    public void test2()
    {
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        set.add(123);   //重复添加123   ->   只会添加一次
        set.add("AA");
        set.add(new String("CC"));
        set.add(new Person("Tom",12));
        set.add(456);

        set.add(new Student("Jurry",20));
        set.add(new Student("Jurry",20));   //重写equals()方法之后仅存在一个对象


        //遍历
        for(Object obj:set)
            System.out.println(obj);
    }

    @Test   //LinkedHashSet:按照添加的顺序进行输出
    public void test3()
    {
        //以数组+双向链表的形式实现         对于频繁遍历操作效率高于HashSet
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add("AA");
        set.add(new String("CC"));
        set.add(new Person("Tom",12));

        //遍历
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().toString());
    }
}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }


    //自然排序->实现Comparable接口
    @Override
    public int compareTo(Object o) {
        if(o == null)
            throw new RuntimeException("对象传入异常!");
        else if(o instanceof Person)
        {
            Person p = (Person) o;
            return p.getName().compareTo(this.getName());
        }
        else
            throw new RuntimeException("对象类型异常!");
    }
}

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

    //重写equals()方法及hashCode()       //※
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }


}
