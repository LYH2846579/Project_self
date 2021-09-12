package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 19:10
 */
public class RegExp04
{
    @Test   //非命名分组
    public void test1()
    {
        String content = "s1e8fscr87 3#d7c^&v 4561dw8 e51c6168";
        String regStr = "(\\d\\d)(\\d\\d)";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println(matcher.group(0));
    }

    @Test   //命名分组
    public void test2()
    {
        String content = "s4c1r9841fd8v1e8e4v6849*ve )( sc";
        String regStr = "(?<Hello1>\\d\\d)(?<Hello2>\\d\\d)";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
            System.out.println("第一组:"+matcher.group("Hello1"));
            System.out.println("第二组:"+matcher.group("Hello2"));
        }

    }

}
