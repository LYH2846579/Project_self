package tree.huffman_tree.review;

import org.junit.Test;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-29 19:11
 *
 * 对HuffmanCode进行复习
 */
public class HuffmanCodeTest1
{
    @Test
    public void test1()
    {
        HuffmanCodeFun huffmanCodeFun = new HuffmanCodeFun("i like like like java do you like a java");
        huffmanCodeFun.preScanner();
        huffmanCodeFun.createhuffmanTree();
        huffmanCodeFun.createHuffmanCode(huffmanCodeFun.getRoot(),"",HuffmanCodeFun.getStringBuilder());
        huffmanCodeFun.dealContent();
        huffmanCodeFun.analysis();

    }
}

//创建HuffmanCode生成方法,使用该方法实现HuffmanTree的构建及文件编码解码的形成
class HuffmanCodeFun
{
    //私有属性
    //1.需要压缩的数据
    private String content;
    //2.遍历压缩对象所需的存储map 存储每一个字符出现的频度
    private static HashMap<Byte,Integer> nodes = new HashMap<>();
    //3.存储每一个对象生成的HuffmanTree结点的链表
    private static LinkedList<Node> list = new LinkedList<>();
    //4.存储生成的HuffmanTree
    private Node root;
    //5.存储生成的HuffmanCode
    private static HashMap<Byte,String> huffmanCodes = new HashMap<>();
    //6.解压所需要的map
    private static HashMap<String,Byte> huffmanCodesAnalysis = new HashMap<>();

    //※存储HuffmanCode的可扩容数组
    private static StringBuilder stringBuilder = new StringBuilder();
    //存储解压后的文本
    private String text = "";

    public Node getRoot() {
        return root;
    }
    public static StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public HuffmanCodeFun() {
    }
    public HuffmanCodeFun(String content) {
        this.content = content;
    }

    //7.第一步,扫描需要压缩的文件
    public void preScanner()
    {
        //扫描字符出现的频度
        byte[] bytes = content.getBytes();
        Integer i = null;
        for(byte b:bytes)
        {
            i = nodes.get(b);
            if(i == null)
                nodes.put(b,1);
            else
                nodes.put(b,i+1);
        }
        //遍历nodes,将所有字符构建node对象
        nodes.forEach((key,value) -> {
            //将构建的新对象加入list
            list.offerLast(new Node(key,value));
        });
    }

    //8.构建HuffmanTree
    public void createhuffmanTree()
    {
        Node node = null;
        Node node1 = null;
        Node node2 = null;

        while(list.size() != 1)
        {
            //排序
            Collections.sort(list);
            //取出最小的两个元素
            node1 = list.pollFirst();
            node2 = list.pollFirst();
            node = new Node(node1.getWeight()+node2.getWeight());
            node.setLchild(node1);
            node.setRchild(node2);
            //将父结点加入list
            list.offerLast(node);
        }
        //当树中仅有一个结点时,HuffmanTree构建完成
        this.root = list.peekFirst();
    }

    //9.生成HuffmanCode
    public void createHuffmanCode(Node node,String code,StringBuilder stringBuilder)
    {
        //创建属于自己的StringBuilder
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入stringBuilder之中
        stringBuilder1.append(code);
        //判断当前结点是否为空
        if(node != null)
        {
            //判断是否为叶子节点
            if(node.getData() == null)      //※注意是否为空!
            {
                //当前结点若非叶子节点时
                createHuffmanCode(node.getLchild(),"0",stringBuilder1);
                createHuffmanCode(node.getRchild(),"1",stringBuilder1);
            }
            else
            {
                //将该结点的stringBuilder值加入huffmanCodes之中
                huffmanCodes.put(node.getData(),stringBuilder1.toString());
            }
        }
    }

    //10.将文本压缩
    public void dealContent()
    {
        String str = "";
        //将content中的字符逐个进行压缩
        byte[] bytes = content.getBytes();
        for(byte b:bytes)
        {
            str += huffmanCodes.get(b);
        }

        FileOutputStream fos = null;
        //将压缩之后的文本写入文件
        try
        {
            fos = new FileOutputStream("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\review\\AfterDeal.txt");
            fos.write(str.getBytes());

            //将huffmanCodes写入文件
            fos = new FileOutputStream("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\review\\HuffmanCodes.txt");
            FileOutputStream finalFos = fos;
            huffmanCodes.forEach((key, value) -> {
                String temp = key+" "+value+"#";
                try
                {
                    finalFos.write(temp.getBytes());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(fos != null)
                    fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //11.将文本解压缩
    public void analysis()
    {
        String str = null;
        FileInputStream fis = null;
        try
        {
            //读取huffmanCodes
            fis = new FileInputStream("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\review\\HuffmanCodes.txt");
            str = "";
            byte[] bbuf = new byte[5];
            int read;
            while((read = fis.read(bbuf)) != -1)
            {
                str += new String(bbuf);
            }
            //将str分组
            String[] strings = str.split("\\#");
            for(String s:strings)
            {
                String[] split = s.split(" ");
                //创建map元素
                huffmanCodesAnalysis.put(split[1],Byte.parseByte(split[0]));    //※注意顺序!
            }

            //读取被压缩的文件
            fis = new FileInputStream("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\review\\AfterDeal.txt");
            //将str置空        ※
            str = "";
            while((read = fis.read(bbuf)) != -1)
            {
                str += new String(bbuf);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(fis != null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //逐位分析
        String subStr = "";
        String regStr = "\\d";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
        {
            subStr += matcher.group(0);
            //倘若可以找到对应的字符
            if(huffmanCodesAnalysis.containsKey(subStr))
            {
                byte aByte = huffmanCodesAnalysis.get(subStr);
                text += (char)aByte;
                //清空subStr
                subStr = "";
            }
        }

        //解压结果输出
        System.out.println(text);
    }

}



//创建HuffmanTree结点 -> 需要使用HuffmanTree生成HuffmanCode
class Node implements Comparable    //实现weight的Comparable接口
{
    //字符数据
    private Byte data;          //这里采用Byte包装类,便于后期判断是否为叶子节点
    //权重
    private int weight;
    //左右子节点
    private Node lchild;
    private Node rchild;

    public Node() {
    }
    public Node(int weight) {
        this.weight = weight;
    }
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }
    public void setData(Byte data) {
        this.data = data;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public Node getLchild() {
        return lchild;
    }
    public void setLchild(Node lchild) {
        this.lchild = lchild;
    }
    public Node getRchild() {
        return rchild;
    }
    public void setRchild(Node rchild) {
        this.rchild = rchild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                ", lchind=" + lchild +
                ", rchild=" + rchild +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Node)
            return this.weight - ((Node) o).weight;
        else
            throw new RuntimeException("对象类型异常");
    }
}
