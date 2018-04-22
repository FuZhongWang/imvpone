package com.example.imvpone.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.imvpone.R;
import com.example.imvpone.presenter.RegisterPresenter;
import com.example.imvpone.utils.AccountValidatorUtil;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements View.OnClickListener {
    private String url = "user/reg";
    private EditText rmoblie;
    private EditText rpassword;
    private EditText rpassword_sure;
    private TextView ralerting;
    private Button registers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        rmoblie = (EditText) findViewById(R.id.rmoblie);
        rpassword = (EditText) findViewById(R.id.rpassword);
        rpassword_sure = (EditText) findViewById(R.id.rpassword_sure);
        ralerting = (TextView) findViewById(R.id.ralerting);
        registers = (Button) findViewById(R.id.registers);
        registers.setOnClickListener(this);

    }

    @Override
    public RegisterPresenter basePresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void fail() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onClick(View view) {

        if (!AccountValidatorUtil.isMobile(rmoblie.getText().toString())){

            ralerting.setText(R.string.wrong_moblie);
            return;
        }
        if (!AccountValidatorUtil.isPassword(rpassword.getText().toString())){
            ralerting.setText(R.string.wrong_password);
            return;
        }
                if (!rpassword.getText().toString().equals(rpassword_sure.getText().toString())){
                    ralerting.setText(R.string.wrong_password_sure);
                    return;
                }

        ralerting.setText("");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile",rmoblie.getText().toString());
        hashMap.put("mobile",rpassword.getText().toString());
        basePresenter.register(url,hashMap,this,this);
    }
}
