package com.itheima.testnewproject.common.dagger.module;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.itheima.testnewproject.BuildConfig;
import com.itheima.testnewproject.common.dagger.scope.NetworkScope;
import com.itheima.testnewproject.network.JsonUtil;
import com.itheima.testnewproject.network.interceptor.MonitorHttpInterceptor;
import com.itheima.testnewproject.network.interceptor.QueryParamsInterceptor;
import com.itheima.testnewproject.network.interceptor.ResultInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/6 17:36 <br/>
 * Version 1.0
 */

@Module
public class NetworkModule {
    private static final long DEFAULT_TIMEOUT_SECONDS = 20;
    private static final long CACHE_MAX_SIZE = 10 * 1024 * 1024;

    private String mBaseUrl;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @NetworkScope
    @Provides
    SSLSocketFactory provideSSLSocketFactory(Context context) {
        return null;
    }

    @NetworkScope
    @Provides
    Cache provideCache(Context context) {

        //代码级错误，将cache保存到SDCard根目录了
        // 需要修正，将老的cache文件清除掉
        // 3.5.3 以后的版本可以将这段清除的代码删掉
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "journal");
            if (file.exists()) {
                Cache oldCache = new Cache(Environment.getExternalStorageDirectory(), CACHE_MAX_SIZE);
                oldCache.evictAll();
                oldCache.close();
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
        } catch (Exception ignored) {
        }

        File cacheDir =
                TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)
                        ? context.getExternalCacheDir() : context.getCacheDir();

        return new Cache(cacheDir, CACHE_MAX_SIZE);
    }

    @NetworkScope
    @Provides
    protected List<Interceptor> provideInterceptors() {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new QueryParamsInterceptor());
        interceptors.add(new ResultInterceptor());
        interceptors.add(new MonitorHttpInterceptor());
        return interceptors;
    }

    @NetworkScope
    @Provides
    @Named("network_interceptors")
    List<Interceptor> provideNetworkInterceptors() {
        List<Interceptor> networkInterceptors = new ArrayList<>();
        //Debug 模式禁用Gzip。因为Logging打印不了Gzip的数据。
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            networkInterceptors.add(httpLoggingInterceptor);
            networkInterceptors.add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .removeHeader("Accept-Encoding").build();
                    return chain.proceed(request);
                }
            });
        }
//        networkInterceptors.add(new LoginStatusInterceptor());
        return networkInterceptors;
    }

    @NetworkScope
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(Cache cache, CookieJar cookieJar) {
        return new Builder()
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .cookieJar(cookieJar);
    }

    @NetworkScope
    @Provides
    OkHttpClient provideOkHttpClient(Builder builder, List<Interceptor> interceptors,
                                     @Named("network_interceptors") List<Interceptor> networkInterceptors) {
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        for (Interceptor interceptor : networkInterceptors) {
            builder.addNetworkInterceptor(interceptor);
        }
        return builder.build();
    }

    @NetworkScope
    @Provides
    Gson provideGson() {
        return JsonUtil.GSON;
    }

    @NetworkScope
    @Provides
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    @NetworkScope
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder
                .baseUrl(mBaseUrl)
                .build();
    }
}