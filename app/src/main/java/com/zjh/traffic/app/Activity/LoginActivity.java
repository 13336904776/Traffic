package com.zjh.traffic.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Util.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity {

    private EditText username,password;
    private String userNameValue,passwordValue;
    private CheckBox remember_password,auto_login;
    private Button btn_login,btn_register;
    private SharedPreferencesUtil spUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init_UI();
        is_login();
        ClickListener();
    }

    private void init_UI() {
        //获得实例对象
        spUtil=new SharedPreferencesUtil("userInfo",this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        remember_password=findViewById(R.id.remember_password);
        auto_login=findViewById(R.id.auto_login);
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);
    }

    public void is_login() {
        //判断记住密码多选框的状态
        if((Boolean)spUtil.getSP("ISCHECK", false))
        {
            //设置默认是记录密码状态
            remember_password.setChecked(true);

            username.setText((String)spUtil.getSP("USER_NAME", ""));
            password.setText((String)spUtil.getSP("PASSWORD", ""));
            //判断自动登陆多选框状态
            if((Boolean)spUtil.getSP("AUTO_ISCHECK", false))
            {
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                Toast.makeText(this,"正在登录中",Toast.LENGTH_SHORT).show();
                //跳转界面
                TimerTask task = new TimerTask(){
                    public void run(){
                        startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));
                        finish();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 3000);
            }
        }
    }
    private void ClickListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                userNameValue = username.getText().toString();
                passwordValue = password.getText().toString();

                if(userNameValue.equals("admin")&&passwordValue.equals("admin"))
                {
                    Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                    //登录成功和记住密码框为选中状态才保存用户信息
                    if(remember_password.isChecked())
                    {
                        //记住用户名、密码
                        spUtil.putSP("USER_NAME", userNameValue);
                        spUtil.putSP("PASSWORD", passwordValue);
                    }
                    //跳转界面
                    startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
                }

            }
        });

        //监听记住密码多选框按钮事件
        remember_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (remember_password.isChecked()) {
                    //记住密码已选中
                    spUtil.putSP("ISCHECK", true);

                }else {
                    ///记住密码没有选中
                    spUtil.putSP("ISCHECK", false);

                }

            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto_login.isChecked()) {
                    //自动登录已选中
                    remember_password.setChecked(true);
                    spUtil.putSP("AUTO_ISCHECK", true);

                } else {
                    //自动登录没有选中
                    spUtil.putSP("AUTO_ISCHECK", false);
                }
            }
        });

    }
}
