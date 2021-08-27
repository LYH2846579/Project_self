package com.atguigu.java_advanced_programming;

import org.junit.Test;

import java.util.*;

/**
 *  //ctrl+p  ->   提示参数类型
 *
 * Collections:操作Collection、Map的工具类
 *
 * 面试题：Collection 和 Collections的区别?
 * 附：Collection -> 存储数据的集合接口
 *    Collections -> 集合工具类
 *
 * 结构框架:
 *    Collection:
 *      List                    Set
 *          -> ArrayList            ->HashSet   --> LinkedHashSet
 *          -> LinkedList           ->TreeSet
 *          -> Vector
 *
 *    Map:
 *      -> HashMap  --> LinkedHashMap
 *      -> TreeMap
 *      -> HashTable -->Properties
 *
 * @author LYHstart
 * @create 2021-08-26 22:51
 *
 * 附：该代码涉及Collections工具类常用方法的使用、及使用Collections类中的多个synchronizedXxx()方法转换线程同步集合的过程
 */
public class collections_tool_class
{
    /*
    Collections常用方法:
        reverse(List):反转List中元素的顺序
        shuffle(List):对List集合元素进行随机排序
        sort(List):根据元素的自然顺序对指定List集合元素按升序排序
        sort(List, Comparator):根据指定的Comparator产生顺序对List集合元素进行排序
        swap(List, int, int):将指定list集合中的i元素和j处元素进行交换

        Object max(Collection):根据元素的自然排序，返回给定集合中的最大元素
        Object max(Collection, Comparator):根据Comparator指定的顺序，返回给定集合中的最大元素
        Object min(Collection):
        Object min(Collection, Comparator):
        int frequency(Collection, Object):返回指定集合中指定元素的出现次数
        void copy(List dest,List src):将src中的内容复制到dest中
        boolean replaceAll(List list, Object oldVal, Object newVal):使用新值替换List中对象的所有旧值
     */

    @Test   //与顺序有关的考虑对List的操作
    public void test1()
    {
        //reverse()
        List list = new ArrayList();
        list.add("123");
        list.add(new String("456"));
        list.add("789");
        list.add("789");
        list.add("AAA");

        print(list);
        Collections.reverse(list);
        System.out.println("*********");
        print(list);

        //shuffle()
        System.out.println("*********");
        Collections.shuffle(list);
        print(list);

        //Sort()
        System.out.println("*********");
        Collections.sort(list);
        print(list);

        //Sort(List ,Comparator)
        System.out.println("*********");
        Collections.shuffle(list);
        Collections.sort(list, new Comparator()
        {

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Integer && o2 instanceof Integer)
                {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;
                    return Integer.compare(i1,i2);
                }
                else if(o1 instanceof String && o2 instanceof String)
                {
                    return ((String) o1).compareTo((String) o2);
                }
                else
                    throw new RuntimeException("传入对象异常!");
            }
        });
        print(list);
        //System.out.println("***");

        //swap(List,int,int)
        System.out.println("*********");
        Collections.swap(list,1,2);     //IndexOutOfBoundsException
        print(list);


        //Object max(list)
        System.out.println("*********");
        System.out.println("Max:"+Collections.max(list));

        //Object min(list,Comparator)
        System.out.println("*********");
        System.out.println("Min:"+Collections.min(list, new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String)
                {
                    return ((String) o1).compareTo((String) o2);
                } else
                    throw new RuntimeException("传入对象异常!");
            }
        }));

        //frequency()       //调用equals()方法匹配
        System.out.println("*********");
        System.out.println(Collections.frequency(list, new String("789")));

        //copy()
        System.out.println("*********");
        //List list1 = new LinkedList();    //IndexOutOfBoundsException: Source does not fit in dest
        //Collections.copy(list1,list);     //初始化队列时，需要指定大小
                                        //传入大小为list.size()的数组作为
        List list1 = Arrays.asList(new Object[list.size()]);
        Collections.copy(list1,list);
        print(list1);

        //replaceAll()          //ctrl+p  ->   提示参数类型
        System.out.println("*********");
        Collections.replaceAll(list,"789",new String("1010"));
        print(list);
    }

    @Test
    public void test2()
    {
        /*
        collections类中提供了多个synchronizedXxx ().方法，
        该方法可使将指定集合包装成线程同步的集合，
        从而可以解决多线程并发访问集合时的线程安全问题
         */

        List list = new LinkedList();
        list.add("AAA");
        list.add("CCC");
        list.add("123");
        list.add("synchronized");

        List list1 = Collections.synchronizedList(list);
        print(list1);
    }


    //遍历Iterator方法
    public void print(Collection collection)
    {
        Iterator iterator = collection.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());
    }
}
