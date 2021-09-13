package regular_expression.commonly_used_class;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 14:51
 */
public class MatcherMethod
{
    @Test   //start()  end()
    public void test1()
    {
        String content = "Hhello jack java hello Hello HellO";
        String regStr = "hello";

        Pattern pattern = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
        {
            System.out.println("**********");
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("找到:"+content.substring(matcher.start(),matcher.end()));
        }
    }

    @Test   //整体匹配方法
    public void test2()
    {
        String content = "Hello jack java hello Hello HellO";
        String regStr = "hello.*";

        Pattern pattern = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        if(matcher.matches())
        {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(content.substring(matcher.start(),matcher.end()));
        }
    }

    @Test
    public void test3()
    {
        String content = "Hello jack java hello Hello HellO";
        String regStr = "hello.*";

        Pattern pattern = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        //将所有的hello(区分大小写) 置换为Jerry    ->修改之前的匹配器
        regStr = "hello";
        pattern = Pattern.compile(regStr);      //此时已经区分大小写
        matcher = pattern.matcher(content);
        //返回的结果即为被替换掉的字符串 -> 原字符串不会被修改
        String jerry = matcher.replaceAll("Jerry");

        System.out.println(jerry);
    }
}
