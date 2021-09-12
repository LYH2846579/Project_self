package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 18:11
 *
 * 对限定符的使用进行练习
 */
public class RegExp02
{
    @Test   //*             -> 0个或者1个
    public void test1()
    {
        String content = "aa8651*s^ceh.. FsOsm18";

        String regStr = "a*8";   //*+? -> 默认与其前一个字符搭配使用

        Pattern pattern = Pattern.compile(regStr);

        Matcher matcher = pattern.matcher(content);

        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }

    @Test
    public void test2()
    {
        //{} -> 个数限定
        String content = "aa8651*s^ceh.. FsOsm18a";

        String regStr = "[a]{1,2}";   //字符a的个数为0或1

        Pattern pattern = Pattern.compile(regStr);

        Matcher matcher = pattern.matcher(content);

        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }
}
