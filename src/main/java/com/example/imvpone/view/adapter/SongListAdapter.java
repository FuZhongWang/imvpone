package com.example.imvpone.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imvpone.R;
import com.example.imvpone.model.bean.SongBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.MySongViewHolder>{
    private Context context;
    private List<SongBean.DataBean> list = new ArrayList<>();
    public SongListAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<SongBean.DataBean> list){
        this.list=list;

    }

    @Override
    public MySongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_song_list, null);
        MySongViewHolder mySongViewHolder = new MySongViewHolder(v);
        return mySongViewHolder;
    }

    @Override
    public void onBindViewHolder(MySongViewHolder holder, int position) {
        holder.songName.setText(list.get(position).getNews_title());
        ImageLoader.getInstance().displayImage(list.get(position).getPic_url(),holder.songGoods);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MySongViewHolder extends RecyclerView.ViewHolder{

        private final TextView songName;
        private final ImageView songGoods;

        public MySongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songName);
            songGoods = itemView.findViewById(R.id.songGoods);
        }
    }
}
