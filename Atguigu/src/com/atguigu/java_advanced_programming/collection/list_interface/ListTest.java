package com.atguigu.java_advanced_programming.collection.list_interface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LYHstart
 * @create 2021-08-23 9:35
 *
 * 1. l----collection接口:单列集合，用来存储一个一个的对象
 *        l----List接口:存储有序的、可重复的数据。-->“动态”数组,替换原有的数组
 *            l----ArrayList:作为List接口的主要实现类;线程不安全的，效率高;底层使用Object[] elementData存储
 *            l----LinkedList:对于频繁的插入、删除操作，使用此类效率比ArrayList高;底层使用双向链表存储
 *            l----vector:作AList接口的古老实现类;线程安全的，效率低;底层使用Object[] elementData存储
 * 2. ArrayList的源码分析:
 *  2.1 jdk 7情况下
 *      Arraylist list = new ArrayList( );//底层创建了长度是10的object[]数组elementData
 *      list.add(123); //elementData[e] = new Integer(123);
 *      ...
 *      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
 *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *      结论:建议开发中使用带参的构造器: ArrayList list = new ArrayList(int capacity)
 *
 *  2.2 jdk 8中ArrayList的变化:
 *      ArrayList list = new ArrayList();//底层object[] elementData初始化为{}.并没有创建长度为10的数组
 *      list.add(123);//第一次调用add()时，底层才创建了长度1e的数组，并将数据123添加到eLemen
 *      ...
 *      后续的添加和扩容操作与jdk 7无异。
 *  2.3小结: jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList
 *          的对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 *
 * 3.LinkedList原码分析
 *      LinkedList list = new linkedList(); 内部声明了Node类型的first和last属性，默认值为null
 *      list.add(123); //将123封装到Node中，创建Node对象
 *
 *      其中，Node定义为：体现了LinkedList的双向链表的说法：
 *      private static class Node<E> {
 *          E item;
 *          Node<E> next;
 *          Node<E> prev;
 *
 *          Node(Node<E>prev, E eLement，Node<E> next)
 *          {
 *          this.item = element;
 *          this.next = next;
 *          this.prev = prev;}
 *      }
 *
 * 4. Vector的源码分析: jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组
 *    在扩容方面，默认扩容为原来的数组长度的2倍。
 *
 * 面试题:ArrayList、 LinkedList. Vector三者的异同?
 * 同:三个类都是实现了List接口，存储数据的特点相同:存储有序的、可重复的数据
 * 不同:见上
 *
 * 5.List接口的常用方法
 * 增:add(Object obj)
 * 删:remove(int index) / remove(Object obj)
 * 改:set(int index,Object ele)
 * 查:get(int index)
 * 插:add(int index,Object ele)
 * 长度:size()
 * 遍历:①Iterator迭代器方式
 *     ②增强for循环
 *     ③普通for循环
 */
public class ListTest
{
    @Test       //List接口的常用方法
    public void test1()
    {
        List l = new ArrayList();
        l.add("123");

        //add(int index,Object ele)
        l.add(1,"456");

        //Boolean addAll(int index,Collection eles)
        List l1 = new LinkedList();
        l1.add("789");
        l.addAll(l1);

        //输出
        System.out.println(l);

        //get()
        System.out.println(l.get(2));
        //System.out.println(l.get(3));       //注意IndexOutOfBoundsException!

        //indexOf()     //返回第一次出现的位置
        System.out.println(l.indexOf("789"));

        //lastIndexOf() //返回最后一次出现的位置
        System.out.println(l.lastIndexOf("789"));
        System.out.println(l.lastIndexOf("78910"));     //未找到返回-1

        //Object remove(int index)       //返回指定index位置的元素并删除
        l.remove(0);

        //输出
        System.out.println(l);          //自动前移

        //Object set(int index,Object ele)
        Object set = l.set(0, "007");
        System.out.println(set);        //将被替代的对象返回

        //输出
        System.out.println(l);          //自动前移

        //List subList(int fromIndex,int toIndex)   //左闭右开区间
        List list = l.subList(0, 1);    //小心IndexOutOfBoundsException
        System.out.println(list);
    }

    @Test   //List遍历
    public void test2()
    {
        List l = new ArrayList();
        l.add("123");
        l.add(456);
        l.add(new Double(789));
        l.add(new String("10"));

        //Iterator
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object obj = iterator.next();
            System.out.println(obj);
        }

        //foreach
        System.out.println("********************");
        for(Object obj:l)
            System.out.println(obj);

        //for
        System.out.println("********************");
        for (int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i));
        }
    }
}
