package User_information_management_system;

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-07-06 15:38
 */
public class System_control                      //控制类起名一定要注意，要是System可能会出错
{
    //存储                                       //必须声明为Static类型!
    public static Customer[] customers = new Customer[17];
    public static int index = 0;

    public static void main(String[] args)       //思考：※如何在主类中调用函数?  ①静态函数
    {
        Normal n1 = new Normal();



        //选择指示
        int select = 1;

        //构建Customer所需要的参数
        int id;
        String name;
        String sex;
        int age;
        String phone_number;
        String email;

        while (true)
        {
            n1.show_menu();
            System.out.println("请输入您的选择:");
            Scanner scan = new Scanner(System.in);
            select = scan.nextInt();
            if(select == 1)
            {
                //检查是否已满
                if(index >= customers.length)
                {
                    System.out.println("存储空间已满，无法进行插入数据!");
                }
                else
                {
                System.out.println("请输入编号:");       //这里保证编号为主码
                id = scan.nextInt();
                boolean pass = check(id);
                if(pass == false)
                {
                    System.out.println("该用户已存在!");
                }
                else
                {
                    System.out.println("请输入姓名:");
                    name = scan.next();
                    System.out.println("请输入性别:");
                    sex = scan.next();
                    System.out.println("请输入年龄:");
                    age = scan.nextInt();
                    System.out.println("请输入电话:");
                    phone_number = scan.next();
                    System.out.println("请输入邮箱:");
                    email = scan.next();

                    Customer c = new Customer(id,name,sex,age,phone_number,email);
                    customers[index++] = c;
                }


                }
            }
            else if(select == 2)
            {
                CustomerDelete customerDelete = new CustomerDelete(customers,index);
                boolean minus = customerDelete.deleteCustomers();
                if(minus)
                {
                    index--;            //※由于采用值传递的方式，只能依靠这种方式实现
                }
            }
            else if(select == 3)
            {                                                                          //即对象调用方法
                CustomerList customerList = new CustomerList(customers,index);      //创建类的构造器之后才能进行函数调用
                customerList.printCustomers();
            }
            else if(select == 0)
            {
                System.out.println("欢迎下次使用!");
                System.exit(0);
            }
            else
            {
                System.out.println("输入错误，请重新输入!");
            }
        }
    }

    public static boolean check(int id)
    {
        for(int i=0;i<index;i++)         //小心空指针异常! -> 即在一开始的时候id不存在!
        {
            if(id == customers[i].id)
                return false;
        }
        return true;
    }

}

