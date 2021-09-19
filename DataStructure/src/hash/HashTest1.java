package hash;

import org.junit.Test;

import java.util.Objects;

/**
 * @author LYHstart
 * @create 2021-09-19 10:02
 *
 * 尝试实现Hashtable
 */
public class HashTest1
{
    @Test
    public void test1()
    {
        HashTab tab = new HashTab(7);
        tab.addEmp(1,"张三");
        tab.addEmp(8,"李四");
        tab.addEmp(5,"王五");
        tab.addEmp(6,"赵六");

        boolean b = tab.delEmp(5);
        if(b)
            System.out.println("该员工已被删除");

        Emp emp = tab.findEmp(8);
        System.out.println(emp);
    }
}

//创建员工类
class Emp
{
    private int id;
    private String name;
    private long phoneNumber;
    private Emp next;

    public Emp() {
    }
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Emp(int id, String name, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Emp getNext() {
        return next;
    }
    public void setNext(Emp next) {
        this.next = next;
    }

    //重写equals方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return id == emp.id &&
                phoneNumber == emp.phoneNumber &&
                Objects.equals(name, emp.name) &&
                Objects.equals(next, emp.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, next);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

//创建员工链表
class EmpLinkedList
{
    Emp head;
    int length;

    public EmpLinkedList() {
        head = null;
    }
    public EmpLinkedList(Emp head) {
        this.head = head;
        this.length = 0;
    }
}

//创建Hash表
class HashTab
{
    EmpLinkedList[] list;
    int size;

    public HashTab() {
    }
    public HashTab(int size) {
        this.size = size;
        this.list = new EmpLinkedList[size];
        //注意!必须进行初始化!
        for (int i = 0; i < size; i++)
        {
            list[i] = new EmpLinkedList();
        }
    }

    //生成Hash值
    public int hashValue(int key)
    {
        return key%size;
    }

    //添加员工
    public boolean addEmp(int id,String name)
    {
        Emp emp = new Emp(id,name);
        int key = hashValue(id);
        EmpLinkedList list1 = this.list[key];
        Emp temp = list1.head;
        while(temp != null)
        {
            if(temp.equals(emp))
            {
                System.out.println("该员工已存在!");
                return false;
            }
            else if(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            else    //temp.getNext() == null
            {
                //插入
                temp.setNext(emp);
                list1.length++;
                return true;
            }
        }
        //若temp == null -> 直接插入
        list1.head = emp;
        list1.length++;
        return true;
    }

    //删除员工
    public boolean delEmp(int id)
    {
        int key = hashValue(id);
        EmpLinkedList list1 = this.list[key];
        Emp temp = list1.head;
        while(temp != null)
        {
            if(temp.getId() == id)
            {
                //若查询到该员工
                //遍历寻找前序结点
                Emp preNode = list1.head;
                //倘若第一个结点就是该结点
                if(list1.head.getId() == id)
                {
                    list1.head.setNext(null);
                    temp = null;    //将结点摧毁
                    list1.length--;
                    return true;
                }
                else
                {
                    while(true)
                    {
                        if(preNode.getNext().equals(temp))
                            break;
                        else
                            preNode = preNode.getNext();
                    }
                }
                //查询到前序结点之后
                preNode.setNext(temp.getNext());
                temp.setNext(null);
                temp = null;
                list1.length--;
                return true;
            }
            else if(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            else    //temp.getNext() == null
            {
                System.out.println("该员工不存在!");
                return false;
            }
        }
        //temp == null
        System.out.println("该员工不存在!");
        return false;
    }

    //查找员工
    public Emp findEmp(int id)
    {
        int key = hashValue(id);
        EmpLinkedList list1 = this.list[key];
        Emp temp = list1.head;
        while(temp != null)
        {
            if(temp.getId() == id)
            {
                return temp;
            }
            else if(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            else    //temp.getNext() == null
            {
                System.out.println("该员工不存在!");
                return null;
            }
        }
        //temp == null
        System.out.println("该员工不存在!");
        return null;
    }
}
