package com.itheima.testnewproject.rx;


import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public class RxBus {
    private static final PublishSubject<RxEvent> SUBJECT = PublishSubject.create();
    private static final Object EMPTY = new Object();

    private RxBus() {
    }

    public static void post(int code) {
        SUBJECT.onNext(new RxEvent(code, EMPTY));
    }

    public static void post(int code, Object event) {
        SUBJECT.onNext(new RxEvent(code, event));
    }

    public static Observable<Object> filteredEvent(final int eventCode) {
        return SUBJECT
                .filter(new Func1<RxEvent, Boolean>() {
                    @Override
                    public Boolean call(RxEvent rxEvent) {
                        return rxEvent.code == eventCode;
                    }
                })
                .map(new Func1<RxEvent, Object>() {
                    @Override
                    public Object call(RxEvent rxEvent) {
                        return EMPTY;
                    }
                });
    }

    public static <T> Observable<T> filteredEvent(final int eventCode, final Class<T> eventClass) {
        return SUBJECT
                .filter(new Func1<RxEvent, Boolean>() {
                    @Override
                    public Boolean call(RxEvent rxEvent) {
                        return rxEvent.code == eventCode;
                    }
                })
                .map(new Func1<RxEvent, Object>() {
                    @Override
                    public Object call(RxEvent rxEvent) {
                        return rxEvent.event;
                    }
                })
                .ofType(eventClass);
    }

    static class RxEvent {
        int code;
        Object event;

        RxEvent(int code, Object event) {
            this.code = code;
            this.event = event;
        }
    }
}
