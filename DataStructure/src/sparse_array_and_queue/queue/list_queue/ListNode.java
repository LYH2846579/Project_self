package sparse_array_and_queue.queue.list_queue;

/**
 * @author LYHstart
 * @create 2021-09-07 20:18
 *
 * 单链表的结点
 */
public class ListNode<T>
{
    private T data;
    private ListNode next;

    public ListNode() {
    }
    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public ListNode getNext() {
        return next;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }
}

