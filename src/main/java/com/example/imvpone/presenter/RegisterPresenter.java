package com.example.imvpone.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.imvpone.model.callback.HttpUtilsCallback;
import com.example.imvpone.model.http.HttpUtils;

import java.util.HashMap;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class RegisterPresenter extends BasePresenter {

    private final HttpUtils httpUtils;

    public RegisterPresenter() {
        httpUtils = HttpUtils.getHttpUtils();
    }
    public void register(String url, HashMap<String,String> hashMap, final Activity activity, final Context context){
        httpUtils.doPost(url, hashMap, new HttpUtilsCallback() {
            @Override
            public void onSuccess(String code) {
                Log.e("fzw", "onSuccess: "+code );
                if (code.equals("0")){
                    activity.finish();

                }else{
                    Toast.makeText(context,"注册失败请检查账户重复",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
