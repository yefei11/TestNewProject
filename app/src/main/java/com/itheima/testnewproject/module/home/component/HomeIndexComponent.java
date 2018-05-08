package com.itheima.testnewproject.module.home.component;

import com.itheima.testnewproject.bean.HomeIndexItem;
import com.itheima.testnewproject.common.dagger.component.ModelComponent;
import com.itheima.testnewproject.common.dagger.scope.ActivityScope;
import com.itheima.testnewproject.common.mvp.ItemsContract;
import com.itheima.testnewproject.module.home.view.HomeFragment;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * 创建者     yf
 * 创建时间   2018/5/7 14:13
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
@ActivityScope
@Component(dependencies = ModelComponent.class,modules = HomeIndexComponent.ViewModule.class)
public interface HomeIndexComponent {

    void inject(HomeFragment homeFragment);

    @Module
    class ViewModule{
        private ItemsContract.View<HomeIndexItem> mView;
        public ViewModule(ItemsContract.View<HomeIndexItem> view){
            mView = view;
        }
        @Provides
        @ActivityScope
        ItemsContract.View<HomeIndexItem> provideHomeIndexView() {
            return (ItemsContract.View<HomeIndexItem>) mView;
        }
    }
}
