package com.itheima.testnewproject.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.app.Injectors;
import com.itheima.testnewproject.bean.HomeIndexItem;
import com.itheima.testnewproject.common.fragment.BasePresenterListFragment;
import com.itheima.testnewproject.module.home.item.AdViewHolder;
import com.itheima.testnewproject.module.home.item.FuncViewHolder;
import com.itheima.testnewproject.module.home.item.IndexViewHolder;
import com.itheima.testnewproject.module.home.presenter.HomeIndexPresenter;
import com.youth.banner.Banner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.itheima.testnewproject.bean.HomeIndexItem.ITEM_AD;
import static com.itheima.testnewproject.bean.HomeIndexItem.ITEM_FUNC;

/**
 * 创建者     yf
 * 创建时间   2018/4/28 18:37
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class HomeFragment extends BasePresenterListFragment<HomeIndexItem,HomeIndexPresenter> {
    private Banner vBannerView;
    @Override
    protected void initInjector() {
        super.initInjector();
        Injectors.homeIndexComponent(this).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAdapter().setOnLoadMoreListener(null);
        getAdapter().addFooterView(View.inflate(getActivity(),R.layout.fragment_home_index_bottom,null));
    }

    @Override
    public View getFooterView() {
        return super.getFooterView();
    }

    @Override
    protected BaseQuickAdapter<HomeIndexItem, ? extends BaseViewHolder> createAdapter() {
        return new MultiItemAdapter(null);
    }

    private  class MultiItemAdapter extends BaseMultiItemQuickAdapter<HomeIndexItem,IndexViewHolder>{

        public MultiItemAdapter(List<HomeIndexItem> data) {
            super(data);
//            addItemType(ITEM_AD,R.layout.layout_banner);
//            addItemType(ITEM_FUNC,R.layout.item_index_func);
//            addItemType(ITEM_SERVICES,R.layout.item_index_services);
//            addItemType(ITEM_PRODUCTION,R.layout.item_index_production);
        }

        @Override
        protected IndexViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
            IndexViewHolder holder=null;
            switch (viewType) {
                case ITEM_AD:
                    AdViewHolder adViewHolder = new AdViewHolder(parent);
                    holder = adViewHolder;
                    vBannerView = adViewHolder.getBannerView();
                    break;
                case ITEM_FUNC:
                    holder = new FuncViewHolder(parent);
                    break;

                default:
                    break;
            }
            return holder;
        }

        @Override
        protected void convert(IndexViewHolder helper, HomeIndexItem item) {
            helper.updateView(item.getData());
            /*switch (item.getItemType()){
                case ITEM_AD:
                    ((AdViewHolder)helper).bindData((List<HomeIndex.InnerData>) item.getData());
                    break;

            }*/
        }
    }

    @NonNull
    @Override
    protected Map<String, Object> getFetchListItemsParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("v", "2.0");
        return params;
    }
}
