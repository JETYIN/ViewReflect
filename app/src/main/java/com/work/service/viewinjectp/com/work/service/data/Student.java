package com.work.service.viewinjectp.com.work.service.data;

/**
 * Created by Administrator on 2017/5/31.
 */

public class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
