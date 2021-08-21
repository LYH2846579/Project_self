package com.atguigu.java_advanced_programming.enum_class;

import org.junit.Test;

import java.sql.SQLOutput;

/**
 * @author LYHstart
 * @create 2021-08-20 20:03
 *
 * 使用enum关键字来定义枚举类
 * Enum类常用方法:values()、valuesOf()、toString()
 * 枚举类实现接口:
 *      情况一:实现接口，在ehum类中实现抽象方法
 *      情况二:让枚举类的对象分别实现接口中的抽象方法
 */
public class SeasonTest1
{
    public static void main(String[] args) {
        Season1 season1 = Season1.SPRING;
        System.out.println(season1);            //SPRING
    }

    @Test   //Enum类常用方法
    public void test1()
    {
        //values()
        Season1[] season1 = Season1.values();
        for (int i = 0; i < season1.length; i++)
        {
            System.out.println(season1[i]);
        }
        System.out.println("**********************");
        //Thread values
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++)
        {
            System.out.println(values1[i]);
        }

        System.out.println("**********************");
        //valueOf(String objName):返回枚举类中对象名是objName的对象。
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
        //如果没有objName的枚举类对象，则抛异常: ILLegalArgumentException
        //Season1 winter1 = Season1.valueOf("Winter");
        //System.out.println(winter1);

        //实现接口方法
        System.out.println("**********************");
        Season1 season11 = Season1.SUMMER;
        season11.show();
        season11.check();
    }
}

//实现接口
interface Info
{
    void show();
    void check();
}

//使用enum自定义枚举类
enum Season1 implements Info
{
    //提供当前枚举类对象     多个对象之间以","分隔  末尾以";"结尾
    SPRING("春天","春暖花开")             //接口方法对象单独实现
            {
                @Override
                public void check() {
                    System.out.println("这是春天");
                }
            },
    SUMMER("夏天","夏虫语冰")
            {
                @Override
                public void check() {
                    System.out.println("这是夏天");
                }
            },
    AUTUMN("秋天","秋风萧瑟")
            {
                @Override
                public void check() {
                    System.out.println("这是秋天");
                }
            },
    WINTER("冬天","冰天雪地")
            {
                @Override
                public void check() {
                    System.out.println("这是冬天");
                }
            };

    //私有属性
    private final String seasonName;
    private final String seasonDesc;

    //构造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //get & set方法
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    //重写接口中的抽象方法
    @Override
    public void show() {
        System.out.println("Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +'}');
    }

    //重写toString方法          默认打印枚举类对象名称
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }
}
