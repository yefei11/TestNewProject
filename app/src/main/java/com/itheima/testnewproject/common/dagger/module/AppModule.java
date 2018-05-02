package com.itheima.testnewproject.common.dagger.module;

import android.app.Application;
import android.content.Context;

import com.itheima.testnewproject.app.AppContext;
import com.itheima.testnewproject.bean.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * 用户管理  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/6 17:36 <br/>
 * Version 1.0
 */

@Module
public class AppModule {

    private AppContext appContext;

    public AppModule(AppContext appContext) {
        this.appContext = appContext;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return appContext;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return appContext;
    }

    @Singleton
    @Provides
    AppContext provideAppContext() {
        return appContext;
    }

    @Singleton
    @Provides
    User provideUser(com.itheima.testnewproject.app.UserManager userManager) {
        return userManager.getUser();
    }
}
