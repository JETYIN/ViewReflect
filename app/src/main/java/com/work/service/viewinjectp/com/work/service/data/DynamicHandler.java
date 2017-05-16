package com.work.service.viewinjectp.com.work.service.data;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */

public class DynamicHandler implements InvocationHandler {

    //创建方法map
    private Map<String, Method> methodMap = new HashMap<String,Method>(1);


    //创建当前对象弱引用，防止因activity引用造成的内存泄漏
    private WeakReference<Object> weakReference;

    /**
     * 动态代理
     **/

    public DynamicHandler(Object obj) {
        //实例化弱引用
        this.weakReference = new WeakReference<Object>(obj);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        //获取传入的对象--activity
        Log.e("dylan", "********调用invoke");
        Object handler = weakReference.get();
        if (handler != null) {
            String methodName = method.getName();
            //获取map中的method
            method = methodMap.get(methodName);
            if (method != null) {
                Log.e("dylan", "********进入invoke");
                return method.invoke(handler, objects);

            }
        }
        Log.e("dylan", "*************");
        return null;
    }

    public void addMethod(String name, Method method) {
        Log.e("dylan", "********调用addMethod");
        methodMap.put(name, method);
    }
}
