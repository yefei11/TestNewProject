package com.itheima.testnewproject.common.mvp;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/19 17:58 <br/>
 * Version 1.0
 */
public class BasePresenter<V extends IView> implements IPresenter {
    private V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
