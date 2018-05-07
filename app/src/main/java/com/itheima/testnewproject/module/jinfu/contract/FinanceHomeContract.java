package com.itheima.testnewproject.module.jinfu.contract;


import com.itheima.testnewproject.bean.Banner;
import com.itheima.testnewproject.bean.BorrowPage;
import com.itheima.testnewproject.common.mvp.ItemsContract;

import java.util.List;

/**
 * Created by admin on 2017/2/28.
 */

public interface FinanceHomeContract extends ItemsContract {
    interface View extends ItemsContract.View<BorrowPage>{
        void onGetBannerDataSuccess(List<Banner> banners);
    }
}
