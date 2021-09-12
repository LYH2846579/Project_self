package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 15:13
 *
 * 演示转义字符的使用
 */
public class ExcapeCharacter
{
    @Test
    public void test1()
    {
        String content = "abc$(abc(123(";
        //使用正则表达式匹配(
        String regStr = "\\(";
        //创建匹配模式
        Pattern pattern = Pattern.compile(regStr);
        //创建生成器 ->  放入匹配文件
        Matcher matcher = pattern.matcher(content);

        //开始匹配
        while(matcher.find())
        {
            System.out.println(matcher.group(0));
        }
    }
}
