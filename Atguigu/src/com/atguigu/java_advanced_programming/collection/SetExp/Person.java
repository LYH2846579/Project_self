package com.atguigu.java_advanced_programming.collection.SetExp;

import java.util.Objects;

/**
 * @author LYHstart
 * @create 2021-08-24 9:21
 */
public class Person
{
    public int id;
    public String name;

    public Person() {
    }
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
