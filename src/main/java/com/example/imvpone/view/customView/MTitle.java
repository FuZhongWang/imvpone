package com.example.imvpone.view.customView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.imvpone.R;

/**
 * Created by P7XXTM1-G on 2018/4/20.
 */

public class MTitle extends RelativeLayout {

    private ImageView imageView;

    public MTitle(Context context) {
        this(context,null);
    }

    public MTitle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTitleView(context,attrs,defStyleAttr,defStyleRes);
    }

    public void initTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutParams qrlayoutp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView = new ImageView(context);
        imageView.setBackgroundColor(Color.WHITE);
        qrlayoutp.leftMargin=48;
        imageView.setImageResource(R.drawable.sao_hei);
        addView(imageView,qrlayoutp);


    }

}
