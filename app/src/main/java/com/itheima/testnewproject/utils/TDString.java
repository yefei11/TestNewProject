package com.itheima.testnewproject.utils;


import com.itheima.testnewproject.app.MyApplication;

/**
 * 统一获取资源字符串，避免各类频繁引用StoreApplication
 */
public class TDString {

    public static String getStr(int resId) {
        return MyApplication.getInstance().getResources().getString(resId);
    }

}
