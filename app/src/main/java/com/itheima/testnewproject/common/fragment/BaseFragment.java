package com.itheima.testnewproject.common.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.itheima.testnewproject.R;
import com.itheima.testnewproject.common.dagger.scope.FragmentScope;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.utils.ViewUtil;
import com.itheima.testnewproject.widget.LoadingLayout;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;


/**
 * 基础Fragment,包括公共部分的功能.
 * Author : zhongw <br/>
 */
@FragmentScope
public abstract class BaseFragment extends RxFragment implements KeyEvent.Callback {

    LoadingLayout vLoadingLayout;

    private Loading mLoading;

    protected boolean isVisible;
    private boolean hadCreated;

    @LayoutRes
    abstract protected int getLayoutResId();

    protected void initInjector() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initInjector();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(getLayoutResId(), container, false);

        LoadingLayout loadingLayout = ViewUtil.findView(contentView, R.id.layout_loading);

        if (loadingLayout == null) {
            loadingLayout = new LoadingLayout(contentView.getContext());
            loadingLayout.setId(R.id.layout_loading);
            loadingLayout.setLayoutParams(contentView.getLayoutParams());
            loadingLayout.addView(contentView,
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT));
            contentView = loadingLayout;
        }

        loadingLayout.setOnLoadingClickListener(new LoadingLayout.OnLoadingClickListener() {
            @Override
            public void onReloadClick() {
                BaseFragment.this.onReloadClick();
            }
        });
        setLoading(loadingLayout);
        vLoadingLayout = loadingLayout;

        ButterKnife.bind(this, loadingLayout);


        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            lazyLoad();
        }
        hadCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && hadCreated) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
    }

    protected void onInvisible() {
    }

    public LoadingLayout getLoadingLayout() {
        return vLoadingLayout;
    }

    public Loading getLoading() {
        return mLoading;
    }

    public void setLoading(Loading loading) {
        mLoading = loading;
    }

    public void onReloadClick() {

    }

    public Fragment getFragment() {
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
