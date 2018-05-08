package com.itheima.testnewproject.module.home.service;

import com.itheima.testnewproject.bean.HomeIndex;
import com.itheima.testnewproject.network.result.SingleResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.itheima.testnewproject.network.ServerAddress.GOP_PATH;

/**
 * 创建者     yf
 * 创建时间   2018/5/7 14:03
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public interface IndexService {
    @GET(GOP_PATH + "index")
        //    @GET("http://192.168.1.108:8122/router/gop/index")
    Observable<SingleResult<HomeIndex>> getIndex(
            @QueryMap Map<String, Object> params
    );
}
