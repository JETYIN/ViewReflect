package com.work.service.viewinjectp.com.work.service.data;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/5/16.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClickEnvents(listenerType = View.OnClickListener.class, setListener = "setOnClickListener", clickMethod = "onClick")
//设置自定义的点击事件类型参数
public @interface onClickInject {

   int []value();

}
