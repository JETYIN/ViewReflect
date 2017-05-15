package com.work.service.viewinjectp.com.work.service.data;

import android.app.Activity;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ViewUtils {

    //定义的反射方法，遍历运行时的class，进行注解
    /**设置为findViewById--查找对应组件的id**/

    private static String FIND_VIEW_BY_ID = "findViewById";

    /**
     * 动态传入activity对象
     **/
    public static void injectView(Activity activity) {

        //动态获取class
        Class cla = activity.getClass();
        //获取到class中的所有字段
        Field[] files = cla.getDeclaredFields();

        //遍历整个字段，得到被注解的字段
        for (Field field : files) {

            //透过@获取被注解的字段说明
            if (field.isAnnotationPresent(ViewInject.class)) {

                //得到被注解字段的注解对象
                ViewInject viewInject = field.getAnnotation(ViewInject.class);

                //获取被注解字段下的对应值--调用注解接口中的方法
                int viewId = viewInject.value();
                /**利用反射方法获取当前运行activity中的设置viewid(此类设置为findViewById)方法**/

               //获取查找view的方法
                try {

                    Method method=cla.getMethod(FIND_VIEW_BY_ID,int.class);
                    method.setAccessible(true);
                    Object resView = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, resView);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
