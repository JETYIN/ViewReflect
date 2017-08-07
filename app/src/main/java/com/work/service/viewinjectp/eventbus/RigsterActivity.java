package com.work.service.viewinjectp.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.work.service.viewinjectp.R;
import com.work.service.viewinjectp.com.work.service.data.ContentViewInject;
import com.work.service.viewinjectp.com.work.service.data.InjectObject;
import com.work.service.viewinjectp.com.work.service.data.Student;
import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;
import com.work.service.viewinjectp.com.work.service.data.onClickInject;
import com.work.service.viewinjectp.eventbus.message.LoginMessage;
import com.work.service.viewinjectp.eventbus.message.UserInfo;

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
        ViewUtils.register(this);//注册事件分发

    }

    /**事件发送依据接收对象的不同自动发布消息**/
    @onClickInject({R.id.commit_bt})
    public void initClick(View view) {
        //点击按钮进行发送消息
        LoginMessage loginMessage = new LoginMessage(getEtText(userET));
        /**注册界面发布事件**/
        EventBus.getDefault().post(loginMessage);
        /**发送userinfo**/
        UserInfo userInfo = new UserInfo(getEtText(passET));
        EventBus.getDefault().post(userInfo);

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

    @InjectObject
    private void initObject(Object obj) {
        Student student = (Student) obj;

        Toast.makeText(this, "studentname是" + student.getName() + "studentAge是" + student.getAge(), Toast.LENGTH_SHORT).show();

    }
}
