package regular_expression.commonly_used_class;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 14:31
 *
 * Pattern.matches -> 从字符串头部开始整体匹配
 *
 * 1.该方法可以用来对匹配方法进行改进升级，即当仅需判断是否满足要求时，使用matches方法
 * 2.该方法的底层原理是调用Matcher.matches()方法进行处理
 */
public class PatternMethod
{
    @Test   //对matches方法使用练习
    public void test1()
    {
        String content = "Hello hello java hello jack han";
        String regStr = "hello";

                                        //匹配模式  字符串
        boolean matches = Pattern.matches(regStr, content);
        System.out.println(matches);    //false

    }

    @Test
    public void test2()
    {
        //matches方法是对整体的匹配方式 -》 返回boolean
        String content = "Hello hello java hello jack han";
        String regStr = "Hello.*";
        boolean matches = Pattern.matches(regStr, content);
        System.out.println(matches);    //true
    }

    @Test   //对匹配URL的方式进行优化处理 -> 整体匹配甚至可以不加首尾定位符
    public void test3()
    {
        String content = "https://www.bilibili.com/video/BV1Eq4y1E79W?p=18";
        String regStr = "^((https?)://)([\\w-]+\\.)+[\\w-]+(\\/[\\w-.*&%?/=]*)?$";

        boolean matches = Pattern.matches(regStr, content);
        System.out.println(matches);
    }
}
