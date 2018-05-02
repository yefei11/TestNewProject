package com.itheima.testnewproject.rx;


import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.EmptyUtils;
import com.itheima.testnewproject.common.exception.Exceptions;
import com.itheima.testnewproject.common.loading.DialogLoading;
import com.itheima.testnewproject.common.loading.Loading;

import rx.Observable;
import rx.Observable.Transformer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import timber.log.Timber;

/**
 * RxJava 的 Transformer  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/2/22 9:58 <br/>
 * Version 1.0
 */
public class TransHelper  {

    private TransHelper() {
    }

    /**
     * 附加 DialogLoading 到 Observable 生命周期里。
     * 如果 Dialog Cancel ，Observable 也会相应的 unsubscribe() 。
     */
    public static <T> Transformer<T, T> loadingDialog(@NonNull Context context) {
        final DialogLoading loading = new DialogLoading(context);
        return new Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> resultObservable) {
                //创建一个 PublishSubject，用于转接resultObservable的数据。
                final PublishSubject<T> delegateSubject = PublishSubject.create();
                //订阅 resultObservable，使其开始执行任务，并将结果发送到 delegateSubject ;
                final Subscription resultSubscribe = resultObservable.subscribe(delegateSubject);

                //监听Dialog取消事件，用于取消resultObservable。
                loading.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (resultSubscribe != null && !resultSubscribe.isUnsubscribed()) {
                            resultSubscribe.unsubscribe();
                        }
                    }
                });

                return delegateSubject
                        .doOnUnsubscribe(new Action0() {
                            @Override
                            public void call() {
                                //如果 delegateSubject 取消订阅，相应的 resultSubscribe 也需要取消订阅。
                                if (resultSubscribe != null && !resultSubscribe.isUnsubscribed()) {
                                    resultSubscribe.unsubscribe();
                                }
                            }
                        }).doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                loading.onStart();
                            }
                        }).doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                loading.onError(throwable);
                            }
                        }).doOnNext(new Action1<T>() {
                            @Override
                            public void call(T t) {
                                loading.onFinish();
                            }
                        })
                        .doOnCompleted(new Action0() {
                            @Override
                            public void call() {
                                loading.onFinish();
                            }
                        });
            }
        };
    }


    public static <T> Transformer<T, T> loading(final Loading loading) {
        if (loading == null) {
            Timber.w("Loading is null!");
            return new Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable;
                }
            };
        }
        return new Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        loading.onStart();
                    }
                }).doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (EmptyUtils.isEmpty(t)) {
                            loading.onError(Exceptions.EMPTY_EXCEPTION);
                        } else {
                            loading.onFinish();
                        }
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loading.onError(throwable);
                    }
                });
            }
        };
    }

    /*
    * 隐藏空白页面
    * */
    public static <T> Transformer<T, T> loading2(final Loading loading) {
        if (loading == null) {
            Timber.w("Loading is null!");
            return new Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable;
                }
            };
        }
        return new Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        loading.onStart();
                    }
                }).doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (EmptyUtils.isEmpty(t)) {
                            loading.onFinish();
                        } else {
                            loading.onFinish();
                        }
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loading.onError(throwable);
                    }
                });
            }
        };
    }

    /*
     * 隐藏错误页面
     * */
    public static <T> Transformer<T, T> loading3(final Loading loading) {
        if (loading == null) {
            Timber.w("Loading is null!");
            return new Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> tObservable) {
                    return tObservable;
                }
            };
        }
        return new Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        loading.onStart();
                    }
                }).doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (EmptyUtils.isEmpty(t)) {
                            loading.onFinish();
                        } else {
                            loading.onFinish();
                        }
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loading.onFinish();
                    }
                });
            }
        };
    }

    public static <T> Transformer<T, T> uiScheduler() {
        return new Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
