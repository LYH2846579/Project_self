package User_information_management_system;

/**
 * @author LYHstart
 * @create 2021-07-06 15:21
 */

public class Customer
{
    int id;
    String name;
    String sex;
    int age;
    String phone_number;
    String email;

    //构造器
    public Customer(int id, String name, String sex, int age, String phone_number, String email)
    {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone_number = phone_number;
        this.email = email;
    }


    //直接自动生成getter与setter
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
