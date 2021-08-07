package com.atguigu.java.error_exception;

/**
 * @author LYHstart
 * @create 2021-08-07 12:31
 */
public class MyException extends RuntimeException       //继承extends
{
    //标识符
    static final long serialVersionUID = -7034897190745761717L;

    //构造器
    public MyException()
    {

    }
    public MyException(String msg)
    {
        super(msg);
    }
}

