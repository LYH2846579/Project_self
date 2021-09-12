package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 16:02
 *
 * 对于字符匹配符使用练习
 */
public class RegExp01
{
    @Test
    public void test1()
    {
        //练习使用[]    :寻找包含在[]内的任何一个字符
        String content = "456asdc#q%($);";

        //创建匹配原则
        String regStr = "[dc]";
        //创建匹配模式
        Pattern pattern = Pattern.compile(regStr);
        //匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }

    @Test
    public void test2()
    {
        //练习使用[^]   :匹配[]内不包含的字符
        String content = "456asdc#q%($);";
        //生成匹配原则
        String regStr = "[^dc]";
        //创建匹配模式对象
        Pattern pattern = Pattern.compile(regStr);
        //构建匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到"+matcher.group(0));
        }
    }

    @Test
    public void test3()
    {
        //练习使用-   :匹配-两侧字符中间的所有字符 -> 包括两端
        String content = "456asdcz#q%($);";
        //生成匹配原则
        String regStr = "[a-z]";
        //创建匹配模式对象
        Pattern pattern = Pattern.compile(regStr);
        //构建匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到"+matcher.group(0));
        }
    }

    @Test
    public void test4()
    {
        //练习使用.   :匹配除了\n之外的任意字符    ->  在[]之外使用
        String content = "456asdcw#q%($);";
        //生成匹配原则
        String regStr = "..";       //若匹配. -> \\.
        //创建匹配模式对象
        Pattern pattern = Pattern.compile(regStr);
        //构建匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到"+matcher.group(0));
        }
    }

    @Test   //实现不区分大小写  ->  方式一
    public void test5()
    {
        String content = "456asdAcw#q%($);";
        //生成匹配原则
        String regStr = "(?i)asda";     //(?i)
        //创建匹配模式对象
        Pattern pattern = Pattern.compile(regStr);
        //构建匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到" + matcher.group(0));
        }
    }

    @Test   //实现不区分大小写
    public void test6()
    {
        String content = "456asdAcw#q%($);";
        //生成匹配原则
        String regStr = "asda";
        //创建匹配模式对象
        Pattern pattern = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);
        //构建匹配器
        Matcher matcher = pattern.matcher(content);

        //匹配
        while(matcher.find())
        {
            System.out.println("找到" + matcher.group(0));
        }
    }
}
