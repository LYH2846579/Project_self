package com.atguigu.java_advanced_programming.collection.set_interface;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author LYHstart
 * @create 2021-08-24 7:54
 *
 * 1.向TreeSet中添加对象必须是同一种类型的！
 * 2.两种排序方式：自然排序（实现Comparable接口）和定制排序（Comparator）
 *
 * 3.在自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals().
 * 4.在定制排序中，比较两个对象是否相同的标准为：compare()返回0，不再是equals().
 */
public class TreeSetTest
{
    @Test
    public void test1()
    {
        TreeSet set = new TreeSet();

        //ClassCastException不能添加不同类的对象
        //set.add(123);
        //set.add(456);
        //set.add("AA");
        //set.add(new Person("Tom",18));

        //添加整型
        /*
        set.add(123);
        set.add(1);
        set.add(23);
        set.add(123);
        */
        //添加自定义类型       //ClassCastException：未实现Comparable接口(自然排序)
        set.add(new Person("Hank",28));
        set.add(new Person("Duck",58));
        set.add(new Person("Tom",18));
        set.add(new Person("Jerry",19));


        //按照从小到大顺序输出
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

    @Test
    public void test2()
    {
        //实现Comparator接口
        TreeSet set = new TreeSet(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 == null || o2 == null)
                    throw new RuntimeException("对象传入异常!");
                else if(o1 instanceof Student && o2 instanceof Student)
                {
                    Student s1 = (Student) o1;
                    Student s2 = (Student) o2;
                    return s1.getName().compareTo(s2.getName());
                }
                else
                    throw new RuntimeException("对象类型异常!");
            }
        });

        set.add(new Student("Tom",58));
        set.add(new Student("Jerry",28));
        set.add(new Student("Jack",18));
        set.add(new Student("Duck",48));

        //遍历
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().toString());
    }
}
