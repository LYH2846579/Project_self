package regular_expression.reg_grammar;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-12 20:27
 */
public class RegExp06
{
    @Test   //汉字
    public void test1()
    {
        //汉字    ->  . ?不对  -> \u4e00-\u9fa5
        String content = "函";
        String regStr = "^[\u4e00-\u9fa5]+$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            System.out.println("满足格式");
        else
            System.out.println("不满足格式");
    }

    @Test   //邮政编码
    public void test2()
    {
        String content = "252100";
        String regStr = "^[1-9]\\d{5}+$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            System.out.println("满足格式");
        else
            System.out.println("不满足格式");
    }

    @Test   //QQ号码
    public void test3()
    {
        String content = "1557965684";
        String regStr = "^[1-9]\\d{4,9}+$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            System.out.println("满足格式");
        else
            System.out.println("不满足格式");
    }

    @Test   //手机号码
    public void test4()
    {
        String content = "19819660666";
        String regStr = "^1[3,4,5,7,8,9][0-9]{9}$";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find())
            System.out.println("满足格式");
        else
            System.out.println("不满足格式");
    }

    /*
    https://www.bilibili.com/video/BV1Eq4y1E79W?p=16&spm_id_from=pageDriver
    https://translate.google.cn/?sl=zh-CN&tl=zh-CN&text=INSENSITIVE&op=translate
    https://blog.csdn.net/netyeaxi/article/details/107054479
    https://blog.csdn.net/gao_zhennan/article/details/115700791
     */

    @Test   //URL
    public void test5()
    {

    }
}
