package regular_expression.commonly_used_class;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 15:18
 *
 * 分组、捕获、反向引用
 */
public class RegExp07
{
    @Test       //匹配两个连续相同的数字
    public void test1()
    {
        String content = "1234 5245 8899 1551 3258 6666 9527";

        String regStr = "(\\d)\\1";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }

    @Test       //匹配五个连续相同的数字
    public void test2()
    {
        String content = "1234 5245 8899 1551 3258 66666 9527";

        String regStr = "(\\d)\\1{4}";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }

    @Test       //匹配个位与千位相同，百位与十位相同的四位数
    public void test3()
    {
        String content = "1234 5245 8899 1551 3258 66666 9527";

        String regStr = "(\\d)(\\d)\\2\\1";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find())
        {
            System.out.println("找到:"+matcher.group(0));
        }
    }


    /*
    思考题：
    请在字符串中检索商品编号形式如:12321-333999111这
    样的号码,要求满足前面是一-个五位数，然后一一个-号然后是一
    个九位数,连续的每三位要相同
     */
    @Test
    public void test4()
    {
        String content = "12321-3339991111";
                                                                //一定要限制好大小
        String regStr = "^[1-9]\\d{4}-(\\d)\\1{2}(\\d)\\2{2}(\\d)\\3{2}$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            System.out.println("满足规则");
        else
            System.out.println("不满足规则");
    }
}

