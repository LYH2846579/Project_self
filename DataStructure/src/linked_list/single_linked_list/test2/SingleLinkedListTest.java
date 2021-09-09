package linked_list.single_linked_list.test2;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-09-08 19:16
 */
public class SingleLinkedListTest
{
    @Test
    public void test1()
    {
        //创建单链表
        SingleLinkedList<Integer> list = new SingleLinkedList();
        //创建结点data
        Integer integer;
        SingleLinkedListNode node;
        //选择
        int select;

        while(true)
        {
            //循环
            printMenu();
            Scanner scan = new Scanner(System.in);
            select = scan.nextInt();
            switch (select)
            {
                case 0:
                    System.out.println("欢迎下次使用单链表!");
                    list.destroy();
                    System.exit(0);
                case 1:
                    System.out.println("请输入结点数据:");
                    integer = scan.nextInt();
                    node = new SingleLinkedListNode(integer);
                    list.pushBack(node);
                    break;
                case 2:
                    list.popBack();
                    break;
                case 3:
                    list.print();
                    break;
                case 4:
                    System.out.println("请输入结点数据:");
                    integer = scan.nextInt();
                    node = new SingleLinkedListNode(integer);
                    list.pushFront(node);
                    break;
                case 5:
                    list.popFront();
                    break;
                case 6:
                    System.out.println("请输入结点数据:");
                    integer = scan.nextInt();
                    node = new SingleLinkedListNode(integer);
                    System.out.println("请输入结点插入的位置:");
                    integer = scan.nextInt();
                    //自动装箱与拆箱
                    list.insert(node,integer);
                    break;
                case 7:
                    list.reverse();
                    break;
                case 8:
                    list.sort();
                    break;
                case 9:
                    list.clear();
                    break;
                default:
                    break;
            }
        }
    }

    public void printMenu()
    {
        System.out.println("******** SingleLinkedList ********");
        System.out.println("*  [1] pushBack   [2] popBack    *");
        System.out.println("*  [3] print      [4] pushFront  *");
        System.out.println("*  [5] popFront   [6] insert     *");
        System.out.println("*  [7] reverse    [8] sort       *");
        System.out.println("*  [9] clear      [0] exit       *");
        System.out.println("请输入您的选择:");
    }
}
