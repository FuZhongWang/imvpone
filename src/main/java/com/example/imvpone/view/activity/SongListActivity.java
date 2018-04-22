package com.example.imvpone.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.imvpone.R;
import com.example.imvpone.model.bean.SongBean;
import com.example.imvpone.presenter.SongListPresenter;
import com.example.imvpone.view.adapter.SongListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends BaseActivity<SongListPresenter> implements SwipeRefreshLayout.OnRefreshListener {
    public String url = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    public int path = 0;
    private ArrayList<SongBean.DataBean> list = new ArrayList<SongBean.DataBean>();
    private RecyclerView recycler;
    private SongListAdapter songListAdapter;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
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
    private SwipeRefreshLayout refreshl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_song_list;
    }

    @Override
    public void initView() {
        refreshl = (SwipeRefreshLayout) findViewById(R.id.refreshlayout);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        refreshl.setOnRefreshListener(this);
        songListAdapter = new SongListAdapter(this);

        recycler.setAdapter(songListAdapter);
        songListAdapter.notifyDataSetChanged();
        basePresenter.loging(url+path,songListAdapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                path+=1;
                basePresenter.loging(url+path,songListAdapter);
                refreshl.setRefreshing(false);
            }
        });

    }

    @Override
    public SongListPresenter basePresenter() {
        return new SongListPresenter();
    }

    @Override
    public void fail() {

    }

    @Override
    public void onSuccess() {

    }


    @Override
    public void onRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);
        basePresenter.loging(url+path,songListAdapter);
        refreshl.setRefreshing(false);
    }
}
