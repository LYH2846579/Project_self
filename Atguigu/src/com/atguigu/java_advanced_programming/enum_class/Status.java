package com.atguigu.java_advanced_programming.enum_class;

/**
 * @author LYHstart
 * @create 2021-08-21 9:21
 *
 * 修改案例三中的Status类
 */

//原Status类
/*
public class Status
{
    private final String NAME;

    public Status(String NAME) {
        this.NAME = NAME;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getNAME()
    {
        return NAME;
    }

    @Override
    public String toString() {
        return "Status{" +
                "NAME='" + NAME + '\'' +
                '}';
    }
}
*/

enum Status
{
    //默认对象
    FREE("FREE"),
    BUSY("BUSY"),
    VOCATION("VOCATION");

    //私有化属性
    private final String NAME;

    //私有化构造器
    private Status(String NAME)
    {
        this.NAME = NAME;
    }

    //属性方法
    public String getNAME() {
        return NAME;
    }
}

/*  //另一种方式
public enum Status
{
    FREE,BUSY,VOCATION;
}
 */
