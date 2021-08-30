package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;
import sun.text.resources.iw.FormatData_iw_IL;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-08-30 16:25
 *
 * 实现对JPG图片的加密
 *
 * 提示：
 *      int read = 0;
 *      while((read = fis.read()) != -1)
 *      {
 *          fos.write(read ^ 5);    //??
 *      }
 * 个人理解：通过对读取数组中的每一个数据进行加密运算(异或)实现加密
 */
public class jpg_encryption
{
    @Test
    public void test1()
    {
        FileInputStream fr = null;
        FileOutputStream fw = null;
        try
        {
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Tony.jpg");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\Tony_encryption.jpg");

            //结点流加密
            fr = new FileInputStream(file1);
            fw = new FileOutputStream(file2);

            //读取、加密、输出
            byte[] cbuf = new byte[7];
            int read;
            while((read = fr.read(cbuf)) != -1)
            {
                //加密操作
                for (int i = 0; i < read; i++)
                {
                    //异或运算
                    cbuf[i] ^= 7;
                }
                //写数据
                fw.write(cbuf,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(fr != null)
                    fr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
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

    @Test   //图片解密
    public void test2()     //成功!
    {
        FileInputStream fr = null;
        FileOutputStream fw = null;
        try
        {
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Tony_encryption.jpg");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\Tony_back.jpg");

            //结点流加密
            fr = new FileInputStream(file1);
            fw = new FileOutputStream(file2);

            //读取、加密、输出
            byte[] cbuf = new byte[7];
            int read;
            while((read = fr.read(cbuf)) != -1)
            {
                //加密操作
                for (int i = 0; i < read; i++)
                {
                    //异或运算
                    cbuf[i] ^= 7;
                }
                //写数据
                fw.write(cbuf,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if(fr != null)
                    fr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
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

    //思考：理论上字节流处理的过程之中实现按位与加密，可以在节点流(FileInputStream...)和缓冲流(BufferedInputStream...)实现
    @Test   //Buffered...Stream是否可以执行加密?    -> 成功!
    public void test3()
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Switzerland.mp4");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\Buffered_encryption.mp4");

            //文件流资源加载
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            //缓冲流资源加载
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //处理加密视频操作
            byte[] buffer = new byte[100];
            int read;
            while ((read = bis.read(buffer)) != -1)
            {
                //加密处理
                for (int i = 0; i < read; i++)
                {
                    buffer[i] ^= 7;
                }
                //输出
                bos.write(buffer, 0, read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if (bis != null)
                    bis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(bos != null)
                    bos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    @Test   //解密
    public void test4()
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Buffered_encryption.mp4");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\answer.mp4");

            //流
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //解密
            byte[] buffer = new byte[100];
            int read;
            while((read = bis.read(buffer)) != -1)
            {
                //解密处理
                for (int i = 0; i < read; i++)
                {
                    buffer[i] ^= 7;
                }
                //输出
                bos.write(buffer,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            try
            {
                if (bis != null)
                    bis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(bos != null)
                    bos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
