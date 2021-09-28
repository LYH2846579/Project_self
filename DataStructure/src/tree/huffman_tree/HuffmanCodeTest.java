package tree.huffman_tree;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LYHstart
 * @create 2021-09-28 14:28
 *
 * 使用Huffman编码对数据进行压缩
 */
public class HuffmanCodeTest
{

    @Test   //实现对数据进行编码
    public void HuffmanCodeTest1()
    {
        HuffmanCode huffmanCode = new HuffmanCode("i like like like java do you like a java");
        //前序处理
        huffmanCode.predel();
        //构建HuffmanTree
        huffmanCode.CreateHuffmanTree();
        //生成HuffmanCode
        huffmanCode.getCodes(HuffmanCode.root,"",HuffmanCode.stringBuilder);
        //将生成的HuffmanCode输出
        HashMap<Byte, String> codes = huffmanCode.getHuffmanCodes();

        /*
        codes.forEach((key,value) -> {
            System.out.print("Key:"+key+" value:"+value+"\t");
        });
        */

        //处理信息
        huffmanCode.delContent();

        //解码
        huffmanCode.analysis();
        System.out.println(HuffmanCode.text);
    }




    @Test   //对字节数组进行测试
    public void test()
    {
        String str = "Hello World!";
        byte[] bytes = str.getBytes();

        for(byte b:bytes)
            System.out.println(b);
        //会发现将原字符串转换为ASCII码的int类型进行存储
    }
}

class HuffmanCode
{
    //传入的数据
    private String content;
    //生成的HuffmanCode存储  ->  将霍夫曼编码表存储起来,以32->01,97->100等形式
    private HashMap<Byte,String> huffmanCodes;
    //HuffmanCode生成
    static StringBuilder stringBuilder = new StringBuilder();
    //生成一颗HuffmanTree   -> 根节点
    static Node root;
    //存储字符及频度信息(在数据遍历时使用)
    static HashMap<Byte,Integer> codes = new HashMap<>();
    //存储HuffmanNode的list
    static LinkedList<Node> list = new LinkedList<>();      //这里若使用list指向,不可调用LinkedList中的一些方法

    //解码使用的map
    private HashMap<String,Byte> huffmanCodesAnalysis;
    //存储解压后的数据
    static String text = "";

    public HuffmanCode(String content) {
        this.content = content;
        this.huffmanCodes = new HashMap<>();
        this.huffmanCodesAnalysis = new HashMap<>();
    }

    //1.遍历给出的所有数据,统计频度和字符
    public void predel()
    {
        byte[] bytes = content.getBytes();
        //遍历统计
        for(byte b:bytes)
        {
            //从codes中进行查询   ->  获取频度信息
            Integer integer = codes.get(b);
            if(integer == null)
                codes.put(b,1);         //倘若无频度信息
            else
                codes.put(b,integer+1); //若存在频度信息,数值+1覆盖原有信息
        }
        //遍历完成之后,将codes中所有的数据生成code结点存储到list中
        codes.forEach((key,value) -> {
            //添加结点
            list.add(new Node(key,value));
        });
    }

    //2.根据list中的数据构建HuffmanTree
    public void CreateHuffmanTree()
    {
        if(list.size() <= 0)
            return;
        while(list.size() != 1)
        {
            //排序
            Collections.sort(list);
            //取出两个最小的元素来
            Node node1 = list.pollFirst();
            Node node2 = list.pollFirst();
            //创建其父结点
            Node node = new Node(node1.getWeight()+node2.getWeight());
            node.setLchild(node1);
            node.setRchild(node2);
            //将父结点加入链表
            list.offerLast(node);
        }
        root = list.peekFirst();
    }

