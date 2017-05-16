package com.work.service.viewinjectp.com.work.service.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/5/16.
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ClickEnvents {

    /**
     * 此处自定义点击事件的事件类型
     **/

    Class listenerType();//类名，View.OnclickListenner

    String setListener();//注册监听String字符串

    String clickMethod();//点击方法名onClick方法
}
