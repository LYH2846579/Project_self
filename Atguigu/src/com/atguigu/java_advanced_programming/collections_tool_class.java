package com.atguigu.java_advanced_programming;

/**
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
}
