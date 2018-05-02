package com.itheima.testnewproject.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itheima.testnewproject.common.mvp.ItemsContract;
import com.itheima.testnewproject.common.mvp.ItemsPresenter;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * 简易ListActivity，只需要提供数据源  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/16 16:51 <br/>
 * Version 1.0
 */
public abstract class SimpleListFragment<T> extends BaseListFragment<T> implements ItemsContract.View<T> {

    private ItemsPresenter<T, ItemsContract.View<T>> mItemsPresenter;

    protected abstract Observable<List<T>> getListItemsObservable(Map<String, Object> params);

    protected abstract Observable<List<T>> getMoreListItemsObservable(Map<String, Object> params);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemsPresenter = new ItemsPresenter<T, ItemsContract.View<T>>(this) {

            @Override
            protected Observable<List<T>> getListItemsObservable(Map<String, Object> params) {
                return SimpleListFragment.this.getListItemsObservable(params);
            }

            @Override
            protected Observable<List<T>> getMoreListItemsObservable(Map<String, Object> params) {
                return SimpleListFragment.this.getMoreListItemsObservable(params);
            }
        };
    }

    @Override
    protected void fetchListItems(Map<String, Object> params) {
        mItemsPresenter.fetchListItems(params);
    }

    @Override
    protected void fetchMoreListItems(Map<String, Object> params) {
        mItemsPresenter.fetchMoreListItems(params);
    }
}
