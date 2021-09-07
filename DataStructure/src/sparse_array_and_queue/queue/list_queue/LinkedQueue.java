package sparse_array_and_queue.queue.list_queue;

/**
 * @author LYHstart
 * @create 2021-09-07 19:55
 *
 * ① 时刻保证first指向的为链表的头结点，last指向链表的最后一个结点
 * ② 入队列时，仅需将尾节点指向新插入的结点即可，并将新结点的next设置为null
 * ③ 出队列时，需要特别注意!
 *    [1] 使用temp指向需要被释放的结点，最后将temp指向null，以便于将结点彻底回收
 *    [2] 当删除的为最后一个结点的时候，尾节点指向头结点(此时需要修改尾节点!)
 *    [3] 为了方便起见，在队列中加入length用以计数
 */
public class LinkedQueue<T>
{
    private ListNode<T> first;
    private ListNode<T> last;
    private int length;

    public LinkedQueue() {
        //创建头结点
        this.first = this.last = new ListNode<>();
        this.first.setNext(null);
    }

    //无需判定队列满
    //判定队列空
    public boolean isEmpty()
    {
        if(this.first == this.last)
            return true;
        else
            return false;
    }

    //enQueue & deQueue
    public void enQueue(T t)
    {
        ListNode node = new ListNode(t);
        //在尾部插入
        this.last.setNext(node);
        node.setNext(null);
        this.length++;
        //调整last
        this.last = node;
        //输出提示
        System.out.println(t+"入队成功!");
    }
    public T deQueue()
    {
        T temp = null;
        if(isEmpty())
            System.out.println("队列为空，无法出队!");
        else
        {
            ListNode node = this.first.getNext();
            temp = (T) node.getData();
            //判断是否为尾节点
            if(node == this.last)
                this.last = this.first;
            //将头结点指向后移
            this.first.setNext(node.getNext());
            //释放结点
            node.setNext(null);
            node = null;
            //调整length
            this.length--;
            //输出提示
            System.out.println(temp+"出队成功!");
        }
        //返回data
        return temp;
    }

    //遍历
    public void print()
    {
        ListNode p = this.first.getNext();
        while(p.getNext() != null)
        {
            System.out.print(p.getData()+"\t");
            p = p.getNext();
        }
        //将尾节点的数据输出
        System.out.println(p.getData());
    }

}