    //遍历HuffmanTree的所有叶子节点,得到HuffmanCode
    /**
     *
     * @param node  传入的根节点
     * @param code  路径信息(左子节点为0,右子节点为1)
     * @param stringBuilder 用于路径拼接
     */
    public void getCodes(Node node,String code,StringBuilder stringBuilder)
    {
        //接收来自父结点的编码
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code添加入stringBuilder1之中
        stringBuilder1.append(code);
        //判断当前结点是否为null
        if(node != null)
        {
            //判断当前结点是否为叶子节点
            if(node.getData() == null)
            {
                //倘若不存在数据 ->  非叶子节点
                //向左递归处理
                getCodes(node.getLchild(),"0",stringBuilder1);
                //向右递归处理
                getCodes(node.getRchild(),"1",stringBuilder1);
            }
            else
            {
                //当为叶子节点的时候 -> 将HuffmanCode存储到map中
                huffmanCodes.put(node.getData(),stringBuilder1.toString());
            }
        }
    }

    //将文本压缩 -> 压缩数据存储到文件中
    public void delContent()
    {
        FileOutputStream fos = null;
        try
        {
            String str = "";
            byte[] bytes = content.getBytes();
            for(byte b:bytes)
            {
                String s = huffmanCodes.get(b);
                str += s;
            }
            //将得到的字符集保存在文件中
            File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\test.txt");
            fos = new FileOutputStream(file);

            byte[] bbuf = str.getBytes();
            //写入文件
            fos.write(bbuf);
            //调用存储字符集方法
            HuffmanCodesSave();
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
    //将压缩字符集存储到文件
    public void HuffmanCodesSave()
    {
        FileOutputStream fos = null;
        try
        {
            File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\huffmancodes.txt");
            fos = new FileOutputStream(file);

            //将huffmanCodes中的元素存储到文件
            FileOutputStream finalFos = fos;
            huffmanCodes.forEach((key, value) -> {
                String str = "";
                //将数据存入字符串
                str += key;
                str += " ";
                str += value;
                str += "#";
                //将字符串转换为数组
                byte[] bytes = str.getBytes();
                try
                {
                    finalFos.write(bytes);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e)
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

    //接下来对编码的信息进行解码
    public void analysis()
    {
        String str = null;
        FileInputStream fis = null;
        try
        {
            //从文件将字符编码集读取出来
            File file = new File("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\huffmancodes.txt");
            fis = new FileInputStream(file);

            str = "";
            byte[] bbus = new byte[5];
            int read;
            while((read = fis.read(bbus)) != -1)
            {
                str += new String(bbus);
            }
            String[] split = str.split("\\#");
            for(String s:split)
            {
                //读取每一对信息
                String[] strings = s.split(" ");
                byte key = (byte) Integer.parseInt(strings[0]);
                String code = strings[1];
                //加入huffmanCodesAnalysis中
                huffmanCodesAnalysis.put(code,key);             //※反向建立map
            }

            //读取压缩文件
            str = "";
            File file1 = new File("F:\\Java\\Project_self\\DataStructure\\src\\tree\\huffman_tree\\test.txt");
            fis = new FileInputStream(file1);
            while((read = fis.read(bbus)) != -1)
            {
                str += new String(bbus);
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

        //接下来开始解压
        String subString = "";
        //如何将String类型的一个超长字符串按照单个字符的形式转换为字节数组
        ArrayList<Byte> bytes = new ArrayList<>();
        //正则匹配
        String regStr = "\\d";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find())
            bytes.add(Byte.parseByte(matcher.group(0)));

        for(byte b:bytes)
        {
            subString += b;
            //开始查询是否有该结点
            if(huffmanCodesAnalysis.containsKey(subString))
            {
                //倘若存在该结点
                byte temp = huffmanCodesAnalysis.get(subString);
                text += (char)temp;
                //清空subString
                subString = "";
            }
        }
    }

    //获取编码集
    public HashMap<Byte, String> getHuffmanCodes() {
        return huffmanCodes;
    }
}

//字符存储结点 -> 将字符存储到内部,并统计频度,便于构建HuffmanTree
class Node implements Comparable
{
    //存储的数据 ->  ASCII码值
    private Byte data;              //这里将data设置为Byte类型,便于判断是否存在数据
    //权重 -> 出现的次数
    private int weight;
    //左右子节点
    private Node lchild;
    private Node rchild;

    public Node(int weight) {
        this.weight = weight;
    }
    public Node(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }
    public void setData(byte data) {
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
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Node)
            return this.weight-((Node) o).weight;
        else
            throw new RuntimeException("对象类型异常!");
    }
}
