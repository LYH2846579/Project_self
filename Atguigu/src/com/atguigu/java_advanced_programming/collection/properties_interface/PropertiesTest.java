package com.atguigu.java_advanced_programming.collection.properties_interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author LYHstart
 * @create 2021-08-25 12:53
 */
public class PropertiesTest
{
    //Properties:常用来处理配置文件，key和value都是String类型
    public static void main(String[] args)
    {
        FileInputStream fis = null;
        try
        {
            Properties pros = new Properties();
            fis = new FileInputStream("jdbc.properties");
            pros.load(fis);

            String name = pros.getProperty("Name");
            String passWord = pros.getProperty("PassWord");

            System.out.println("Name:"+name+", PassWord:"+passWord);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if(fis != null)
            {
                try
                {
                    fis.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
