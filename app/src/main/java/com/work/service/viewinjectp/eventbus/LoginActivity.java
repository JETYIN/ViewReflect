package com.work.service.viewinjectp.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.work.service.viewinjectp.R;
import com.work.service.viewinjectp.com.work.service.data.ContentViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;
import com.work.service.viewinjectp.com.work.service.data.onClickInject;
import com.work.service.viewinjectp.eventbus.message.LoginMessage;
import com.work.service.viewinjectp.eventbus.message.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/5/16.
 */

@ContentViewInject(R.layout.login_activty)
public class LoginActivity extends Activity {

    @ViewInject(R.id.user_et)
    private EditText userET;
    @ViewInject(R.id.pass_et)
    private EditText passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 根据消息发布者发布的信息类型接收消息数据
     **/
    //消息处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventMessage(UserInfo userInfo) {
        Toast.makeText(this, "Login-" + userInfo.getUserName(), Toast.LENGTH_SHORT).show();


    }

    //点击事件
    @onClickInject({R.id.commit_bt})
    public void initClick(View view) {

        startActivity(new Intent(this, RigsterActivity.class));
    }
}
