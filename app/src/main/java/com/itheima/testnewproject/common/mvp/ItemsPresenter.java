package com.itheima.testnewproject.common.mvp;

import com.blankj.utilcode.utils.EmptyUtils;
import com.itheima.testnewproject.rx.SubscriberHelper;
import com.itheima.testnewproject.rx.TransHelper;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public abstract class ItemsPresenter<T, V extends ItemsContract.View<T>> extends BasePresenter<V>
        implements ItemsContract.Presenter {

    protected abstract Observable<List<T>> getListItemsObservable(Map<String, Object> params);

    protected abstract Observable<List<T>> getMoreListItemsObservable(Map<String, Object> params);

    public ItemsPresenter(V view) {
        super(view);
    }

    @Override
    public void fetchListItems(Map<String, Object> params) {
        getListItemsObservable(params)
                .compose(TransHelper.<List<T>>loading(getView().getLoading()))
                .subscribe(SubscriberHelper.create(new Action1<List<T>>() {
                    @Override
                    public void call(List<T> strings) {
                        getView().updateListItems(strings);
                    }
                }));
    }

    @Override
    public void fetchMoreListItems(final Map<String, Object> params) {
        final Observable<List<T>> listObservable = getMoreListItemsObservable(params);
        if (listObservable == null) {
            getView().loadMoreEnd();
            return;
        }
        listObservable.compose(TransHelper.<List<T>>uiScheduler())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().loadMoreFail();
                    }
                })
                .subscribe(
                        SubscriberHelper.create(new Action1<List<T>>() {
                            @Override
                            public void call(List<T> items) {
                                getView().appendListItems(items);
                                if (EmptyUtils.isEmpty(items)) {
                                    getView().loadMoreEnd();
                                } else {
                                    getView().loadMoreComplete();
                                }
                            }
                        }));
    }
}
