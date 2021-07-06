package User_information_management_system;

/**
 * @author LYHstart
 * @create 2021-07-06 15:22
 */
public class CustomerList
{
    Customer[] customers;
    int index;

    public CustomerList(Customer[] customers, int index)
    {
        this.customers = customers;
        this.index = index;
    }

    void printCustomers()
    {
        System.out.println("序号\t\t"+"姓名\t\t"+"性别\t\t"+"年龄\t\t"+"电话\t\t"+"邮箱\t\t");
        for(int i=0;i<index;i++)
        {
            System.out.println(customers[i].id+"\t\t"+customers[i].name+"\t\t"+customers[i].sex
                    +"\t\t"+customers[i].age+"\t\t"+customers[i].phone_number+"\t\t"+customers[i].email+"\t\t");
        }
    }
}
