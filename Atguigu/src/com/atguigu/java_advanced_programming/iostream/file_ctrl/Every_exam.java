package com.atguigu.java_advanced_programming.iostream.file_ctrl;

import org.junit.Test;

import java.io.File;

import java.io.IOException;
import java.util.*;

/**
 * @author LYHstart
 * @create 2021-08-29 10:55
 */
public class Every_exam
{
    @Test
    public void test1()
    {
        //1.如何遍历Map的Key集，value集，Key-value集，使用上泛型
        Map_test map_test = new Map_test(new HashMap<String,Integer>());

        //添加
        map_test.getMap().put("Tom",17);
        map_test.getMap().put("Jerry",18);
        map_test.getMap().put("Jack",20);

        //获取Key集
        Set keySet = map_test.getMap().keySet();
        //遍历Key集
        System.out.println("遍历Key集");
        Iterator iterator = keySet.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
        System.out.println("***************");

        //获取value集
        Collection values = map_test.getMap().values();
        //遍历value集
        System.out.println("遍历value集");
        Iterator iterator1 = values.iterator();
        while(iterator1.hasNext())
            System.out.println(iterator1.next().toString());
        System.out.println("***************");

        //获取Key-value集
        Set entrySet = map_test.getMap().entrySet();
        //遍历Key-value集
        System.out.println("遍历Key-value集");
        Iterator iterator2 = entrySet.iterator();
        while(iterator2.hasNext())
            System.out.println(iterator2.next().toString());
        System.out.println("***************");
    }

    @Test
    public void test2()
    {
        //写出使用Iterator和增强for循环List<String>的代码，使用上泛型
        List<String> list = new LinkedList<>();

        //String
        list.add("123");
        list.add("456");
        list.add("789");

        //Integer
        List<Integer> list1 = new LinkedList<>();
        list1.add(456);
        list1.add(789);
        list1.add(123);

        //Iterator遍历
        List_test list_test = new List_test(list);
        list_test.List_each(list);
        System.out.println("**************");
        list_test.List_each(list1);

        //for遍历
        System.out.println("**************");
        list_test.print(list);
        System.out.println("**************");
        list_test.print(list1);
    }

    @Test
    public void test3()
    {
        //提供一个方法，用于遍历获取HashMap<String,String>中的所有value,
        //并存放在List中返回，考虑上集合中泛型的使用
        Map<String,String> map = new HashMap<>();
        map.put("Tom","CEO");
        map.put("Jerry","CFO");
        map.put("Jack","CTO");

        HashMap_test hashMap_test = new HashMap_test(map);
        List value = hashMap_test.getValue();
        Iterator iterator = value.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

    @Test
    public void test4()
    {
        //创建一个与a.txt文件同目录下的另一个文件b.txt
        File file = new File("F:\\Java\\IO\\Exam");

        if(!file.exists())
        {
            file.mkdirs();
        }
        //创建a.txt
        File file1 = new File(file,"a.txt");
        if (file1.exists())
        {
            try
            {
                file1.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                System.out.println("文件创建成功!");
            }
        }

        //创建b.txt
        File file2 = new File(file1.getPath()+"b.txt");
        if (!file2.exists())
        {
            try
            {
                file2.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test5()
    {
        //Map接口中常用的方法
        /*
        Object put(Object Key,Object value);
        void putAll(Map m)
        Object remove(Object key)
        void clear()

        Object get(Object key)
        boolean containsKey(Object key)
        boolean containsValues(Object value)
        int size()
        boolean isEmpty()
        boolean equals()

        Set keySet()
        Collection values()
        Set entrySet()
        */
    }
}


//1.
class Map_test<K,V>     //泛型类
{
    private Map<K,V> map;

    public Map_test() {
    }
    public Map_test(Map<K, V> map) {
        this.map = map;
    }
    public Map<K, V> getMap() {
        return map;
    }
}

//2.
class List_test<T>
{
    private List<T> list;

    public List_test() {
    }
    public List_test(List<T> list) {
        this.list = list;
    }

    //泛型遍历方法Iterator
    public <U> void List_each(List<U> list)
    {
        Iterator<U> iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

    //增强for
    public void print(List<T> list)
    {
        for(T temp:list)
            System.out.println(temp.toString());
    }
}

//3.
class HashMap_test<K,V>
{
    private Map<K,V> map = null;

    public HashMap_test() {
    }
    public HashMap_test(Map<K, V> map) {
        this.map = map;
    }

    //获取value并存储在List中保存
    public List<V> getValue()
    {
        if(this.map == null)
            throw new RuntimeException("map对象错误");
        else
        {
            Collection<V> values = map.values();

            List<V> list = new LinkedList<>();

            Iterator<V> iterator = values.iterator();
            while (iterator.hasNext())
            {
                list.add(iterator.next());
            }
            return list;
        }
    }
}
