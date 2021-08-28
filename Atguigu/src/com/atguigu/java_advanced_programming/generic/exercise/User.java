package com.atguigu.java_advanced_programming.generic.exercise;

import java.util.Objects;

/**
 * @author LYHstart
 * @create 2021-08-28 10:46
 */
public class User
{
    private int id;
    private int age;
    private String name;

    public User() {
    }
    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(name, user.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }
}
