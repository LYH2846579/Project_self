package com.atguigu.java.project3;

/**
 * @author LYHstart
 * @create 2021-08-08 7:40
 */
public class Emp
{
    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
    private String pos;
    private String state;
    private Double bonus;
    private Double stock;
    private String facility;

    //constructor
    public Emp(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public Emp(Integer id, String name, Integer age, Double salary, String pos, String state,String facility) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.pos = pos;
        this.state = state;
        this.facility = facility;
    }
    public Emp(Integer id, String name, Integer age, Double salary, String pos, String state, Double bonus, String facility) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.pos = pos;
        this.state = state;
        this.bonus = bonus;
        this.facility = facility;
    }
    public Emp(Integer id, String name, Integer age, Double salary, String pos, String state, Double bonus, Double stock, String facility) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.pos = pos;
        this.state = state;
        this.bonus = bonus;
        this.stock = stock;
        this.facility = facility;
    }

    @Override
    public String toString() {
        return ("\t\t"+this.id+"\t\t"+this.name+"\t\t"+this.age+"\t\t"+this.salary+"\t\t"+this.pos+"\t\t"+this.state
                +"\t\t"+this.bonus+"\t\t"+this.stock+"\t\t"+this.facility);
    }

    public String show()
    {
        return ("\t"+this.name+"\t\t"+this.age+"\t\t"+this.salary+"\t\t"+this.pos+"\t\t"+this.state
                +"\t\t"+this.bonus+"\t\t"+this.stock+"\t\t"+this.facility);
    }


    //setter & getter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
}
