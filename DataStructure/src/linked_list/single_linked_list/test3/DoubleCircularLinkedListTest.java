package linked_list.single_linked_list.test3;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-09-09 15:07
 */
public class DoubleCircularLinkedListTest
{
    @Test
    public void test1()
    {
        //创建双向链表
        DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
        //辅助变量
        int select;
        Scanner scan = new Scanner(System.in);
        //创建结点
        Integer integer = null;
        DoubleCircularLinkedListNode<Integer> node;
        //循环
        while(true)
        {
            printMenu();
            select = scan.nextInt();
            switch (select)
            {
                case 0:
                    System.out.println("欢迎下次使用双向链表!");
                    System.exit(0);
                case 1:
                    System.out.println("请输入结点的数据:");
                    integer = scan.nextInt();
                    node = new DoubleCircularLinkedListNode(integer);
                    list.pushBack(node);
                    break;
                case 2:
                    list.popBack();
                    break;
                case 3:
                    list.print();
                    break;
                case 4:
                    System.out.println("请输入结点的数据:");
                    integer = scan.nextInt();
                    node = new DoubleCircularLinkedListNode(integer);
                    list.pushFront(node);
                    break;
                case 5:
                    list.popFront();
                    break;
                case 6:
                    System.out.println("请输入结点的数据:");
                    integer = scan.nextInt();
                    list.deleteElm(integer);
                    break;
                case 7:
                    System.out.println("请输入结点的数据:");
                    integer = scan.nextInt();
                    node = new DoubleCircularLinkedListNode(integer);
                    System.out.println("请输入结点的要插入的位置:");
                    integer = scan.nextInt();
                    list.insert(node,integer);
                    break;
                case 8:
                    list.reverse();
                    break;
                case 9:
                    list.sort();
                    break;
                case 10:
                    list.clear();
                    break;
                default:
                    System.out.println("选择错误，请重新选择!");
                    break;
            }
        }
    }

    public void printMenu()
    {
        System.out.println("******** DoubleCircularLinkedList ********");
        System.out.println("*    [1] pushBack      [2] popBack       *");
        System.out.println("*    [3] print         [4] pushFront     *");
        System.out.println("*    [5] popFront      [6] deleteElm     *");
        System.out.println("*    [7] insert        [8] reverse       *");
        System.out.println("*    [9] sort          [10] clear        *");
        System.out.println("*    [0] exit                            *");
        System.out.println("请输入您的选择:");
    }
}
