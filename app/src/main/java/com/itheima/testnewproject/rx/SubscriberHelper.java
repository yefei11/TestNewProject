package com.itheima.testnewproject.rx;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * 用于创建安全的Subscriber <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/9 17:08 <br/>
 * Version 1.0
 */
public class SubscriberHelper {
    private SubscriberHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Subscriber<T> empty() {
        return from(Observers.empty());
    }

    public static <T> Subscriber<T> from(final Observer<? super T> o) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
                o.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                o.onError(e);
            }

            @Override
            public void onNext(T t) {
                o.onNext(t);
            }

        };
    }

    public static <T> Subscriber<T> create(final Action1<? super T> onNext) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }

        return new SafeSubscriber<>(onNext);
    }

    public static <T> Subscriber<T> create(final Action1<? super T> onNext, final Action1<Throwable> onError) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        }

        return new SafeSubscriber<>(onNext, onError);
    }

    public static <T> Subscriber<T> create(final Action1<? super T> onNext, final Action1<Throwable> onError,
                                           final Action0 onComplete) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (onComplete == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }

        return new SafeSubscriber<>(onNext, onError, onComplete);
    }

    public static <T> Subscriber<T> create(final Action1<? super T> onNext, final Action1<Throwable> onError,
                                           final Action0 onStart, final Action0 onComplete) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (onComplete == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }

        return new SafeSubscriber<>(onNext, onError, onStart, onComplete);
    }

}
