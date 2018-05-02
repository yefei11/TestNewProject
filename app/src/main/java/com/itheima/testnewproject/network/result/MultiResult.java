package com.itheima.testnewproject.network.result;

import android.support.annotation.Keep;

import java.util.List;

/**
 * 多个结果，List  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2015/8/21 15:03 <br/>
 * Modified : zhongw <br/>
 * ModifiedDate : 2015/8/21 15:03 <br/>
 * Email : 730123427@qq.com <br/>
 * Version 1.0
 */
@Keep
public class MultiResult<T> extends Result<List<T>> {

    private String code;
    private String message;
    private List<T> data;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<T> getData() {
        return data;
    }

}
