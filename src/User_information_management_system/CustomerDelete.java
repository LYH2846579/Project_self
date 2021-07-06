package User_information_management_system;

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-07-06 15:58
 */
public class CustomerDelete
{
    //存储
    Customer[] customers;
    int index;

    //选择控制
    int select;
    Scanner scan = new Scanner(System.in);

    //查询控制
    int id;
    String name;
    int i;

    public CustomerDelete(Customer[] customers, int index)
    {
        this.customers = customers;
        this.index = index;
    }

    public boolean deleteCustomers()
    {
        System.out.println("--------------删除用户-------------");
        System.out.println("*         1.id     2.name        *");
        System.out.println("----------------------------------");
        System.out.println("请选择以何种方式进行删除:");
        select = scan.nextInt();
        if(select == 1)
        {
            System.out.println("请输入id:");
            id = scan.nextInt();
            for(i=0;i<index;i++)
            {
                if(customers[i].id == id)
                {
                    replace(i);
                    return true;
                }
            }
            if(i == index)
            {
                System.out.println("该用户不存在!");
                return false;
            }
        }
        else if(select == 2)
        {
            System.out.println("请输入name:");
            name = scan.next();
            for(i=0;i<index;i++)
            {
                if(customers[i].name == name)
                {
                    replace(i);
                    return true;
                }
            }
            if(i == index)
            {
                System.out.println("该用户不存在!");
                return false;
            }
        }
        else
        {
            System.out.println("select ERROR!");
            //deleteCustomers();                                //自己调用自己方式是否可行?  false! ※返回值！
        }
        return false;
    }

    public void replace(int pos)
    {
        for(int j=pos;j<index;j++)
        {
            customers[pos] = customers[pos+1];
        }
        index--;
    }
}
