package stack;

/**
 * @author LYHstart
 * @create 2021-09-10 19:51
 */
public class LinkedListNode<T>
{
    private T data;
    private LinkedListNode<T> next;

    public LinkedListNode() {
    }
    public LinkedListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public LinkedListNode<T> getNext() {
        return next;
    }
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
