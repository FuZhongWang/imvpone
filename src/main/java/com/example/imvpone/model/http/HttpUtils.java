package com.example.imvpone.model.http;

import android.util.Log;

import com.example.imvpone.model.bean.RequestCode;
import com.example.imvpone.model.callback.HttpUtilsCallback;
import com.example.imvpone.utils.constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class HttpUtils implements Callback {
    //恶汉式
    private HttpUtilsCallback httpUtilsCallback;
   private static HttpUtils httpUtils = new HttpUtils();
   private OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private HttpUtils() {
    }
    public static HttpUtils getHttpUtils(){

        return httpUtils;
    }
    public void doGet(String url, final HttpUtilsCallback httpUtilsCallback){
        this.httpUtilsCallback=httpUtilsCallback;
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                httpUtilsCallback.onSuccess(string);
            }
        });

    }
    public void doPost(String url, HashMap<String,String> hashMap,HttpUtilsCallback httpUtilsCallback){
        this.httpUtilsCallback=httpUtilsCallback;
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = hashMap.get(key);
            builder.add(key,value);

        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(constant.SITE + url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(this);
    }
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        String string = response.body().string();
        Log.e("fzw", "onResponse: "+string);
        Gson gson = new Gson();
        RequestCode requestCode = gson.fromJson(string, RequestCode.class);
        String code = requestCode.getCode();
        httpUtilsCallback.onSuccess(code);

    }
}
