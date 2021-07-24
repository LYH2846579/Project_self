package JAVA基础学习;

import javax.sound.midi.Soundbank;
import java.util.zip.CheckedOutputStream;

/**     文档注释
 * @author LYHstart
 * @create 2021-07-24 16:05
 */

public class Kuang
{
    //常量定义  final       常量定义一般用大写字母表示
    static final double PI = 3.1415926;

    //类变量 static
    static double salary = 2500;

    //实例变量：从属于对象，若不自行初始化，该值即为类型默认值 0 0.0 null false
    //除去基本数据类型,其余的默认值均为null
    String name;
    int age;
    public static void main(String[] args)
    {
        //运算符                       //快捷键: ctrl+D       复制当前行到下一行
        //long l = 12123L;            //规范:L
        //  JAVA支持自增自减运算符      //++,--
        double d1 = Math.pow(2,3);
        //逻辑运算符     && || !     短路运算符
        //短路运算
        int a = 5;
        boolean b = (a<4)&&(a++>4);   //前面为假，不执行后续运算
        boolean c = (a<4)&(a++>4);    //无论真假，均执行后续运算
        System.out.println(b);
        System.out.println(a);
        //位运算 2*8 -> 2*2*2*2
        System.out.println(2<<3);     //左移运算
        //字符串连接符
        int i1 = 10;
        int i2 = 20;
        System.out.println(""+i1+i2);
        System.out.println(i1+i2+"");

        //三元运算符
        int score = 80;        //  true  false
        String type = score<60 ? "不及格":"及格";
        System.out.println(type);
        /*

        *****************************************************************

        //附:变量命名规范
        //① 所有变量、方法、类名定义要 "见名知意"
        //② 类成员变量: 首字母小写和驼峰原则   monthSalary
        //③ 局部变量: 首字母小写和驼峰原则
        //④ 常量:大写字母和下划线             MAX_VALUE
        //⑤ 类名:首字母大写和驼峰原则          Man,GoodMan
        //⑥ 方法名:首字母小写和驼峰原则        run(),runRun()
        //⑦ 包名:所有字母君=均小写            package
        //局部变量      //JAVA为强类型语言(一旦定义变量若不强制转换，该变量类型不会改变；使用变量前需定义) 输出必须定义值
        int i = 10;
        System.out.println(i);

        //变量类型  变量名字 = new Kuang();
        Kuang kuang = new Kuang();
        System.out.println(kuang.age);
        System.out.println(kuang.name);

        //类变量 static
        System.out.println(salary);                 //无需实例化对象
        System.out.println(Kuang.salary);           //可以用类名直接调用

        //静态变量
        System.out.println(PI);
        System.out.println(Kuang.PI);

        *****************************************************************

        //Unicode编码
        char c3 = '\u0061';
        System.out.println(c3);         //a
        System.out.println((int)c3);    //97    //注意强制转换格式!   Python:int(c3)

        *****************************************************************

        //浮点数拓展     ->  最好避免浮点数比较
        float f = 0.1f;
        double d = 1.0/10;
        System.out.println(f==d);       //false

        float f1 = 12345678910f;
        float f2 = f1 + 1;
        System.out.println(f1==f2);     //true

        int a = 10000_00000
        int b = 20;
        //long c = a*b;                   //(×)         ※
        long c = a*(long)b                //必须在计算之前进行强制类型转换,否则会损失精度
        */
    }
}
