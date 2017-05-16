package com.work.service.viewinjectp.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.work.service.viewinjectp.R;
import com.work.service.viewinjectp.com.work.service.data.ContentViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;
import com.work.service.viewinjectp.com.work.service.data.onClickInject;
import com.work.service.viewinjectp.eventbus.message.LoginMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/5/16.
 */

@ContentViewInject(R.layout.app_activity)
public class AppMainActivity extends Activity {

    /**
     * @params ThreadMode 四种模式
     **/
    //注解view
    @ViewInject(R.id.collect_bt)
    private Button button;
    @ViewInject(R.id.content_tv)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        /**1.当前activity绑定eventBus**/
        //在主界面注册eventBus--绑定了当前activity
        EventBus.getDefault().register(this);
    }

    //点击事件注解--只能为public

    @onClickInject({R.id.collect_bt})
    public void initClick(View view) {
        //点击事件
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * 3.处理消息事件--通过反射实现，需要定义成public,定义当前事件线程在ui线程中处理
     **/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerEventMessage(LoginMessage loginMessage) {
        String message = loginMessage.getMessage();

        /**更新线程操作未成功**/
        textView.setText("已登录:" + message);

        Toast.makeText(this, "app" + message, Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销eventBus
        /**2.注销eventBus**/
        EventBus.getDefault().unregister(this);
    }
}
