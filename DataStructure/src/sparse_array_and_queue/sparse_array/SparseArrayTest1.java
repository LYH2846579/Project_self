package sparse_array_and_queue.sparse_array;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author LYHstart
 * @create 2021-09-06 20:12
 *
 * 实现五子棋存档功能
 *
 * 总结:
 * ① 数组下标从0开始!一定注意！！！
 * ② 稀疏数组的行数比有效值的个数+1  ->  需要多出一行存储原始数组的属性
 * ③ 将稀疏数组输出到文件中时，将数据直接输出即可(换行将会严重影响数据的准确性!)
 * ④ 使用FileReader和FileWriter输出和读入数组时
 *    [1] 写出时int值转换为ASCII表中对应位置的符号!
 *    [2] 读取时需要将符号转换为int类型
 * ⑤ 读取文件数据的时候，使用cbuf以每三个一组读取即可 -> (char[] cbuf = new char[3];)
 *
 * 待解决的问题：
 * ① ASCII值的范围为0~127，若棋盘超出此范围，如何处理?
 *    附：经试验表明，当棋盘大小为2555*2555的时候，仍可以正常保存!
 */
public class SparseArrayTest1
{
    @Test   //五子棋存档
    public void test1()
    {
        //创建原始只有两颗棋子的数组 -> 黑色为1，蓝色为2
        int[][] arr = new int[2555][2555];  //行列！
        arr[1][2] = 1;
        arr[2][3] = 2;

        //打印数组
        //System.out.println(arr.length);   //行
        //System.out.println(arr[0].length);//列
        //横着输出      //先行后列
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }

        //将数组压缩为稀疏数组
        int values = 0;
        //遍历寻找有效值
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if(arr[i][j] != 0)
                    values++;
            }
        }
        //1.如何存储稀疏数组? -> 使用二维数组存储(固定三列)
        int[][] sparseArray = new int[values+1][3];     //多出一行存储原始数组数据
        //存储原始数组大小
        sparseArray[0][0] = arr.length;
        sparseArray[0][1] = arr[0].length;
        sparseArray[0][2] = values;
        //控制sparseArray存储
        int m = 1;
        int n = 0;
        //将有效值存储
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if(arr[i][j] != 0)
                {
                    sparseArray[m][n++] = i;
                    sparseArray[m][n++] = j;
                    sparseArray[m++][n] = arr[i][j];
                    n = 0;
                }
            }
        }
        //输出sparseArray
        for (int i = 0; i < sparseArray.length; i++)
        {
            for (int j = 0; j < sparseArray[0].length; j++)
            {
                System.out.print(sparseArray[i][j]+"\t");
            }
            System.out.println();
        }

        //将稀疏数组输出到文件之中  //使用.txt文件存储
        File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\sparse_array_and_queue\\sparse_array\\SparseArrayInfo.txt");


        //流资源加载
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(file);
            //处理数据 -> 将稀疏数组中的内容写到文件之中
            for (int i = 0; i < sparseArray.length; i++)
            {
                for (int j = 0; j < sparseArray[0].length; j++)
                {
                    fw.write(sparseArray[i][j]);        //int -> 显示在txt文件中的为ASCII表中的位置!
                }
                //fw.write("\n"); //换行          //换行会严重影响读取的数据准确性!
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(fw != null)
                    fw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test   //尝试还原棋盘
    public void test2() throws IOException
    {
        //读取文件内容
        FileReader fr = null;
        fr = new FileReader("F:\\Java\\Project_self\\DataStructure\\src\\sparse_array_and_queue\\sparse_array\\SparseArrayInfo.txt");

        //读取数据
        char[] cbuf = new char[3];
        int read;
        read = fr.read(cbuf);
        //for(char c:cbuf)
        //    System.out.print((int)c+"\t");
        int row = (int)cbuf[0];
        int col = (int)cbuf[1];
        int count = (int)cbuf[2];
        System.out.println(row+"\t"+col+"\t"+count);
        //创建二维数组
        int[][] arr = new int[row][col];
        for (int i = 0; i < count; i++)
        {
            fr.read(cbuf);
            System.out.println("**"+(int)cbuf[0]+"\t"+(int)cbuf[1]+"\t"+(int)cbuf[2]+"**");
            arr[(int)cbuf[0]][(int)cbuf[1]] = (int)cbuf[2];
        }
        //输出二维数组
//        for (int i = 0; i < arr.length; i++)
//        {
//            for (int j = 0; j < arr[0].length; j++)
//            {
//                System.out.print(arr[i][j]+"\t");
//            }
//            System.out.println();
//        }
    }

    @Test   //测试JAVA数组行列
    public void test()
    {
        int[][] arr = new int[2][3];    //两行三列数组

        System.out.println(arr.length);
        System.out.println(arr[0].length);

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //这里练习一下I/O流
    @Test   //使用缓冲流加速+转换流
    public void test3() throws IOException
    {
        //生成稀疏数组
        int[][] sparseArray = new int[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                sparseArray[i][j] = j;
            }
        }
        //输出稀疏数组
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(sparseArray[i][j]+"\t");
            }
            System.out.println();
        }

        File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\sparse_array_and_queue\\sparse_array\\IO.txt");

        FileOutputStream fos = new FileOutputStream(file);

        //缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //转换流
        OutputStreamWriter osw = new OutputStreamWriter(bos,"UTF-8");

        for (int i = 0; i < sparseArray.length; i++)
        {
            for (int j = 0; j < sparseArray[0].length; j++)
            {
                osw.write(sparseArray[i][j]);
            }
        }

        //流资源关闭
        osw.close();
    }

    @Test   //读取文件      ->      成功!
    public void test4() throws IOException
    {
        //File
        File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\sparse_array_and_queue\\sparse_array\\IO.txt");
        //字节流
        FileInputStream fis = new FileInputStream(file);
        //缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        //转换流
        InputStreamReader isr = new InputStreamReader(bis);

        //读取
        char[] cbuf = new char[3];
        isr.read(cbuf);
        int row = cbuf[0];
        int col = cbuf[1];
        int count = cbuf[2];
        //创建稀疏数组
        int[][] arr = new int[3][3];
        //读取数据
        arr[0][0] = row;
        arr[0][1] = col;
        arr[0][2] = count;
        //读取数据 -> 填充数组
        //位置定位
        int m = 1;
        int n = 0;
        for (int i = 0; i < count; i++)
        {
            isr.read(cbuf);
            arr[m][n++] = cbuf[0];
            arr[m][n++] = cbuf[1];
            arr[m++][n] = cbuf[2];
            n = 0;
        }
        //数组输出
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
