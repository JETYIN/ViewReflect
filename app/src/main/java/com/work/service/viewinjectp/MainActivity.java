package com.work.service.viewinjectp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.work.service.viewinjectp.com.work.service.data.ContentViewInject;
import com.work.service.viewinjectp.com.work.service.data.InjectObject;
import com.work.service.viewinjectp.com.work.service.data.Student;
import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;
import com.work.service.viewinjectp.com.work.service.data.onClickInject;
import com.work.service.viewinjectp.eventbus.AppMainActivity;

/**
 * contentView的注解
 **/
@ContentViewInject(R.layout.activity_main)
public class MainActivity extends Activity {

    /**
     * 采用注解的形式
     **/

    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;
    @ViewInject(R.id.button3)
    private Button button3;
    @ViewInject(R.id.button4)
    private Button button4;
    @ViewInject(R.id.button5)
    private Button button5;
    @ViewInject(R.id.button6)
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);通过注解实现
        /**将当前activity对象传入到动态方法中，进行遍历获取文件声明**/
        //contentview注解
        //view注解
        ViewUtils.inject(this);

        /**模拟分发数据**/
        ViewUtils.getFlectObject(this);
    }

    @InjectObject
    private void initObject(Object obj) {
        Student student = (Student) obj;

        Toast.makeText(this, "studentname是" + student.getName() + "studentAge是" + student.getAge(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    /**
     * 此处方法不能设置为private，否则会crash，找不到方法
     **/
    @onClickInject({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6})
    public void initClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, AppMainActivity.class));
                Toast.makeText(MainActivity.this, "button1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(MainActivity.this, "button2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(MainActivity.this, "button3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Toast.makeText(MainActivity.this, "button4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                Toast.makeText(MainActivity.this, "button5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button6:
                Toast.makeText(MainActivity.this, "button6", Toast.LENGTH_SHORT).show();
                break;

        }


    }

    private void getStactTrace() {
        /**日志管理输出时**/

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];

    }

    private void init() {
        Intent intent = new Intent();
        //intent 可通过putExtra传递实现了序列化的对象
        //intent.putExtra("key",Parcelable)
        //获取对象上一个intent传递过来的实现序列化的对象
        getIntent().getParcelableExtra("key");
    }
}