package com.itheima.testnewproject.common.mvp;

import android.support.v4.app.Fragment;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;

/**
 * Fragment 对应的 IView  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/20 16:26 <br/>
 * Version 1.0
 */
public interface FView extends IView, LifecycleProvider<FragmentEvent> {
    Fragment getFragment();
}
