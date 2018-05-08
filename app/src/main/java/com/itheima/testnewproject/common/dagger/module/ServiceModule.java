package com.itheima.testnewproject.common.dagger.module;


import com.blankj.utilcode.utils.NetworkUtils;
import com.itheima.testnewproject.common.dagger.scope.NetworkScope;
import com.itheima.testnewproject.module.home.service.IndexService;
import com.itheima.testnewproject.module.jinfu.service.FinancingService;
import com.itheima.testnewproject.network.exception.NoNetworkException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/6 17:01 <br/>
 * Version 1.0
 */
@Module
public class ServiceModule {

  /*  @NetworkScope
    @Provides
    AgenyService provideAgenyService(Retrofit retrofit) {
        return createService(retrofit, AgenyService.class);
    }*/
  @NetworkScope
  @Provides
  FinancingService provideFinancingService(Retrofit retrofit) {
      return createService(retrofit, FinancingService.class);
  }

    @NetworkScope
    @Provides
    IndexService provideIndexService(Retrofit retrofit) {
        return createService(retrofit, IndexService.class);
    }

    private <SERVICE> SERVICE createService(Retrofit retrofit, final Class<SERVICE> service) {
        final SERVICE originService = retrofit.create(service);

        @SuppressWarnings("unchecked")
        SERVICE proxyService =
                (SERVICE) Proxy.newProxyInstance(service.getClassLoader(),
                        new Class<?>[]{service},
                        new InvocationHandler() {
                            @SuppressWarnings("unchecked")
                            @Override
                            public Object invoke(Object proxy, Method method, Object... args)
                                    throws Throwable {
                                Object invokeResult = method.invoke(originService, args);
                                if (invokeResult instanceof Observable) {
                                    if (NetworkUtils.isConnected()) {
                                        final Observable observable = (Observable) invokeResult;
                                        return observable;
                                    } else {
                                        return Observable.error(new NoNetworkException());
                                    }
                                }
                                return invokeResult;
                            }
                        });
        return proxyService;
    }
}
