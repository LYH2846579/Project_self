package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 18:40
 *
 * 对定位符的使用进行练习
 */
public class RegExp03
{
    @Test
    public void test1()
    {
        //指定起始字符^     ->  注意与转义字符的区别
        String content = "1s2d465f4165s9419/236@754#$s544d498 ;$%z";
        String regStr = "^[0-9]+[a-z]*";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println(matcher.group(0));       //1s -》 仅仅从首部校验
    }

    @Test
    public void test2()
    {
        //指定结束字符
        String content = "1s";
        String regStr = "^[0-9]+[a-z]$";        //必须是至少一个数字开头，一个小写字母结尾的字符串
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println(matcher.group(0));       //1s -》 仅仅从首部校验
    }

    @Test
    public void test3()
    {
        //匹配字符串结尾
        String content = "hanshunpng shan hanlehan";    //注意空格!
        String regStr = "han\\b";                       //B -> 寻找开头
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
            System.out.println(matcher.group(0));
    }
}
