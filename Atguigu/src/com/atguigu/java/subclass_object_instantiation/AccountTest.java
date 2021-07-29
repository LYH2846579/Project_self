package com.atguigu.java.subclass_object_instantiation;

/**
 * @author LYHstart
 * @create 2021-07-28 9:24
 */
public class AccountTest
{
    public static void main(String[] args)
    {
        CheckAccount c = new CheckAccount(1122,20000,0.045,5000);
        c.withdrow(5000);
        c.withdrow(18000);
        c.withdrow(3000);
    }
}
