package com.work.service.viewinjectp.com.work.service.data;

import android.app.Activity;
import android.util.Log;
import android.view.View;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ViewUtils {

    //定义的反射方法，遍历运行时的class，进行注解
    /**
     * 设置为findViewById--查找对应组件的id
     **/

    private static String FIND_VIEW_BY_ID = "findViewById";
    private static String SET_CONTENT_VIEW = "setContentView";

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

                    /**setAccessible(boolean)--true反射对象在使用时应取消java语言访问检查（可提高运行速率）
                     * false-反射对象应该实施java语言访问检查**/
                    Method method = cla.getMethod(FIND_VIEW_BY_ID, int.class);
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

    /**
     * 对contetntView的注解
     **/

    public static void injectContentView(Activity activity) {
        //contentView只有一个注解标签，所以不再获取filed字段
        Class cla = activity.getClass();

        //当前注解标签存在于目标类中时
        if (cla.isAnnotationPresent(ContentViewInject.class)) {
            //获取注解对象
            ContentViewInject contentViewInject = (ContentViewInject) cla.getAnnotation(ContentViewInject.class);

            //得到注解的值(id)
            int contentViewId = contentViewInject.value();

            /**获取到对应的注解值，通过反射调用setContentView方法**/
            //获取到该方法
            try {
                Method method = cla.getMethod(SET_CONTENT_VIEW, int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对点击事件的注解
     **/

    public static void injectOnClick(Activity activity) {
        Class cla = activity.getClass();
        /**onclick注解的是方法，所以应找到整个activity中的方法**/
        Method[] methods = cla.getDeclaredMethods();
        //遍历整个方法得到被注解的方法
        for (Method methodItem : methods) {
            //如果当前方法被onclickInject标记
            if (methodItem.isAnnotationPresent(onClickInject.class)) {

                //获得onClickInject
                onClickInject clickInject = methodItem.getAnnotation(onClickInject.class);

                //获取注解下的值
                int clickValue[] = clickInject.value();

                /**获取onClickInject上的Clickenvents**/
                ClickEnvents clickEnvents = clickInject.annotationType().getAnnotation(ClickEnvents.class);

                /**获取对应ClickEnvents注解下的值**/

                //注册监听
                String setOnclickListener = clickEnvents.setListener();
                //点击方法onclick
                String onclick = clickEnvents.clickMethod();
                Class<?> onclickListener = clickEnvents.listenerType();

                /**使用动态代理*
                 *
                 */
                //将当前activty传入动态代理
                DynamicHandler dynamicHandler = new DynamicHandler(activity);
                //动态创建代理者--View.OnClickListener
                Object proxyListener = Proxy.newProxyInstance(onclickListener.getClassLoader(), new Class<?>[]{onclickListener}, dynamicHandler);
                //动态代理加入方法
                dynamicHandler.addMethod(onclick, methodItem);
                Log.e("dylan", "******dynamicHandler");
                /**为每个view设置点击事件**/
                for (int viewId : clickValue) {
                    Log.e("dylan", "******viewId");
                    try {
                        Method findMethod = cla.getMethod(FIND_VIEW_BY_ID, int.class);

                        findMethod.setAccessible(true);
                        //调用findViewById方法获取到view
                        View view = (View) findMethod.invoke(activity, viewId);
                        //为view添加监听事

                        Method setListenerMethod = view.getClass().getMethod(setOnclickListener, onclickListener);

                        setListenerMethod.setAccessible(true);
                        setListenerMethod.invoke(view, proxyListener);//调用动态代理对象
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
