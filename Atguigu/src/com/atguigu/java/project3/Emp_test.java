package com.atguigu.java.project3;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author LYHstart
 * @create 2021-08-08 7:51
 */
public class Emp_test
{

    public static void main(String[] args)
    {
        Company c = new Company();
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            c.print();
            int select = scan.nextInt();
            switch (select)
            {
                case 1:
                    c.show();
                    break;
                case 2:
                    c.addEmp();
                    break;
                case 3:
                    c.del_Emp();
                    break;
                case 4:
                    System.out.println("欢迎您再次使用!");
                    scan.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

class Company
{
    /*
    可以使用vector对系统进行优化
     */
    Emp[] e = new Emp[17];              //Employ
    int tag = 12;
    int flag = 0;

    //团队
    Group_Emp[] gemp = new Group_Emp[17];

    //记录职务信息
    int architect = 0;
    int programmer = 0;
    int stylist = 0;

    public Company()
    {
        e[0] = new Emp(1,"马云 ",22, (double) 3000);          //包装类必须类型一致
        e[1] = new Emp(2,"马化腾",32, (double) 18000,"架构师","FREE",15000.0, (double) 2000,"联想T4(6000.0)");
        e[2] = new Emp(3,"李彦宏",23, (double) 7000,"程序员","FREE","戴尔(NEC17寸)");
        e[3] = new Emp(4,"刘强东",24, (double) 7300,"程序员","FREE","戴尔(三星17寸)");
        e[4] = new Emp(5,"雷军 ",28, (double) 10000,"设计师","FREE",5000.0,"激光(佳能2900)");
        e[5] = new Emp(6,"任志强",22, (double) 6800,"程序员","FREE","华硕(三星17寸)");
        e[6] = new Emp(7,"柳传志",29, (double) 10800,"程序员","FREE",5200.0,"华硕(三星17寸)");
        e[7] = new Emp(8,"杨元庆",30, (double) 19800,"架构师","FREE",15000.0,2500.0,"针式(爱普生20k)");
        e[8] = new Emp(9,"史玉柱",26, (double) 9800,"设计师","FREE",5500.0,"惠普m6(5800.0)");
        e[9] = new Emp(10,"丁磊 ",21, (double) 6600,"程序员","FREE","戴尔(NEC17寸)");
        e[10] = new Emp(11,"张朝阳",25, (double) 7100,"程序员","FREE","华硕(三星17寸)");
        e[11] = new Emp(12,"杨致远",27, (double) 9600,"设计师","FREE",4800.0,"惠普m6(5800.0)");
    }

    public void print()
    {
        System.out.println("----------------开发团队调度软件----------------");
        System.out.println("\t\tID\t\t姓名\t\t\t年龄\t\t工资\t\t\t职位\t\t\t状态\t\t\t奖金\t\t\t股票\t\t\t领用设备");
        for(int i=0;i<tag;i++)
        {
            String info = e[i].toString();
            System.out.println(info);
        }
        System.out.println("----------------------------------------------");
        System.out.println("请选择:");
        System.out.println("*    [1] 团队列表   \t   [2]添加团队成员       *");
        System.out.println("*    [3] 删除团队成员\t   [4]退出              *");
    }
    public void addEmp()
    {
        System.out.println("请输入要添加的职工信息:");
        Scanner scan = new Scanner(System.in);
        int tempid = scan.nextInt();
        Emp temp = this.e[tempid-1];

        if(temp.getState().equals("BUSY"))
            System.out.println("该职工已经分配任务");
        else
        {
            try
            {
                if(temp.getPos().equals("架构师") && architect > 0)
                    throw new arch_Exception("架构师人数已达到最大值!");
                else if(temp.getPos().equals("设计师") && stylist > 1)
                    throw new styl_Exception("设计师人数已达到最大值!");
                else if(temp.getPos().equals("程序员") && programmer > 2)
                    throw new prog_Exception("程序员人数已达到最大值!");
                else
                {
                    //添加用户
                    gemp[flag] = new Group_Emp(flag+1,temp);
                    flag++;
                    this.e[tempid-1].setState("BUSY");

                    if(temp.getPos().equals("架构师"))
                        architect++;
                    else if(temp.getPos().equals("设计师"))
                    {
                        stylist++;
                    }
                    else if(temp.getPos().equals("程序员"))
                    {
                        programmer++;
                    }
                }
            }catch (arch_Exception e)
            {
                System.out.println(e.getMessage());
            }catch (prog_Exception e)
            {
                System.out.println(e.getMessage());
            } catch (styl_Exception e)
            {
                System.out.println(e.getMessage());
            }catch (Exception e)
            {
                System.out.println("程序出现异常!");
            }
        }
    }
    public void show()
    {
        System.out.println("----------------开发团队调度软件----------------");
        System.out.println("\tTID/ID\t\t\t姓名\t\t\t年龄\t\t工资\t\t\t职位\t\t\t状态\t\t\t奖金\t\t\t股票\t\t\t领用设备");
        for(int i=0;i<flag;i++)
        {
            System.out.println(gemp[i].toString());
        }
        System.out.println("----------------------------------------------");
    }
    /*
    此处删除方法并未考虑到一个团队所拥有的各项成员职责的问题，即缺失成员问题(如架构师缺失)
     */
    public void del_Emp()
    {
        this.show();
        System.out.println("请选择要删除的职工:");
        Scanner scan = new Scanner(System.in);
        int index = scan.nextInt();

        int i = 0;
        boolean change = false;
        for(i=0;i<flag;i++)
        {
            if(this.gemp[i].getId() == index)
            {
                //获取该用户信息
                Emp temp = this.gemp[i].getEmp();
                //数组进行排序
                for(int j=i;j<flag;j++)
                {
                    gemp[j] = gemp[j+1];
                }
                change = true;
                //下标值递减
                flag--;
                //修改状态信息
                for(int k=0;k<tag;k++)
                {
                    if(this.e[k].getId() == temp.getId())
                    {
                        this.e[k].setState("FREE");
                        break;
                    }
                }
                //修改组内人员信息
                if(temp.getPos().equals("架构师"))
                    architect--;
                else if(temp.getPos().equals("设计师"))
                {
                    stylist--;
                }
                else if(temp.getPos().equals("程序员"))
                {
                    programmer--;
                }
                break;
            }
        }
        if(!change)
        {
            System.out.println("未找到该职工!");
        }
    }
}

class Group_Emp
{
    private int id;
    private Emp emp;

    public Group_Emp(int id, Emp emp) {
        this.id = id;
        this.emp = emp;
    }

    @Override
    public String toString() {
        if(this.getId() < 10)
            return ("\t"+this.id+"/"+emp.getId()+"\t\t\t"+emp.show());
        else
            return (""+this.id+"/"+emp.getId()+emp.show());                 //死活调整不齐!
    }

    //getter & setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Emp getEmp() {
        return emp;
    }
    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}

//架构师人数异常
class arch_Exception extends Exception
{
    static final long serialVersionUID = -3387516993124171717L;

    public arch_Exception() {
    }

    public arch_Exception(String message) {
        super(message);
    }
}
//设计师人数异常
class styl_Exception extends Exception
{
    static final long serialVersionUID = -3387516993124171718L;

    public styl_Exception() {
    }

    public styl_Exception(String message) {
        super(message);
    }
}
//程序员人数异常
class prog_Exception extends Exception
{
    static final long serialVersionUID = -3387516993124171719L;

    public prog_Exception() {
    }

    public prog_Exception(String message) {
        super(message);
    }
}