package com.itheima.testnewproject.rx;

import android.support.annotation.CallSuper;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public class SafeSubscriber<T> extends Subscriber<T> {

    private Action1<? super T> onNext;
    private Action1<Throwable> onError;
    private Action0 onStart;
    private Action0 onComplete;

    public SafeSubscriber(Action1<? super T> onNext) {
        this(onNext, null);
    }

    public SafeSubscriber(Action1<? super T> onNext, Action1<Throwable> onError) {
        this(onNext, onError, null);
    }

    public SafeSubscriber(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onComplete) {
        this(onNext, onError, null, onComplete);
    }

    public SafeSubscriber(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onStart, Action0 onComplete) {
        this.onNext = onNext;
        this.onError = onError;
        this.onStart = onStart;
        this.onComplete = onComplete;
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        if (onStart != null) {
            onStart.call();
        }
    }

    @Override
    @CallSuper
    public void onCompleted() {
        if (onComplete != null) {
            onComplete.call();
        }
    }

    @Override
    @CallSuper
    public void onError(Throwable e) {
        Timber.e(e);
        if (onError != null) {
            onError.call(e);
        }
    }

    @Override
    @CallSuper
    public void onNext(T t) {
        if (onNext != null) {
            onNext.call(t);
        }
    }
}
