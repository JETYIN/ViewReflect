package com.work.service.viewinjectp.eventbus.message;

/**
 * Created by Administrator on 2017/5/16.
 */

public class UserInfo {

    private String userName;
    private int age;

    public UserInfo(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getUserName() {
        return userName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
