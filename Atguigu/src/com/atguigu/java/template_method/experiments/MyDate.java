package com.atguigu.java.template_method.experiments;

/**
 * @author LYHstart
 * @create 2021-08-03 8:32
 */
public class MyDate
{
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "MyDate{" + year +
                "年" + month +
                "月" + day +
                "日}";
    }
}
