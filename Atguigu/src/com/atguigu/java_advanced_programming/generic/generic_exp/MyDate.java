package com.atguigu.java_advanced_programming.generic.generic_exp;

/**
 * @author LYHstart
 * @create 2021-08-27 18:04
 */
public class MyDate
{
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
