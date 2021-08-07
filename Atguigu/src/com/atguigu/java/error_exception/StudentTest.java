package com.atguigu.java.error_exception;

/**
 * @author LYHstart
 * @create 2021-08-07 11:46
 *
 * 手动抛出异常，一般为RunTimeException或者Exception
 */
public class StudentTest
{
    public static void main(String[] args)
    {
        Student s = new Student();
        try
        {
            s.regist(-1);
        } catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

class Student
{
    private int id;

    public void regist(int id) throws Exception
    {
        if(id > 0)
        {
            this.id = id;
        }
        else
        {
            //throw new Exception("您输入的数据非法!");
            throw new MyException("不可以输入负数!");
        }
    }
}
