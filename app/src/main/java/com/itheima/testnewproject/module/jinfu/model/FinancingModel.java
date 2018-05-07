package com.itheima.testnewproject.module.jinfu.model;


import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.bean.NewBorrowPage;
import com.itheima.testnewproject.common.model.BaseServiceModel;
import com.itheima.testnewproject.module.jinfu.service.FinancingService;
import com.itheima.testnewproject.network.result.MultiResult;
import com.itheima.testnewproject.rx.TransHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * P2P  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/02/22 10:50 <br/>
 * Version 1.0
 */
public class FinancingModel extends BaseServiceModel<FinancingService> {

    @Inject
    public FinancingModel(FinancingService service) {
        super(service);
    }
    public Observable<List<BorrowPage>> getBorrowListing(Map<String, Object> params) {
        return getService().getBorrowListing(params)
                .compose(TransHelper.<MultiResult<BorrowPage>>uiScheduler())
                .compose(this.<List<BorrowPage>, MultiResult<BorrowPage>>transformResultToData());
    }
    /**
     * 理财首页列表
     */
    public Observable<List<NewBorrowPage>> getNewBorrowListing() {
        return getService().getNewBorrowListing()
                .compose(TransHelper.<MultiResult<NewBorrowPage>>uiScheduler())
                .compose(this.<List<NewBorrowPage>, MultiResult<NewBorrowPage>>transformResultToData());
    }
}
