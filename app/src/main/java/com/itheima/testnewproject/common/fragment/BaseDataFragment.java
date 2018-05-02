package com.itheima.testnewproject.common.fragment;


import com.itheima.testnewproject.rx.TransHelper;

import rx.Observable;
import rx.functions.Action1;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public abstract class BaseDataFragment<T> extends BaseFragment {
    protected abstract Observable<T> loadDataObservable();

    protected abstract void onLoadDataSuccess(T data);

    protected abstract void onLoadDataError(Throwable throwable);

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        startLoadData();
    }

    @Override
    public void onReloadClick() {
        super.onReloadClick();
        startLoadData();
    }

    private void startLoadData() {
        Observable<T> dataObservable = loadDataObservable();
        if (dataObservable != null)
            dataObservable
                    .compose(TransHelper.<T>uiScheduler())
                    .compose(this.<T>bindToLifecycle())
                    .compose(TransHelper.<T>loading(getLoading()))
                    .subscribe(new Action1<T>() {
                        @Override
                        public void call(T t) {
                            onLoadDataSuccess(t);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            onLoadDataError(throwable);
                        }
                    });
    }

}
