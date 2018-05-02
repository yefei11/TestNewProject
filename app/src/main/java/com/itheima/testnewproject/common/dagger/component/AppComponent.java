package com.itheima.testnewproject.common.dagger.component;

import android.app.Application;
import android.content.Context;

import com.itheima.testnewproject.app.AppContext;
import com.itheima.testnewproject.app.UserManager;
import com.itheima.testnewproject.bean.User;
import com.itheima.testnewproject.common.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/6 17:36 <br/>
 * Version 1.0
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context context();

    Application application();

    AppContext appContext();

    UserManager userManager();

    User user();

    void inject(AppContext appContext);


}
