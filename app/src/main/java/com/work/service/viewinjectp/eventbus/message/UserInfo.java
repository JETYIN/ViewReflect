package com.work.service.viewinjectp.eventbus.message;

/**
 * Created by Administrator on 2017/5/16.
 */

public class UserInfo {

    private String userName;


    public UserInfo(String userName) {
        this.userName = userName;

    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
}
