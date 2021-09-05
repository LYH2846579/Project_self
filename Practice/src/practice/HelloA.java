package practice;

/**
 * @author LYHstart
 * @create 2021-09-05 20:34
 *
 * 静态代码块 > 构造代码块 > 构造函数
 */
public class HelloA {
    public HelloA() {
        System.out.println("A的构造函数");
    }
    {
        System.out.println("A的构造代码块");
    }
    static {
        System.out.println("A的静态代码块");
    }
    public static void main(String[] args) {
        HelloA a = new HelloA();
    }
}