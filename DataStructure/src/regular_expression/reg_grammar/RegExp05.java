package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 19:57
 *
 * 学习了解一些不常用的分组
 */
public class RegExp05
{
    @Test       //(?:pattern)   -> 不能捕获分组
    public void test1()
    {
        //找到韩顺平教育、韩顺平老师、韩顺平同学
        String content = "hello韩顺平教育 jack韩顺平老师 韩顺平同学Hello";
        String regStr = "韩顺平(?:教育|老师|同学)";  //此代码与下一行代码等价
        //String regStr = "韩顺平教育|韩顺平老师|韩顺平同学";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println("找到:"+matcher.group(0));
    }

    @Test
    public void test2()
    {
        //找到韩顺平教育、韩顺平老师中包含有的 韩顺平 关键字  -> 韩顺平同学中包含的不计入
        String content = "hello韩顺平教育 jack韩顺平老师 韩顺平同学Hello";
        String regStr = "韩顺平(?=教育|老师)";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println("找到:"+matcher.group(0));
    }

    @Test
    public void test3()
    {
        //找到韩顺平同学中包含有的 韩顺平 关键字  -> 韩顺平教育、韩顺平老师中包含的不计入
        String content = "hello韩顺平教育 jack韩顺平老师 韩顺平同学Hello";
        String regStr = "韩顺平(?!教育|老师)";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println("找到:"+matcher.group(0));
    }
}
