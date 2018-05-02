package com.itheima.testnewproject.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截Url的Query参数，并增加必要参数和签名  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/2/21 16:34 <br/>
 * Version 1.0
 */
public class QueryParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String fixedUrl = UrlHelper.fixUrl(request.url().toString());

        //替换原来的url。
        Request newRequest = request.newBuilder().url(fixedUrl).build();
        return chain.proceed(newRequest);
    }
}
