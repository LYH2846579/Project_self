package com.atguigu.java_advanced_programming.reflection.review;

class Person
{
    private String name;
    public int age;

    public Person() {
    }
    //私有单参数构造器
    private Person(String name) {
        this.name = name;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public void show()
    {
        System.out.println("你好，我是一个人");
    }
    private String showNation(String nation)
    {
        System.out.println("我是"+nation+"人");
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
