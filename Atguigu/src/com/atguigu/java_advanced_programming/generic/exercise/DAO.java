package com.atguigu.java_advanced_programming.generic.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author LYHstart
 * @create 2021-08-28 10:38
 */
public class DAO<T>
{
    private Map<String,T> map;

    public DAO(Map<String, T> map) {
        this.map = map;
    }

    //保存T类型的对象到Map成员变量中
    public void save(String id,T entity)
    {
        map.put(id,entity);
    }
    //从map中获取id对应的对象
    public T get(String id)
    {
        return map.get(id);
    }
    //替换map中key为id的内容，改为entity对象
    public void update(String id,T entity)
    {
        map.replace(id,entity);
    }
    //返回map中存放的所有T对象
    public List<T> list()
    {
        //错误写法：①map.values返回的为Collection<T> ②List中不允许存在重复元素
        //return (List<T>) map.values();

        //遍历
        List<T> list = new ArrayList<>();
        Iterator<T> iterator= map.values().iterator();
        while(iterator.hasNext())
            list.add(iterator.next());
        return list;
    }
    //删除指定id对象
    public void delete(String id)
    {
        map.remove(id);
    }
}
