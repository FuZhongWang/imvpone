package com.example.imvpone.presenter;

import com.example.imvpone.view.interfaces.IBaseView;

/**
 * Created by P7XXTM1-G on 2018/4/21.
 */

public class BasePresenter<T extends IBaseView>{
    private T iBaseView;
    public  void attachView(T iBaseView) {
        this.iBaseView=iBaseView;
    }
    public T getView(){
        return iBaseView;
    }
}
