package com.itheima.testnewproject.network.result;

import android.support.annotation.Keep;

import com.itheima.testnewproject.network.JsonUtil;


/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2015/8/21 14:51 <br/>
 * Modified : zhongw <br/>
 * ModifiedDate : 2015/8/21 14:51 <br/>
 * Email : 730123427@qq.com <br/>
 * Version 1.0
 */
@Keep
public abstract class Result<T> {
    public static final String SUCCESS = "999";

    public abstract String getCode();

    public abstract String getMessage();

    public abstract T getData();

    public boolean isSuccess() {
        return SUCCESS.equals(getCode());
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
