package linked_list.single_linked_list.test1;

/**
 * @author LYHstart
 * @create 2021-09-08 16:17
 */
public class SingleLinkedList
{
    //默认初始化头结点
    private SingleLinkedListNode head;
    //两个指针
    private SingleLinkedListNode first;
    private SingleLinkedListNode last;
    private int length;

    public SingleLinkedList() {
        head = new SingleLinkedListNode();
        //设置指针
        head.setNext(null);
        //设置头尾结点!       ->  小心空指针异常!
        this.first = this.last = head;
    }

    //添加结点
    //尾插法
    public void pushBack(SingleLinkedListNode s)
    {
        //1.修改结点next指向
        this.last.setNext(s);
        //2.修改尾指针
        this.last = s;
        //3.修改长度
        this.length++;
    }
    //按照排行定位
    public void pushByRank(SingleLinkedListNode s)
    {
        //判断是否为空
        //辅助指针
        SingleLinkedListNode temp = this.first.getNext();
        if(temp == null)
        {
            //若链表为空，直接尾插
            pushBack(s);
        }
        else
        {
            //修改temp指向
            temp = this.first;
            //从头开始遍历
            while(temp.getNext() != null)
            {
                //判断排名  后一个排名与要添加的排名
                if(temp.getNext().getId() < s.getId())
                {
                    //若排名比所指的结点大 -> 指针后移
                    temp = temp.getNext();
                }
                else
                {
                    //若排名比所指的结点小 -> 插到temp当前所指的结点之后!
                    //1.修改s指针
                    s.setNext(temp.getNext());
                    //2.修改temp指针
                    temp.setNext(s);
                    //3.修改长度
                    this.length++;
                    //退出循环
                    break;
                }
            }
            //判断是否到尾节点
            if(temp.getNext() == null && (temp == this.last))
            {
                //若到了尾节点 -> 执行尾插
                pushBack(s);
            }
        }
    }

    //遍历
    public void print()
    {
        SingleLinkedListNode s = this.first.getNext();
        //System.out.println(s.toString());
        while(s != null)
        {
            System.out.println(s.toString());
            s = s.getNext();
        }
    }
}
