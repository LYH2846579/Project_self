package com.atguigu.java_advanced_programming.reflection.class_structure;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author LYHstart
 * @create 2021-09-05 10:14
 *
 * 获取运行时类的完整结构
 */
public class PersonTest
{
    @Test   //获取运行时类的属性
    public void test1() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //获取所有属性组  ->  获取自身+父类所有声明为public的属性
        Field[] fields = clazz.getFields();
        for(Field f : fields)
            System.out.println(f);
        System.out.println();

        //获取属性组 -> 获取到自身所有的属性(不包含父类的属性)
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f:declaredFields)
            System.out.println(f);
    }


    @Test //权限修饰符 数据类型 变量名  拆分获取
    public void test2() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");
        //获取所有属性信息
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f:declaredFields)
        {
            //获取权限修饰符
            int modifiers = f.getModifiers();   //返回int标识
            System.out.print(Modifier.toString(modifiers)+"\t");
            //获取数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + "\t");
            //获取变量名
            String name = f.getName();
            System.out.print(name + "\t");

            System.out.println();
        }
    }

    @Test   //获取全部方法
    public void test3() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //获取方法组 -> 仍然获取所有声明为public的方法
        Method[] methods = clazz.getMethods();
        for(Method m : methods)
            System.out.println(m);
        System.out.println();

        //获取方法组 -> 获取自身声明的所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method m:declaredMethods)
            System.out.println(m);
    }

    @Test   //方法解析:权限修饰符+返回值+名称+参数列表+异常处理+注解
    public void test4() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //获取方法详细内容
        Method[] methods = clazz.getMethods();
        for(Method m : methods)
        {
            //注解
            Annotation[] annotations = m.getAnnotations();
            for(Annotation a:annotations)
                System.out.println(a);
            //获取权限修饰符
            int modifiers = m.getModifiers();
            System.out.print(Modifier.toString(modifiers)+"\t");
            //获取返回值类型
            Class<?> returnType = m.getReturnType();
            System.out.print(returnType.getName()+"\t");
            //获取方法名称
            String name = m.getName();
            System.out.print(name);
            //获取参数列表    ?
            System.out.print("(");
            Class<?>[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null || parameterTypes.length == 0))
            {
                for(Class c:parameterTypes)
                    System.out.print(c.getName());
            }
            System.out.print(")");
            //获取异常信息
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if(!(exceptionTypes == null || exceptionTypes.length == 0 ))
            {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++)
                {
                    if(i == exceptionTypes.length-1)
                    {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }

                    System.out.print(exceptionTypes[i].getName()+",");
                }
            }

            System.out.println();
        }
    }

    @Test   //获取构造器内容   ->  获取到声明为public的构造器
    public void test5() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        Constructor[] constructors = clazz.getConstructors();
        for(Constructor c:constructors)
            System.out.println(c);
    }

    @Test   //获取构造器内容   ->  获取到自身的所有构造器
    public void test6() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for(Constructor c:declaredConstructors)
        {
            //System.out.println(c);
            //解析构造器信息的内容
            //1.获取权限修饰符
            int modifiers = c.getModifiers();
            System.out.print(Modifier.toString(modifiers)+"\t");
            //2.获取名称
            String name = c.getName();
            System.out.print(name+"\t");
            //获取参数列表            //参数列表直接调用toString方法即可获取int arg1等数值
            System.out.print("(");
            Parameter[] parameters = c.getParameters();
            for (int i = 0; i < parameters.length; i++)
            {
                if(i == parameters.length-1)
                    System.out.print(parameters[i].toString());
                else
                    System.out.print(parameters[i].toString()+",");
            }
            System.out.print(")");
            System.out.println();
        }
    }

    @Test   //获取运行时类的父类
    public void test7() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        //获取普通的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);     //class com.atguigu.java_advanced_programming.reflection.class_structure.Creature

        //获取带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);  //class com.atguigu.java_advanced_programming.reflection.class_structure.Creature

        //获取父类的泛型
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = type.getActualTypeArguments();
        for(Type t:typeArguments)
            System.out.println(t.getTypeName());
    }

    @Test   //获取运行时类实现的接口
    public void test8() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        Class[] interfaces = clazz.getInterfaces();
        for(Class c:interfaces)
        {
            System.out.println(c);
        }
        System.out.println();

        //获取父类实现的接口
        Class superclass = clazz.getSuperclass();

        Class[] superclassInterfaces = superclass.getInterfaces();
        for(Class c:superclassInterfaces)
        {
            System.out.println(c);
        }
    }

    @Test //获取当前运行时类所在的包
    public void test9() throws ClassNotFoundException
    {
        Class clazz = Class.forName("com.atguigu.java_advanced_programming.reflection.class_structure.Person");

        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
    }

    @Test   //获取运行时类的注解
    public void test10()
    {
        Class clazz = Person.class;

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation a:annotations)
            System.out.println(a);
    }
}
