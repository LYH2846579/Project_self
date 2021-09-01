package com.atguigu.java_advanced_programming.iostream.stream_ctrl;

import org.junit.Test;

import java.io.*;

/**
 * @author LYHstart
 * @create 2021-09-01 9:23
 */
public class Stream_exp
{
    @Test
    public void test1()
    {
        //使用缓冲流实现将a.gif复制为b.gif
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try
        {
            //File
            File file1 = new File("F:\\Java\\Project_self\\Atguigu\\Mario.gif");
            File file2 = new File("F:\\Java\\Project_self\\Atguigu\\b.gif");

            //节点流创建
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            //缓冲流加载
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //信息处理
            byte[] buffer = new byte[16];
            int read;
            while((read = bis.read(buffer)) != -1)
            {
                bos.write(buffer,0,read);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //流资源关闭
            if(bis != null)
            {
                try
                {
                    bis.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(bos != null)
            {
                try
                {
                    bos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
