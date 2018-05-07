package com.itheima.testnewproject.common.model;


import com.itheima.testnewproject.common.exception.ResultMessageException;
import com.itheima.testnewproject.network.result.Result;

import rx.Observable;
import rx.functions.Func1;

/**
 * 数据访问层基类  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/2/22 9:51 <br/>
 * Version 1.0
 */
public abstract class BaseServiceModel<SERVICE> {
    private SERVICE mService;

    public BaseServiceModel(SERVICE service) {
        mService = service;
    }

    protected SERVICE getService() {
        return mService;
    }

    protected <T, RESULT extends Result<T>> Observable.Transformer<RESULT, T> transformResultToData() {
        return new Observable.Transformer<RESULT, T>() {
            @Override
            public Observable<T> call(Observable<RESULT> resultObservable) {
                return resultObservable
                        .compose(BaseServiceModel.<RESULT>handleResultException())
                        .map(new Func1<RESULT, T>() {
                            @Override
                            public T call(RESULT result) {
                                return result.getData();
                            }
                        });
            }
        };
    }


    protected <RESULT extends Result> Observable.Transformer<RESULT, String> transformResultToMsg() {
        return new Observable.Transformer<RESULT, String>() {
            @Override
            public Observable<String> call(Observable<RESULT> resultObservable) {
                return resultObservable
                        .compose(BaseServiceModel.<RESULT>handleResultException())
                        .map(new Func1<RESULT, String>() {
                            @Override
                            public String call(RESULT result) {
                                return result.getMessage();
                            }
                        });
            }
        };
    }


    private static <RESULT extends Result<?>> Observable.Transformer<RESULT, RESULT> handleResultException() {
        return new Observable.Transformer<RESULT, RESULT>() {
            @Override
            public Observable<RESULT> call(Observable<RESULT> resultObservable) {
                return resultObservable
                        .flatMap(new Func1<RESULT, Observable<RESULT>>() {
                            @Override
                            public Observable<RESULT> call(RESULT result) {
                                String errorMessage;
                                if (!result.isSuccess()) {
                                    errorMessage = result.getMessage();
                                    return Observable.error(new ResultMessageException(errorMessage));
                                }
                                return Observable.just(result);
                            }
                        });
            }
        };
    }
}
