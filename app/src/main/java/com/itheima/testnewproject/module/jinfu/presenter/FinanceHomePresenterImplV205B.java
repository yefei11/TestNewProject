package com.itheima.testnewproject.module.jinfu.presenter;


import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.bean.NewBorrowPage;
import com.itheima.testnewproject.module.jinfu.contract.FinanceHomeContract;
import com.itheima.testnewproject.module.jinfu.model.FinancingModel;
import com.itheima.testnewproject.rx.SubscriberHelper;
import com.itheima.testnewproject.rx.TransHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by soffice on 2017/4/19.
 */
public class FinanceHomePresenterImplV205B extends FinanceHomePresenter {
    @Inject
    FinancingModel financeModel;

    @Inject
    public FinanceHomePresenterImplV205B(FinanceHomeContract.View view) {
        super(view);
    }


    @Override
    protected Observable<List<BorrowPage>> getListItemsObservable(Map<String, Object> params) {
        return financeModel.getNewBorrowListing().map(new Func1<List<NewBorrowPage>, List<BorrowPage>>() {
            @Override
            public List<BorrowPage> call(List<NewBorrowPage> newBorrowPages) {
                List<BorrowPage> projectList = new ArrayList<>();
                List<BorrowPage> projectTitleList = new ArrayList<>();
                for (int i = 0; i < newBorrowPages.size(); i++) {
                    BorrowPage b = new BorrowPage(
                            true,
                            newBorrowPages.get(i).getModuleName(),
                            newBorrowPages.get(i).getIsMore(),
                            newBorrowPages.get(i).getModuleId(),
                            newBorrowPages.get(i).getAttachPath()
                    );
                    projectTitleList.add(b);
                }
                for (int i = 0; i < newBorrowPages.size(); i++) {
                    projectList.add(projectTitleList.get(i));
                    projectList.addAll(newBorrowPages.get(i).getModuleList());
                }
                return projectList;
            }
        });
    }

    @Override
    public void fetchListItems(Map<String, Object> params) {
        final List<Object> list = new ArrayList<>();
        Observable<List<BorrowPage>> listItems = getListItemsObservable(params);
        listItems.compose(TransHelper.<List<BorrowPage>>loading2(getView().getLoading()))
                .subscribe(SubscriberHelper.create(new Action1<List<BorrowPage>>() {
                    @Override
                    public void call(List<BorrowPage> ts) {
                        getView().updateListItems(ts);
                    }
                }));
    }

}
