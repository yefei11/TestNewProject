package com.itheima.testnewproject.module.jinfu.component;


import com.itheima.testnewproject.common.dagger.component.ModelComponent;
import com.itheima.testnewproject.common.dagger.scope.FragmentScope;
import com.itheima.testnewproject.common.mvp.ItemsContract;
import com.itheima.testnewproject.module.jinfu.view.JinfuFragment;
import com.itheima.testnewproject.module.jinfu.contract.FinanceHomeContract;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/3/1.
 */
@FragmentScope
@Component(dependencies = ModelComponent.class, modules = FinanceHomeComponent.ViewModule.class)
public interface FinanceHomeComponent {

    void inject(JinfuFragment jinfuFragment);

    @Module
    class ViewModule {
        private ItemsContract.View mView;

        public ViewModule(ItemsContract.View view) {
            mView = view;
        }

        @Provides
        @FragmentScope
        FinanceHomeContract.View providerJinfuHomeContractView() {
            return (FinanceHomeContract.View) mView;
        }
    }
}
