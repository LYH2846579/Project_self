package com.atguigu.java.permission_modifier;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

/**
 * @author LYHstart
 * @create 2021-07-27 11:33
 */
public class Order
{
    private int orderPrivate;
    int orderDefault;
    protected int orderProtected;
    public int orderPublic;

    //类内部调用
    private void methodPrivate()
    {
        orderPrivate = 1;
        orderDefault = 2;
        orderProtected = 3;
        orderPublic = 4;
    }
    void methodDefault()
    {
        orderPrivate = 11;
        orderDefault = 12;
        orderProtected = 13;
        orderPublic = 14;
    }
    protected void methodProtected()
    {
        orderPrivate = 1;
        orderDefault = 2;
        orderProtected = 3;
        orderPublic = 4;
    }
    public void methodPublic()
    {
        orderPrivate = 1;
        orderDefault = 2;
        orderProtected = 3;
        orderPublic = 4;
    }
}
