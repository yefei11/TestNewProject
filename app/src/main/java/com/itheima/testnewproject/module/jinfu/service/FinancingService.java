package com.itheima.testnewproject.module.jinfu.service;

import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.bean.NewBorrowPage;
import com.itheima.testnewproject.network.result.MultiResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.itheima.testnewproject.network.ServerAddress.GOP_PATH;

/**
 * P2P  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/02/20 16:48 <br/>
 */
public interface FinancingService {
    @GET(GOP_PATH + "module.borrow.infolist")
    Observable<MultiResult<BorrowPage>> getBorrowListing(
            @QueryMap Map<String, Object> params
    );
    /**
     * 理财首页项目列表
     * */
    @GET(GOP_PATH + "module.borrow.infolist")
    Observable<MultiResult<NewBorrowPage>> getNewBorrowListing(
    );
}
