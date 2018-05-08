package com.itheima.testnewproject.app;


import com.itheima.testnewproject.common.dagger.component.AppComponent;
import com.itheima.testnewproject.common.dagger.component.DaggerAppComponent;
import com.itheima.testnewproject.common.dagger.component.DaggerModelComponent;
import com.itheima.testnewproject.common.dagger.component.ModelComponent;
import com.itheima.testnewproject.common.dagger.module.AppModule;
import com.itheima.testnewproject.common.dagger.module.NetworkModule;
import com.itheima.testnewproject.common.dagger.module.ServiceModule;
import com.itheima.testnewproject.common.mvp.ItemsContract;
import com.itheima.testnewproject.module.home.component.DaggerHomeIndexComponent;
import com.itheima.testnewproject.module.home.component.HomeIndexComponent;
import com.itheima.testnewproject.module.jinfu.component.DaggerFinanceHomeComponent;
import com.itheima.testnewproject.module.jinfu.component.FinanceHomeComponent;
import com.itheima.testnewproject.network.ServerAddress;

/**
 * Dagger注入器  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2016/12/22 9:56 <br/>
 * Version 1.0
 */
public class Injectors {
    private static AppComponent sAppComponent;
    private static ModelComponent sModelComponent;

    public static void init(AppContext appContext) {

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(appContext)).build();

        sModelComponent = DaggerModelComponent.builder()
                .appComponent(sAppComponent)
                .networkModule(new NetworkModule(ServerAddress.getApiUrl()))
                .serviceModule(new ServiceModule()).build();
    }

    public static AppComponent appComponent() {
        return sAppComponent;
    }

    public static ModelComponent modelComponent() {
        return sModelComponent;
    }

   public static FinanceHomeComponent financeHomeComponent(ItemsContract.View view) {
       return DaggerFinanceHomeComponent.builder()
               .modelComponent(sModelComponent)
               .viewModule(new FinanceHomeComponent.ViewModule(view))
               .build();
   }
    public static HomeIndexComponent homeIndexComponent(ItemsContract.View view) {
        return DaggerHomeIndexComponent.builder()
                .modelComponent(sModelComponent)
                .viewModule(new HomeIndexComponent.ViewModule(view))
                .build();
    }
}
