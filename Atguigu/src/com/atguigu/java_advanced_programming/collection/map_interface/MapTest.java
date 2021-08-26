package com.atguigu.java_advanced_programming.collection.map_interface;

import org.junit.Test;

import java.util.*;

/**
 * @author LYHstart
 * @create 2021-08-24 11:29
 *
 * 一、Map的实现类的结构：
 * /----Map:双列数据，存储key-value对的数据---类似于高中的函数: y = f(x)
 *      /----HashMap:作为Nap的主要实现类;线程不安全的，效率高;存储nuLL的key和value
 *          /----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *              原因:在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素.
 *              对于频繁的遍历操作，此类执行效率高于HashMap .
 *      /----TreeMap:保证按照添加的key-vaLue对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                   底层使用红黑树实现
 *      /----Hashtable:作为古老的实现类;线程安全的，效率低;不能存储null的key和value
 *          / ----Properties:常用来处理配置文件。key和value都是String类型
 *
 *      HashMap的底层：数组+链表 （JDK7及之前）
 *                    数组+链表+红黑树（JDK8）
 *      面试题：
 *      1.HashMap的底层实现原理?
 *      2.HashMap和Hashtable的异同？
 *      3.CurrentHashMap和Hashtable的异同？
 *
 * 二、Map结构的理解
 *  Map中的key:无序的、不可重复的，使用Set存储所有的key ---> key所在的类要重写equals()和nashCode()(以HashNap为例)
 *  Map中的vaLue:无序的、可重复的，使用collection存储所有的value --->vaLue所在的类要重写equals()
 *  一个键值对: key-value构成了一个Entry对象。
 *  Map 中的entry:无序的、不可重复的，使用set存储所有的entry
 *
 *
 * 三、HashMap的底层实现原理？以JDK7为例说明：
 *    HashMap map = new HashMap();
 *    在实例化以后，底层创建了长度是16的一维数组Entry[] table。
 *    ...可能已经执行过多次put. . .
 *    首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置。
 *    如果此位置上的数据为空，此时的key1-value1添加成功。----情况1
 *    如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据的哈希值:
 *      如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。----情况2
 *      如果key1的哈希值和已经存在的某一个数据(key2-vaLue2)的哈希值相同，继续比较。调用Rey1所在类的equals(key2)方法
 *          如果equals()返回faLse:此时key1-vaLue1添加成功。----情况3
 *          如果equals()返回true:使用value1替换vaLue2 。
 *
 *     补充:关于情况2和情况3:此时key1-value1和原来的数据以链表的方式存储。
 *
 *     在不断增加过程中，会涉及到扩容问题，当超出临界值(插入位置非空的时候)
 *          默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。
 *
 *     JDK8 相较于JDK7在底层实现的不同：
 *
 *     jdk8相较于jdk7在底层实现方面的不同:
 *     1. new HashMap():底层没有创建一个长度为16的数组
 *     2. jdk8底层的数组是:Node[].而非Entry[]
 *     3．首次调用put()方法时，底层创建长度为16的数组
 *     4. jdk7底层结构只有:数组+链表。jdk8中底层结构:数组+链表+红黑树。
 *          当数组的某一个索引位置上的元素以链表形式存在的数据个数>8且当前数组的长度>64时，
 *          此时此索引位置上的所有数据改为使用红黑树存储。
 *
 *
 *     HashMap源码中重要的常量:
 *     DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
 *     DEFAULT_LOAD_FACTOR: HashMap的默认加载因子:0.75
 *     threshold:扩容的临界值，=容量*填充因子:16 *0.75 =>12
 *     TREEIFY_THRESHOLD: Bucket中链表长度大于该默认值，转化为红黑树:8
 *     MIN_TREEIFY_CAPACITY:桶中的Node被树化时最小的hash表容量:64
 *
 *
 *     总结：常用方法
 *     增：Object put(Object key,Object value)
 *     删：Object remove(Object key)
 *     改：Object put(Object key,Object value)
 *     查：Object get(Object key)
 *     长度：int size()
 *     遍历：keySet() \ values() \ entrySet()
 */
public class MapTest
{
    @Test
    public void test1()
    {
        /*
        添加、删除、修改操作
        put(),putAll(),remove(),clear()
         */
        Map map = new HashMap();
        //添加put(Object key,Object value)
        map.put("AA",123);
        //修改
        map.put("AA",1);
        map.put("BB",12);
        map.put(45,23);
        System.out.println(map);    //{AA=1, BB=12, 45=23}

        Map map1 = new LinkedHashMap();
        map1.put(new String("CC"),18);
        map1.put(new String("DD"),19);

        //putAll(Map m)
        map.putAll(map1);
        System.out.println(map);

        //Object remove(Object key)     //将key对应的value值返回
        Object remove = map.remove("DD");
        System.out.println(remove);

        //clear()
        System.out.println(map1);
        map1.clear();
        System.out.println(map1);
    }

    @Test
    public void test2()
    {
        /*
        元素查询
        get(),containsKey(),containsValue(),size(),isEmpty(),equals()
         */
        Map map = new HashMap();

        map.put("AA",17);
        map.put("CC",18);
        map.put("BB",19);
        map.put(new String("DD"),20);

        //Object get(Object key)    获取指定的key对应的value
        Object dd = map.get("DD");
        System.out.println(dd);

        //boolean containsKey(Object key)   是否包含指定的Key
        boolean containsKey = map.containsKey("DD");
        System.out.println(containsKey);

        //boolean containsValue(Object value)   是否包含指定的value
        boolean containsValue = map.containsValue(20);
        System.out.println(containsValue);

        //int size()
        System.out.println(map.size()); //返回map中key-value键值对的个数

        //boolean isEmpty()         //判断是否为空
        System.out.println(map.isEmpty());

        //boolean equals(Object obj)    判断当前map是否和参数对象obj相等
        System.out.println(map.equals(new HashMap()));
    }

    @Test
    public void test3()
    {
        /*
        元视图操作
        keySet(),values(),entrySet()
         */
        Map map = new HashMap();

        map.put("AA",17);
        map.put("CC",18);
        map.put("BB",19);
        map.put(new String("DD"),20);

        //Set keySet()      返回所有key构成的Set集合
        Set set = map.keySet();
        System.out.println(set);

        //Collections values()  返回所有value构成的Collection集合
        Collection values = map.values();
        System.out.println(values);

        //Set entrySet()    返回所有key-value对构成的Set集合
        Set entrySet = map.entrySet();
        System.out.println(entrySet);

    }

    @Test
    public void test4()
    {
        /*
        使用原始图操作遍历数据
         */

        //Set keySet()
        Map map = new HashMap();
        map.put("AA",17);
        map.put("CC",18);
        map.put("BB",19);
        map.put(new String("DD"),20);

        Set keySet = map.keySet();
        System.out.println(keySet);

        //Iterator遍历
        Iterator iterator = keySet.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toString());


        //Collection values()
        Collection values = map.values();
        System.out.println(values);

        //Iterator遍历
        Iterator iterator1 = values.iterator();
        while(iterator1.hasNext())
            System.out.println(iterator1.next().toString());


        //Set entrySet()
        Set entrySet = map.entrySet();
        System.out.println(entrySet);

        //Iterator遍历
        Iterator iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext())
            System.out.println(iterator2.next().toString());

        //获取每一个entry的key-value
        Iterator iterator3 = map.entrySet().iterator();
        while(iterator3.hasNext())
        {
            Object obj = iterator3.next();           //ClassCastException
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
    }
}