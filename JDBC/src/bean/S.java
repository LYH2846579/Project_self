package bean;

/**
 * @author LYHstart
 * @create 2021-10-04 20:25
 *
 * 存储test数据库中的S表中的对象内容
 */
public class S
{
    private int id;
    private String name;
    private int age;

    public S() {
    }
    public S(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "S{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
