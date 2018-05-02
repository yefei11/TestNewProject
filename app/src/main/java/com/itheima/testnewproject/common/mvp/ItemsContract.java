package com.itheima.testnewproject.common.mvp;


import java.util.List;
import java.util.Map;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/20 11:11 <br/>
 * Version 1.0
 */
public interface ItemsContract {

    interface View<T> extends IView {
        void updateListItems(List<T> items);

        void appendListItems(List<T> items);

        void loadMoreComplete();

        void loadMoreFail();

        void loadMoreEnd();

    }

    interface Presenter extends IPresenter {
        void fetchListItems(Map<String, Object> params);

        void fetchMoreListItems(Map<String, Object> params);
    }

}
