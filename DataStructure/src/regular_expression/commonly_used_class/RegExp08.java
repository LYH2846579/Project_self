package regular_expression.commonly_used_class;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 15:35
 *
 * 处理结巴程序
 */
public class RegExp08
{
    @Test
    public void test1()
    {
        String content = "我....我要....学学学学....编程java!";

        String regStr = "(\\.{4})?(.)*(\\.{4})?(\\2)*(\\.{4})?";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        String s = matcher.replaceAll("$2");    //使用外部反向引用???   晕
        System.out.println(s);
    }

    @Test   //改进的程序
    public void test2()
    {
        String content = "我....我要....学学学学....编程java!";

        String regStr = "\\.";

        //第一步：将所有的.去除
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        String s = matcher.replaceAll("");
        System.out.println(s);
        //第二步: 去除重复元素
        regStr = "(.)\\1+";
        pattern = Pattern.compile(regStr);
        matcher = pattern.matcher(s);
        String s1 = matcher.replaceAll("$1");
        System.out.println(s1);
    }

    @Test       //plus升级
    public void test3()
    {
        String content = "我....我要....学学学学....编程java!";

        String regStr = "\\.";

        //第一步：将所有的.去除
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        String s = matcher.replaceAll("");
        System.out.println(s);
        //第二步: 去除重复元素
        String s1 = Pattern.compile("(.)\\1+").matcher(s).replaceAll("$1");
        System.out.println(s1);
    }
}
