package sparse_array_and_queue.linked_list.single_linked_list;

/**
 * @author LYHstart
 * @create 2021-09-08 16:18
 *
 * 单链表的一个结点
 * 由于实现水浒英雄的排行管理，因此需考虑对应属性!
 */
public class SingleLinkedListNode
{
    private int id;
    private String name;
    private String nickName;
    private SingleLinkedListNode next;

    public SingleLinkedListNode() {
    }
    public SingleLinkedListNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.next = null;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public SingleLinkedListNode getNext() {
        return next;
    }
    public void setNext(SingleLinkedListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SingleLinkedListNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next.name;
    }
}
