package com.itheima.testnewproject.network.result;

import android.support.annotation.Keep;

/**
 * 单个结果  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2015/8/21 15:03 <br/>
 * Modified : zhongw <br/>
 * ModifiedDate : 2015/8/21 15:03 <br/>
 * Email : 730123427@qq.com <br/>
 * Version 1.0
 */
@Keep
public class SingleResult<T> extends Result<T> {

    private String code;
    private String message;
    private T data;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }
}