package com.itheima.testnewproject.common.mvp;

import android.app.Activity;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;

/**
 * Activity 对应的 IView  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/20 16:26 <br/>
 * Version 1.0
 */
public interface AView extends IView, LifecycleProvider<ActivityEvent> {
    Activity getActivity();
}
