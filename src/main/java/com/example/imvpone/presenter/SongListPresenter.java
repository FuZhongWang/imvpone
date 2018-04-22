package com.example.imvpone.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.imvpone.model.bean.SongBean;
import com.example.imvpone.model.callback.HttpUtilsCallback;
import com.example.imvpone.model.http.HttpUtils;
import com.example.imvpone.view.adapter.SongListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class SongListPresenter extends BasePresenter {

    private final HttpUtils getHttpUtils;
    private SongListAdapter songListAdapter;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==2){
                String string = (String) msg.obj;
                Log.e("cs", "handleMessage: "+string );
                Gson gson = new Gson();
                SongBean songBean = gson.fromJson(string, SongBean.class);
                List<SongBean.DataBean> data = songBean.getData();
                songListAdapter.setList((ArrayList<SongBean.DataBean>) data);
                songListAdapter.notifyDataSetChanged();
            }
        }
    };
    public SongListPresenter() {
        getHttpUtils = HttpUtils.getHttpUtils();
    }
    public void loging(final String url, SongListAdapter songListAdapter){
        this.songListAdapter=songListAdapter;
       new Thread(new Runnable() {
           @Override
           public void run() {

               HttpUtils.getHttpUtils().doGet(url, new HttpUtilsCallback() {
                   @Override
                   public void onSuccess(String code) {

                       Message message = handler.obtainMessage();
                       message.obj=code;
                       message.what=2;
                       handler.sendMessage(message);

                   }
               });
           }
       }).start();

    }
}
