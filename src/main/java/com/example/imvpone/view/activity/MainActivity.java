package com.example.imvpone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.imvpone.R;
import com.example.imvpone.presenter.MainPresenter;
import com.example.imvpone.utils.AccountValidatorUtil;

import java.util.HashMap;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener {

    private EditText moblie;
    private EditText password;
    /*private EditText password_sure;*/
    private Button login;
    private Button register;
    private TextView alerting;
    private String urllogin = "user/login";
    public Context context = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        moblie = (EditText) findViewById(R.id.moblie);
        password = (EditText) findViewById(R.id.password);
       /* password_sure = (EditText) findViewById(R.id.password_sure);*/
        alerting = (TextView) findViewById(R.id.alerting);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }


    @Override
    public MainPresenter basePresenter() {
        return new MainPresenter();
    }

    @Override
    public void fail() {
        alerting.setText("账号密码错误请重新输入");
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                if (!AccountValidatorUtil.isMobile(moblie.getText().toString())){

                    alerting.setText(R.string.wrong_moblie);
                    return;
                }
                if (!AccountValidatorUtil.isPassword(password.getText().toString())){
                    alerting.setText(R.string.wrong_password);
                    return;
                }
               /* if (password.getText().toString()!=password_sure.getText().toString()){
                    alerting.setText(R.string.wrong_password_sure);
                    return;
                }*/
                alerting.setText("");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("mobile",moblie.getText().toString());
                hashMap.put("password",password.getText().toString());
                basePresenter.login(urllogin,hashMap,MainActivity.this,this);


                break;
            case R.id.register:
                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intentRegister,100);
                ;break;



        }

    }
}
