package com.itheima.testnewproject.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.testnewproject.R;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.common.loading.SimpleLoading;
import com.itheima.testnewproject.utils.ViewUtil;


/**
 * 下拉刷新功能
 * Author : zhongw <br/>
 */
public abstract class RefreshableFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout vSwipeRefreshLayout;
    private Loading mRefreshLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);

        SwipeRefreshLayout swipeRefreshLayout = ViewUtil.findView(contentView, R.id.refresh_srl);

        if (swipeRefreshLayout == null) {
            swipeRefreshLayout = new SwipeRefreshLayout(contentView.getContext());
            swipeRefreshLayout.setId(R.id.refresh_srl);
            swipeRefreshLayout.setLayoutParams(contentView.getLayoutParams());
            swipeRefreshLayout.addView(contentView,
                    new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT,
                            SwipeRefreshLayout.LayoutParams.MATCH_PARENT));
            contentView = swipeRefreshLayout;
        }

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setOnChildScrollUpCallback(
                new SwipeRefreshLayout.OnChildScrollUpCallback() {
                    @Override
                    public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View target) {
                        ViewGroup loadContentLayout = ViewUtil.findView(parent, R.id.load_content_layout);
                        if (loadContentLayout != null && loadContentLayout.getChildAt(0) != null) {
                            target = loadContentLayout.getChildAt(0);
                        }
                        return ViewCompat.canScrollVertically(target, -1);
                    }
                });

        vSwipeRefreshLayout = swipeRefreshLayout;
        //统一用蓝色的
        vSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.home_tabs_tronker));

        return contentView;
    }

    public void setRefreshable(boolean refreshable) {
        setRefreshing(false);
        vSwipeRefreshLayout.setEnabled(refreshable);
    }

    public boolean isRefreshable() {
        return vSwipeRefreshLayout.isEnabled();
    }

    public void setRefreshing(boolean refreshing) {
        vSwipeRefreshLayout.setRefreshing(refreshing);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return vSwipeRefreshLayout;
    }

    @Override
    public Loading getLoading() {
        if (!isRefreshable()) {
            return super.getLoading();
        }
        Loading loading = mRefreshLoading == null ? super.getLoading() : mRefreshLoading;
        if (mRefreshLoading == null) {
            mRefreshLoading = new RefreshLoading();
        }
        return loading;
    }

    @Override
    public void onReloadClick() {
        super.onReloadClick();
        onRefresh();
    }

    protected void refreshStart() {
        setRefreshing(true);
    }

    protected void refreshError(Throwable throwable) {
        setRefreshing(false);
        super.getLoading().onError(throwable);
    }

    protected void refreshComplete() {
        setRefreshing(false);
        super.getLoading().onFinish();
    }

    class RefreshLoading extends SimpleLoading {
        @Override
        public void onStart() {
            refreshStart();
        }

        @Override
        public void onError(Throwable e) {
            refreshError(e);
        }

        @Override
        public void onFinish() {
            refreshComplete();
        }
    }
}
