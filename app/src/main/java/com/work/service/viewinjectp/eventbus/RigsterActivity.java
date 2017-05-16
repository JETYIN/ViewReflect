package com.work.service.viewinjectp.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.work.service.viewinjectp.R;
import com.work.service.viewinjectp.com.work.service.data.ContentViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;
import com.work.service.viewinjectp.com.work.service.data.onClickInject;
import com.work.service.viewinjectp.eventbus.message.LoginMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/5/16.
 */

@ContentViewInject(R.layout.login_activty)
public class RigsterActivity extends Activity {
    @ViewInject(R.id.user_et)
    private EditText userET;
    @ViewInject(R.id.pass_et)
    private EditText passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

    }

    @onClickInject({R.id.commit_bt})
    public void initClick(View view) {
        //点击按钮进行发送消息
        LoginMessage loginMessage = new LoginMessage(getEtText(userET));
        /**注册界面发布事件**/
        EventBus.getDefault().post(loginMessage);
        startActivity(new Intent(this, AppMainActivity.class));
        this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private String getEtText(EditText et) {

        return et.getText().toString();
    }
}
