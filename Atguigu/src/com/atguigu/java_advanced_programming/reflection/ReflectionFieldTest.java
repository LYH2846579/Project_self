package com.atguigu.java_advanced_programming.reflection;

import com.atguigu.java_advanced_programming.reflection.class_structure.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author LYHstart
 * @create 2021-09-05 16:11
 *
 * 调用运行时类中指定的结构:属性、方法、构造器
 *
 * 属性：
 *  1.设置当前属性的值  set():参数1：获取那个对象的属性   参数2：将此属性值设置为多少
 *  2.获取当前属性的值  get():参数1 获取那个对象的当前属性值
 *
 * 方法：clazz.getDeclaredMethod("***",参数类型.class);
 *  1.invoke() ： 调用当前方法
 *
 */
public class ReflectionFieldTest
{
    @Test   //获取属性
    public void test1() throws Exception, NoSuchFieldException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //创建运行时类的对象
        Object o = clazz.newInstance();
        Person p = (Person) clazz.newInstance();

        //获取指定的属性
        //获取公有属性
        Field id = clazz.getField("id");
        id.set(p,3715);
        System.out.println(p);

        //获取默认属性    //IllegalAccessException!
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        //Field age = clazz.getField("age");    //NoSuchFieldException
        age.set(p,19);
        System.out.println(p);

        //获取私有化属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p,"Tom");
        System.out.println(name.get(p));    //获取那个对象的当前属性值
        System.out.println(p);
    }

    @Test   //操作运行时类指定的方法
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //获取运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取public方法
        Method show = clazz.getMethod("show");
        show.invoke(p); //Object obj + 参数列表

        //获取private方法
        Method showNation = clazz.getDeclaredMethod("showNation",String.class);
        showNation.setAccessible(true);
        Object invoke = showNation.invoke(p, "中国");
        //由于showNation()的返回值为String
        String str = (String) invoke;
        System.out.println(str);

        //获取静态方法
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        Object invoke1 = showDesc.invoke(p);
        Object invoke2 = showDesc.invoke(null);
        System.out.println(invoke1);    //null  ->  若无返回值，为null
        System.out.println(invoke2);    //静态方法，Object可以为null !
    }

    //一般采用 ->  newInstance()构造器
    @Test   //调用某一个具体的构造器(调用私有构造器)
    public void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person jerry = (Person) constructor.newInstance("Jerry");

        System.out.println(jerry);
    }
}
