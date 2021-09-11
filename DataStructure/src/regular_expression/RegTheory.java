package regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-11 17:12
 *
 * 分析JAVA的正则表达式的底层实现(重要.)
 *
 * matcher.find() 完成的任务
 * 1. 根据指定的规则，定位满足规则的子字符串(如1998)
 * 2. 找到后，将 子字符串的开始的索引记录到matcher对象的属性 int[] groups;
 *    groups[0] = 0,把该子字符串的结束的索引+1的值记录到groups[1] = 4
 * 3. 同时记录 oldLast 的值为子字符串的结束的索引+1 的值，即为4,()下次执行find方法时，就从4开始匹配
 *
 * matcher.group(0)分析：
 * 源码：
 * public String group(int group) {
 *         if (first < 0)
 *             throw new IllegalStateException("No match found");
 *         if (group < 0 || group > groupCount())
 *             throw new IndexOutOfBoundsException("No group " + group);
 *         if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
 *             return null;
 *         return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
 *     }
 *
 * 1. 根据 groups[0]=31 和 groups[1]=35 的记录的位置，从content开始截取子字符串返回
 *    就是[31,,35) 左闭右开区间
 *
 * 若再次调用find() -> 仍按照以上步骤执行 -》覆盖原有的groups[0]和groups[1]
 *
 *
 *
 * 若考虑分组的情况(即在创建匹配规则的时候使用小括号进行分组)
 * 1.什么是分组：比如(\\d\\d)(\\d\\d)，正则表达式中出现小括号()表示分组
 * 2.案例分析：
 *   1) 不妨设regStr = "(\\d\\d)(\\d\\d)"  -> 匹配到的第一个字符串为(19)(98)
 *   2) groups[0] = 0 ,groups[1] = 4
 *   3) groups[2] = 0 ,groups[3] = 2
 *   4) groups[4] = 2 ,groups[5] = 4
 *   5) 若有更多分组，继续向下记录
 * 附：matcher.group(0) -> 匹配到的整体的字符串
 *    matcher.group(1) -> 匹配到的整体的字符串的第一个分组
 *    matcher.group(2) -> 匹配到的整体的字符串的第二个分组
 *    ...
 *    注意：不可以超额取出分组!
 */
public class RegTheory
{
    public static void main(String[] args)
    {
        String content = "1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，Sun公司发布了"+
                "第二代Java平台(简称为Java2)的3个版本:J2NE (Java2 Micro Edition，Java2平台的微型"+
                "版），应用于移动、无线及有限资源的环境;J2SE (Java 2 Standard Edition，Java 2平台的"+
                "标准版），应用于桌面环境;J2EE (Java 2Enterprise Edition，Java 2平台的企业版），应"+
                "用3443于基于Java的应用服务器。Java 2平台的发布，是Java发展过程中最重要的一个"+
                "里程碑，标志着Java的应用开始普及9889 ";

        //目标：匹配所有四个数字
        //说明
        //1. \\d表示一个任意的数字
        String regStr = "(\\d\\d)(\\d\\d)";         //\\d\\d\\d\\d
        //创建模式对象[即正则表达式对象]
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器
        //说明：创建匹配器matcher,按照正则表达式规则取匹配content字符串
        Matcher matcher = pattern.matcher(content);

        //开始匹配
        /**
         *

         */
        while(matcher.find())
        {
            System.out.println("找到："+matcher.group(0));
        }
    }
}





















