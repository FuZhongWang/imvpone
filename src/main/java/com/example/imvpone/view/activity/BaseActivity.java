package com.example.imvpone.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.imvpone.presenter.BasePresenter;
import com.example.imvpone.view.interfaces.IBaseView;

/**
 * Created by P7XXTM1-G on 2018/4/20.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public T basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        this.basePresenter=basePresenter();
        basePresenter.attachView(this);

        initView();

    }

    public abstract int getContentView();
    public abstract void initView();
    public abstract T basePresenter();
    public abstract void fail();
}
