package bean;

/**
 * @author LYHstart
 * @create 2021-09-27 16:36
 *
 * 创建StudInfo对象,用于接收数据库查询的结果
 */
public class StudInfo
{
    private int id;
    private String name;

    public StudInfo() {
    }
    public StudInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
