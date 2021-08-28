package com.atguigu.java_advanced_programming.iostream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

/**
 * @author LYHstart
 * @create 2021-08-28 15:35
 *
 * File类的使用
 *
 * 1、File类的一个对象，代表一个或一个文件目录·
 * 2、File类声明在Java.io包下
 *
 */
public class FileTest
{
    /*
    1、如何创建File类的实例
        File(String filePath)
        File(String parentPath,String childPath)
        File(File parentPath,String childPath)

    2、
    相对路径：相对于某个路径下，指明的路径
    绝对路径：包含盘符在内的文件或文件目录的路径

    3、路径分隔符
        windows:\\
        unix:/

    4. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
       并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
    5．后续FiLe类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
     */

    @Test
    public void test1()
    {
        //构造器1
        File file1 = new File("hello.txt");  //相较于当前module
        File file2 = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\iostream\\hello.txt");

        //构造器2
        File file3 = new File("F:\\Java\\Project_self","Atguigu");

        //构造器3
        File file4 = new File(file3,"src");

        System.out.println(file1);
        System.out.println(file2);
        System.out.println(file3);
        System.out.println(file4);
    }


    /*
    File类的常用方法：
    File类获取功能：
        public String getAbsolutePath(): 获取绝对路径
        public String getPath():获取路径
        public String getName():获取名称
        public String getParent():获取上层文件目录路径。若无，返回null
        public long length():获取文件长度(即:字节数)。不能获取目录的长度
        public long lastModified():获取最后一次修改的时间，毫秒值

        public String[] list():获取指定目录下的所有文件或者文件目录的名称数组
        public File() listFiles():获取指定目录下的所有文件或者文件目录的File数组

    File类的重命名功能：
        public boolean renameTo(File dest):把文件重命名为指定的文件路径

    File类的判断功能
    public boolean isDirectory():判断是否是文件目录
    public boolean isFile():判断是否是文件
    public boolean exists():判断是否存在
    public boolean canRead():判断是否可读
    public boolean canWrite():判断是否可写
    public boolean isHidden():判断是否隐藏

    File类的创建功能：
    public boolean createNewFile():创建文件，若文件以及存在，则不创建，返回false
    public boolean mkdir():创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
    public boolean mkdirs():创建文件目录。如果上层文件目录不存在，一并创建
    注意事项:如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下

    File类的删除功能：
    public boolean delete():删除文件或者文件夹删除注意事项:
    Java中的删除不走回收站。
    要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录

     */

    @Test       //File类获取功能
    public void test2()
    {
        //File file = new File("Hello.txt");
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\iostream\\Hello.txt");

        //getAbsolutePath()
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile);

        //getPath()
        String path = file.getPath();
        System.out.println(path);

        //getName()
        String name = file.getName();
        System.out.println(name);

        //getParent()
        String parent = file.getParent();
        System.out.println(parent);

        //length()
        long length = file.length();
        System.out.println(length);     //获取目录长度为0(不可获取)

        //lastModified()
        System.out.println(file.lastModified());

        //Date
        System.out.println(new Date(file.lastModified()));

        //Calendar  //获取到精确时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(file.lastModified()));
        System.out.println(calendar.getTime());
    }

    @Test   //操作文件目录功能
    public void test3()
    {
        File file = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming");

        //public String[] list()
        String[] list = file.list();
        for (String str:list)
            System.out.println(str);
        System.out.println("***********************");

        //public File[] listFiles()
        File[] listFiles = file.listFiles();
        for(File f:listFiles)
            System.out.println(f);
    }

    @Test   //File重命名方法
    public void test4()
    {
        /*
        public boolean renameTo(File dest):把文件重命名为指定的文件路径
            比如：file1.renameTo(file2)为例：
                要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在
         */

        File file1 = new File("F:\\Java\\Project_self\\Atguigu\\src\\com\\atguigu\\java_advanced_programming\\iostream\\Hello.txt");
        File file2 = new File("F:\\Java\\Hi.txt");

        boolean renameTo = file1.renameTo(file2);
        System.out.println(renameTo);
    }

    @Test
    public void test5()
    {
        File file = new File("F:\\Java\\Hi.txt");

        //public boolean isDirectory()
        boolean file1 = file.isDirectory();
        System.out.println(file1);

        //public boolean isFile()
        boolean file2 = file.isFile();
        System.out.println(file2);

        //public boolean exists()
        boolean exists = file.exists();
        System.out.println(exists);

        //public boolean canRead()
        boolean canRead = file.canRead();
        System.out.println(canRead);

        //public boolean canWrite()
        boolean canWrite = file.canWrite();
        System.out.println(canWrite);

        //public boolean
        boolean hidden = file.isHidden();
        System.out.println(hidden);
    }

    @Test   //文档创建
    public void test6() throws IOException
    {
        File file = new File("F:\\Java\\hi.txt");
        if(!file.exists())
        {
            file.createNewFile();
            System.out.println("创建成功");
        }
        else
        {
            file.delete();
            System.out.println("删除成功");
        }
    }

    @Test //文档目录创建
    public void test7()
    {
        /*
    public boolean createNewFile():创建文件，若文件以及存在，则不创建，返回false
    public boolean mkdir():创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
    public boolean mkdirs():创建文件目录。如果上层文件目录不存在，一并创建
         */
        //文件目录创建
        File file = new File("F:\\Java\\IO\\io1");

        boolean mkdir = file.mkdir();
        if(mkdir)
            System.out.println("io1创建成功");
        else
            System.out.println("io1创建失败");      //-> √

        File file1 = new File("F:\\Java\\IO\\io2");
        mkdir = file.mkdirs();
        if(mkdir)
            System.out.println("io2创建成功");      //-> √
        else
            System.out.println("io2创建失败");
    }

}
