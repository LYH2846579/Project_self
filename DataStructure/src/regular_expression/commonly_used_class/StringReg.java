package regular_expression.commonly_used_class;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-13 16:03
 *
 * 在String中使用正则表达式  -> 替换、匹配、分割
 * String.replaceAll() 和 Matcher.replaceAll()相似，均不会改变原有的字符串!
 */
public class StringReg
{
    @Test
    public void test1()
    {
        String content = "2000年5月，JDK1.3、JDK1.4和J2SE1.3相继发布，几周后其获得了Apple公司Mac OS X的工业标准的支持。" +
                         "2001年9月24日，J2EE1.3发布。2002年2月26日，J2SE1.4发布。自此Java的计算能力有了大幅提升，" +
                         "与J2SE1.3相比，其多了近62%的类和接口。";
        String regStr = "JDK1\\.[34]";

        String jdk = Pattern.compile(regStr).matcher(content).replaceAll("JDK");
        System.out.println(jdk);
    }

    @Test       //在String中使用正则表达式 -》 替换
    public void test2()
    {
        String content = "2000年5月，JDK1.3、JDK1.4和J2SE1.3相继发布，几周后其获得了Apple公司Mac OS X的工业标准的支持。" +
                "2001年9月24日，J2EE1.3发布。2002年2月26日，J2SE1.4发布。自此Java的计算能力有了大幅提升，" +
                "与J2SE1.3相比，其多了近62%的类和接口。";
        String regStr = "JDK1\\.[34]";

        String jdk = content.replaceAll(regStr, "JDK");
        System.out.println(jdk);
        System.out.println(content);
    }

    @Test       //判断一个手机号，必须要求是以138/139开头的      //整体匹配!!!
    public void test3()
    {
        //String str = "19819660666";
        String str = "13869451399";

        String regStr = "^13[89](\\d{8})$";     //一定注意是\\d{8}   !!!

        /*
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
        {
            System.out.println(matcher.group(0));
        }
        */
        boolean matches = str.matches(regStr);
        if(matches)
            System.out.println("符合规则");
        else
            System.out.println("不符合规则");

    }

    @Test   //String的分割功能
    public void test4()
    {
        //要求按照 # 或者 - 或者 ~ 或者数字
        String str = "hello#abc-jack12smith~北京";

        String regStr = "#|-|~|\\d+";       //这里#甚至可以不转义!
        String[] split = str.split(regStr);
        for(String s:split)
            System.out.println(s);
    }
}
