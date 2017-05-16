package com.work.service.viewinjectp.eventbus.message;

/**
 * Created by Administrator on 2017/5/16.
 */

/**
 * 定义事件传递模型
 **/
public class LoginMessage {

    private String message;

    public LoginMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
