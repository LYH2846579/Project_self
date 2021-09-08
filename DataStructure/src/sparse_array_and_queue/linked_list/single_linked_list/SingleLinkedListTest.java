package sparse_array_and_queue.linked_list.single_linked_list;

import org.junit.Test;

/**
 * @author LYHstart
 * @create 2021-09-08 16:46
 *
 * 对于单链表实现水浒英雄排序的测试
 */
public class SingleLinkedListTest
{
    @Test
    public void test1()
    {
        SingleLinkedListNode s1 = new SingleLinkedListNode(1,"宋江","呼保义");
        SingleLinkedListNode s2 = new SingleLinkedListNode(2,"卢俊义","玉麒麟");
        SingleLinkedListNode s3 = new SingleLinkedListNode(3,"吴用","智多星");
        SingleLinkedListNode s4 = new SingleLinkedListNode(4,"公孙胜","入云龙");

        SingleLinkedList list = new SingleLinkedList();
        list.pushByRank(s2);
        list.pushByRank(s1);
        list.pushByRank(s4);
        list.pushByRank(s3);

        list.print();
    }
}
