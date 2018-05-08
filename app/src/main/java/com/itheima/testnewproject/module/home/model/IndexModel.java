package com.itheima.testnewproject.module.home.model;

import com.itheima.testnewproject.bean.HomeIndex;
import com.itheima.testnewproject.common.model.BaseServiceModel;
import com.itheima.testnewproject.module.home.service.IndexService;
import com.itheima.testnewproject.network.result.SingleResult;
import com.itheima.testnewproject.rx.TransHelper;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;


/**
 * 创建者     yf
 * 创建时间   2018/5/7 14:03
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class IndexModel extends BaseServiceModel<IndexService> {
    @Inject
    public IndexModel(IndexService indexService) {
        super(indexService);
    }

    /**
     * 首页
     */
    public Observable<HomeIndex> getIndex(Map<String,Object> params){
        return getService().getIndex(params).compose(TransHelper.<SingleResult<HomeIndex>>uiScheduler()).compose(this.<HomeIndex, SingleResult<HomeIndex>>transformResultToData());
    }
}
