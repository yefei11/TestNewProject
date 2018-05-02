package com.itheima.testnewproject.common.mvp;

/**
 * Activity 对应的 IView  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/20 16:26 <br/>
 * Version 1.0
 */
public interface ProAView extends AView {
    void onStartLoading();
    void onGetDataFailed(Throwable throwable);
    void onGetDataComplete();
}
