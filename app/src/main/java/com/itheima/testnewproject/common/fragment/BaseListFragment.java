package com.itheima.testnewproject.common.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.utils.ViewUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 用于展示列表的Fragment  <br/>
 * Author : zhongw <br/>
 */
@SuppressWarnings("ALL")
public abstract class BaseListFragment<T> extends RefreshableFragment {

    RecyclerView rvList;

    FrameLayout layoutTop;
    FrameLayout layoutBottom;
    FrameLayout layoutBottom2;
    FrameLayout rootFramLayout;
    private View floatTopView;
    private BaseQuickAdapter<T, ? extends BaseViewHolder> mAdapter;

    private int mPageIndex = 1;
    private int limit = 10;
    private int offset = 0;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    private ItemClickListener mItemClickListener;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public FrameLayout getRootFramLayout() {
        return rootFramLayout;
    }

    /**
     * RecyclerView 的Adapter , 用于展示Item
     */
    protected abstract BaseQuickAdapter<T, ? extends BaseViewHolder> createAdapter();

    protected abstract void fetchListItems(Map<String, Object> params);

    protected abstract void fetchMoreListItems(final Map<String, Object> params);

    @Override
    protected int getLayoutResId() {
        return R.layout.t2_layout_list;
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvList = ViewUtil.findView(view, R.id.recycler_view);
        layoutTop = ViewUtil.findView(view, R.id.top_layout);
        layoutBottom = ViewUtil.findView(view, R.id.bottom_layout);
        layoutBottom2 = ViewUtil.findView(view, R.id.bottom_layout2);
        rootFramLayout = ViewUtil.findView(view, R.id.root_fram_layout);
        if (rvList == null) {
            throw new NullPointerException("Layout中必须包含 " + RecyclerView.class.getName() + " !");
        }
        mAdapter = createAdapter();
        if (getFooterView() != null) {
            mAdapter.addFooterView(getFooterView());
        }
        setLoadMoreEnable(true);

        rvList.setAdapter(mAdapter);

        rvList.setLayoutManager(buildLayoutManager());

        rvList.setItemAnimator(new DefaultItemAnimator());
        List<? extends ItemDecoration> itemDecorations = buildItemDecorations();
        if (itemDecorations != null) {
            for (ItemDecoration itemDecoration : itemDecorations) {
                rvList.addItemDecoration(itemDecoration);
            }
        }
        mItemClickListener = new ItemClickListener();
        rvList.addOnItemTouchListener(mItemClickListener);

    }

    public View getFooterView() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchListItems(getFetchListItemsParams());
    }

    @Override
    public Loading getLoading() {
        return new RefreshLoading();
    }

    @NonNull
    protected Map<String, Object> getFetchListItemsParams() {
        return new HashMap<>(0);
    }

    @NonNull
    protected Map<String, Object> getFetchMoreListItemsParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", getPageIndex());
        return params;
    }

    protected List<? extends ItemDecoration> buildItemDecorations() {
        return Collections.singletonList(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider)
                .size(getDividerSize())
                .showLastDivider()
                .build());
    }

    protected int getDividerSize() {
        return getResources().getDimensionPixelSize(R.dimen.divider_1);
    }

    protected LayoutManager buildLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    /**
     * 当加载数据为空或获取数据失败时，点击页面调用的方法
     */
    @Override
    public void onReloadClick() {
        super.onReloadClick();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        offset = 0;
        refresh();
    }

    public void updateListItems(List<T> items) {
        getAdapter().setNewData(items);

    }

    public void appendListItems(List<T> items) {
        getAdapter().addData(items);
    }

    @Override
    protected void refreshComplete() {
        super.refreshComplete();
        mPageIndex++;
        offset += limit;
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public void loadMoreComplete() {
        Observable.timer(50, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        getAdapter().loadMoreComplete();
                    }
                });
        mPageIndex++;
        offset += limit;
    }

    public void loadMoreFail() {
        Observable.timer(50, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        getAdapter().loadMoreFail();
                    }
                });
    }

    public void loadMoreEnd() {
        Observable.timer(50, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        getAdapter().loadMoreEnd(isHideLoadMoreEnd());
                    }
                });
    }

    protected boolean isHideLoadMoreEnd() {
        return false;
    }

    protected void setLoadMoreEnable(boolean enable) {
        if (enable && mAdapter != null) {
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (getAdapter().getData().size() >= limit) {
                        fetchMoreListItems(getFetchMoreListItemsParams());
                    } else {
                        loadMoreEnd();
                    }
                }
            });
        } else {
            mAdapter.setOnLoadMoreListener(null);
        }
    }

    public void onItemClick(T item, BaseQuickAdapter adapter, android.view.View view, int position) {
    }

    public void onItemLongClick(T item, BaseQuickAdapter adapter, android.view.View view, int position) {
    }

    public void onItemChildClick(T item, BaseQuickAdapter adapter, android.view.View view, int position) {
    }

    public void onItemChildLongClick(T item, BaseQuickAdapter adapter, android.view.View view, int position) {
    }

    public void addTopView(android.view.View topView) {
        layoutTop.addView(topView);
    }

    public void addFloatTopView(View floatTopView) {
        this.floatTopView = floatTopView;
        rootFramLayout.addView(floatTopView);
    }

    public View getFloatTopView() {
        return floatTopView;
    }

    public void addBottomView(android.view.View topView) {
        layoutBottom.addView(topView);
    }

    public FrameLayout getLayoutBottom2() {
        return layoutBottom2;
    }

    public BaseQuickAdapter<T, ? extends BaseViewHolder> getAdapter() {
        return mAdapter;
    }

    /**
     * 刷新数据
     */
    public void refresh() {
        fetchListItems(getFetchListItemsParams());
    }

    public RecyclerView getRecyclerView() {
        return rvList;
    }

    @Override
    public void onDestroyView() {
        rvList.removeOnItemTouchListener(mItemClickListener);
        rvList.clearOnScrollListeners();
        super.onDestroyView();
    }

    private class ItemClickListener extends SimpleClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, android.view.View view, int position) {
            BaseListFragment.this.onItemClick(((T) adapter.getItem(position)), adapter, view, position);
        }

        @Override
        public void onItemLongClick(BaseQuickAdapter adapter, android.view.View view, int position) {
            BaseListFragment.this.onItemLongClick(((T) adapter.getItem(position)), adapter, view, position);
        }

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, android.view.View view, int position) {
            BaseListFragment.this.onItemChildClick(((T) adapter.getItem(position)), adapter, view, position);
        }

        @Override
        public void onItemChildLongClick(BaseQuickAdapter adapter, android.view.View view, int position) {
            BaseListFragment.this.onItemChildLongClick(((T) adapter.getItem(position)), adapter, view, position);
        }
    }
}
