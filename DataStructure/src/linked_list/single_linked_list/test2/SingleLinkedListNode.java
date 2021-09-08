package linked_list.single_linked_list.test2;

/**
 * @author LYHstart
 * @create 2021-09-08 18:56
 *
 * 普通单链表的结点,存储数据支持泛型
 */
public class SingleLinkedListNode<T extends Comparable>
{
    private T data;
    private SingleLinkedListNode next;

    public SingleLinkedListNode() {
        this.next = null;
    }

    public SingleLinkedListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public SingleLinkedListNode getNext() {
        return next;
    }
    public void setNext(SingleLinkedListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
