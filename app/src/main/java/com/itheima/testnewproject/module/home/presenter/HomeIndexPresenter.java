package com.itheima.testnewproject.module.home.presenter;

import com.itheima.testnewproject.bean.HomeIndex;
import com.itheima.testnewproject.bean.HomeIndexItem;
import com.itheima.testnewproject.common.mvp.ItemsContract;
import com.itheima.testnewproject.common.mvp.ItemsPresenter;
import com.itheima.testnewproject.module.home.model.IndexModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;


/**
 * 创建者     yf
 * 创建时间   2018/5/7 13:55
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class HomeIndexPresenter extends ItemsPresenter<HomeIndexItem,ItemsContract.View<HomeIndexItem>> {

    @Inject
    IndexModel mIndexModel;

    @Inject
    public HomeIndexPresenter(ItemsContract.View<HomeIndexItem> view) {
        super(view);
    }

    @Override
    protected Observable<List<HomeIndexItem>> getListItemsObservable(Map<String, Object> params) {
        return mIndexModel.getIndex(params).map(new Func1<HomeIndex, List<HomeIndexItem>>() {
            @Override
            public List<HomeIndexItem> call(HomeIndex homeIndex) {
                List<HomeIndexItem> items = new ArrayList<>(8);
                //头部广告
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_AD,homeIndex.getBigBannerList()));
                //动能集合
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_FUNC, null));
                 /*  //精选服务
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_SERVICES, homeIndex.getFeatureList()));
                //产学研
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_PRODUCTION, homeIndex.getTradeList()));
                //投融专区案例
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_INVESTMENT, homeIndex.getInvestmentList()));
                //精选理财
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_FINANCE, homeIndex.getFinanceList()));
                //创业管理
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_ENTREPRENEURSHIP, homeIndex.getEntrepreneurList()));
                //创选优品
                items.add(new HomeIndexItem(HomeIndexItem.ITEM_GOODS, homeIndex.getMallList()));*/
                return items;
            }
        });
    }

    @Override
    protected Observable<List<HomeIndexItem>> getMoreListItemsObservable(Map<String, Object> params) {
        return null;
    }
}
