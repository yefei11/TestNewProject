package com.itheima.testnewproject.common.loading;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public interface Loading {
    void onStart();

    void onFinish();

    void onError(Throwable e);
}
