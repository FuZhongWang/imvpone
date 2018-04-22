package com.example.imvpone.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.imvpone.model.callback.HttpUtilsCallback;
import com.example.imvpone.model.http.HttpUtils;
import com.example.imvpone.view.activity.SongListActivity;

import java.util.HashMap;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class MainPresenter extends BasePresenter{

    private final HttpUtils httpUtils;

    public MainPresenter() {
        this.httpUtils=HttpUtils.getHttpUtils();

    }
    public void login(String url, HashMap<String,String>hashMap, final Context context, final Activity activity){

        httpUtils.doPost(url, hashMap, new HttpUtilsCallback() {
            @Override
            public void onSuccess(String code) {
                if (code.equals("0")){
                    Intent intentSong = new Intent(context, SongListActivity.class);

                    context.startActivity(intentSong);
                    activity.finish();
                }else{
                    Toast.makeText(context,"账号密码错误请重新输入", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
