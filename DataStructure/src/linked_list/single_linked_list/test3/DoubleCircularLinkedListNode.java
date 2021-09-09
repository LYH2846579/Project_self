package linked_list.single_linked_list.test3;

/**
 * @author LYHstart
 * @create 2021-09-09 15:07
 */
public class DoubleCircularLinkedListNode<T extends Comparable>
{
    //私有属性
    private DoubleCircularLinkedListNode pre;
    private DoubleCircularLinkedListNode next;
    private T data;

    public DoubleCircularLinkedListNode() {
    }

    public DoubleCircularLinkedListNode(T data) {
        this.data = data;
        this.pre = this.next = null;
    }

    public DoubleCircularLinkedListNode getPre() {
        return pre;
    }
    public void setPre(DoubleCircularLinkedListNode pre) {
        this.pre = pre;
    }
    public DoubleCircularLinkedListNode getNext() {
        return next;
    }
    public void setNext(DoubleCircularLinkedListNode next) {
        this.next = next;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
