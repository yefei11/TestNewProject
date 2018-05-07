package com.itheima.testnewproject.module.jinfu.contract;

import com.itheima.testnewproject.bean.ProductDetailBean;

/**
 * 创建者     yf
 * 创建时间   2018/5/4 10:02
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class JinFuProductDetailContract {
    interface View{
        void onGetProductDetailSuccess(ProductDetailBean bean);
    }
    interface Presenter{
        void getProductData(String borrowEid);
    }

}
