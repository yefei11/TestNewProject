package com.itheima.testnewproject.app;


import com.itheima.testnewproject.common.dagger.component.AppComponent;
import com.itheima.testnewproject.common.dagger.component.DaggerAppComponent;
import com.itheima.testnewproject.common.dagger.component.DaggerModelComponent;
import com.itheima.testnewproject.common.dagger.component.ModelComponent;
import com.itheima.testnewproject.common.dagger.module.AppModule;
import com.itheima.testnewproject.common.dagger.module.NetworkModule;
import com.itheima.testnewproject.common.dagger.module.ServiceModule;
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

   /* public static LoginComponent loginComponent(LoginContract.View view) {
        return DaggerLoginComponent.builder()
                .modelComponent(sModelComponent)
                .viewModule(new LoginComponent.ViewModule(view))
                .build();
    }*/

}
