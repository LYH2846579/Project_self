package User_information_management_system;

import java.util.Scanner;

/**
 * @author LYHstart
 * @create 2021-07-06 15:38
 */
public class System_control                      //控制类起名一定要注意，要是System可能会出错
{
    public static void main(String[] args)
    {
        Normal n1 = new Normal();
        int select = 1;
        while (true)
        {
            n1.show_menu();
            System.out.println("请输入您的选择:");
            Scanner scan = new Scanner(System.in);
            select = scan.nextInt();
            if(select == 1)
            {

            }
            else if(select == 2)
            {

            }
            else if(select == 3)
            {

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


}
