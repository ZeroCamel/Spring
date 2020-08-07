package com.zerocamel.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @program: springannotation
 * @description:@Value
 * 1、基本数值
 * 2、SpEl #{}  ${}-取出配置文件的值
 * @author: zeroCamel
 * @create: 2020-08-06 13:17
 **/
public class Person {
    private int id;
    @Value("zhangsan")
    private String name;
    @Value("#{10-2}")
    private int age;
    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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
                ", nickName='" + nickName + '\'' +
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
