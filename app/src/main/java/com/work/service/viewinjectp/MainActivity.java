package com.work.service.viewinjectp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.work.service.viewinjectp.com.work.service.data.ViewInject;
import com.work.service.viewinjectp.com.work.service.data.ViewUtils;

public class MainActivity extends Activity implements View.OnClickListener {

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
        setContentView(R.layout.activity_main);
        /**将当前activity对象传入到动态方法中，进行遍历获取文件声明**/
        ViewUtils.injectView(this);
        init();
    }

    private void init() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(MainActivity.this,"button1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(MainActivity.this,"button2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(MainActivity.this,"button3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Toast.makeText(MainActivity.this,"button4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                Toast.makeText(MainActivity.this,"button5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button6:
                Toast.makeText(MainActivity.this,"button6",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
