package com.zerocamel.bean;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-06 13:17
 **/
public class Person {
    public int id;
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void initial()
    {
        System.out.println("init....");
    }

    public void destroy(){
        System.out.println("destroy...");
    }
}
