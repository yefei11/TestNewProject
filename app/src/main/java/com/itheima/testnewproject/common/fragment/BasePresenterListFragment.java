package com.itheima.testnewproject.common.fragment;

import android.support.annotation.NonNull;

import com.itheima.testnewproject.common.mvp.ItemsContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * 用于展示列表的Fragment  <br/>
 * Author : zhongw <br/>
 */
@SuppressWarnings("ALL")
public abstract class BasePresenterListFragment<T, Presenter extends ItemsContract.Presenter>
        extends BaseListFragment<T> implements ItemsContract.View<T> {

    @Inject
    Presenter mPresenter;

    public Presenter getPresenter() {
        return mPresenter;
    }


    public void fetchListItems(@NonNull Map<String, Object> params) {
        getPresenter().fetchListItems(params);
    }

    public void fetchMoreListItems(@NonNull Map<String, Object> params) {
        getPresenter().fetchMoreListItems(params);
    }
}
