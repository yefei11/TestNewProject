package com.itheima.testnewproject.module.jinfu.presenter;


import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.common.dagger.scope.FragmentScope;
import com.itheima.testnewproject.common.mvp.ItemsPresenter;
import com.itheima.testnewproject.module.jinfu.contract.FinanceHomeContract;
import com.itheima.testnewproject.module.jinfu.model.FinancingModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

@FragmentScope
public class FinanceHomePresenter extends ItemsPresenter<BorrowPage, FinanceHomeContract.View> {

    @Inject
    public FinanceHomePresenter(FinanceHomeContract.View view) {
        super(view);
    }

    @Inject
    FinancingModel financeModel;

    @Override
    protected Observable<List<BorrowPage>> getListItemsObservable(Map<String, Object> params) {
        return financeModel.getBorrowListing(params).map(new Func1<List<BorrowPage>, List<BorrowPage>>() {
            @Override
            public List<BorrowPage> call(List<BorrowPage> borrowPages) {
                return null;
            }
        });
    }

    @Override
    protected Observable<List<BorrowPage>> getMoreListItemsObservable(Map<String, Object> params) {
        return financeModel.getBorrowListing(params);
    }



   /* @Override
    protected Observable<List<BorrowPage>> getListItemsObservable(Map<String, Object> params) {
        return financeModel.getBorrowListing(params).map(new Func1<List<BorrowPage>, List<BorrowPage>>() {
            @Override
            public List<BorrowPage> call(List<BorrowPage> borrowPages) {
                return null;
            }
        });
    }

    @Override
    protected Observable<List<BorrowPage>> getMoreListItemsObservable(Map<String, Object> params) {
        return financeModel.getBorrowListing(params);
    }


    public void getJinfuBannerObservable() {
        financeModel.getIndex().map(new Func1<FinancingIndex, List<Banner>>() {
            @Override
            public List<Banner> call(FinancingIndex financingIndex) {
                return financingIndex.getBannerList();
            }
        }).subscribe(SubscriberHelper.create(new Action1<List<Banner>>() {
            @Override
            public void call(List<Banner> banners) {
                getView().onGetBannerDataSuccess(banners);
            }
        }));

    }*/
}
