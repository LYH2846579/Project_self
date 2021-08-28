package com.atguigu.java_advanced_programming.generic.dao;

import java.util.List;

/**
 * @author LYHstart
 * @create 2021-08-28 8:57
 *
 * 泛型编程使用
 *
 * DAO:data(base) access object
 *
 * 进行数据库操作时，可以将每一张表格视为一个类，而对每一张表格的操作过程是非常类似的
 * 因此可以使用泛型来实现对多张表不同数据类型的增删改查
 */
public class DAO<T>
{
    //添加一条记录
    public void add(T t)
    {

    }
    //删除一条记录
    public boolean remove(T t)
    {
        return false;
    }
    //修改一条记录
    public void update(int index,T t)
    {

    }
    //查询一条记录
    public T getIndex(int index)
    {
        return null;
    }
    //查询多条记录
    public List<T> getForList(int index)
    {
        return null;
    }
}
