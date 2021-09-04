package com.atguigu.java_advanced_programming.reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-09-04 18:36
 *
 * ※ properties和ClassLoader
 * //使用ClassLoader加载配置文件
 */
public class ClassLoaderTest
{
    @Test   //properties加载
    public void test1() throws IOException
    {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("F:\\Java\\Project_self\\jdbc.properties");
        pros.load(fis);     //Properties加载流资源

        String name = pros.getProperty("Name");
        String passWord = pros.getProperty("PassWord");
        System.out.println("Name:"+name+", PassWord:"+passWord);
    }

    @Test   //使用类加载器加载
    public void test2() throws IOException
    {
        Properties pros = new Properties();
        //获取一个系统类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream("F:\\Java\\Project_self\\jdbc.properties");
        pros.load(is);

        String name = pros.getProperty("Name");
        String passWord = pros.getProperty("PassWord");
        System.out.println("Name:"+name+", PassWord:"+passWord);
    }
}

