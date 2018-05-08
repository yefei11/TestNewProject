package com.itheima.testnewproject.common.dagger.component;


import com.itheima.testnewproject.common.dagger.module.NetworkModule;
import com.itheima.testnewproject.common.dagger.module.ServiceModule;
import com.itheima.testnewproject.common.dagger.scope.NetworkScope;
import com.itheima.testnewproject.module.home.model.IndexModel;
import com.itheima.testnewproject.module.jinfu.model.FinancingModel;

import dagger.Component;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/1/6 16:21 <br/>
 * Version 1.0
 */
@NetworkScope
@Component(dependencies = {AppComponent.class}, modules = {NetworkModule.class, ServiceModule.class})
public interface ModelComponent {

    FinancingModel financingModel();

    IndexModel indexModel();

}
