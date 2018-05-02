package com.itheima.testnewproject.network.interceptor;

import android.text.TextUtils;

import com.itheima.testnewproject.common.Property;
import com.itheima.testnewproject.network.ServerAddress;
import com.itheima.testnewproject.utils.MessageDigestUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.HttpUrl;

/**
 * Url帮助类  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/20 11:59 <br/>
 * Version 1.0
 */
public class UrlHelper {

    public static final String P_SIGN = "sign";

    /***
     * 修复Url：1.增加公共参数，2.调整新Url path部分，3.签名参数
     * @param url 原始url
     * @return 修复后的Url
     */
    public static String fixUrl(String url) {

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) return url;

        //获取url中的method，path中的最后一个
        String method = "";
        if (httpUrl.pathSegments() != null && httpUrl.pathSize() > 0) {
            method = httpUrl.pathSegments().get(httpUrl.pathSize() - 1);
        }

        //增加必要参数
        HttpUrl.Builder httpBuilder = httpUrl.newBuilder()
                .removeAllQueryParameters("appKey")
                .removeAllQueryParameters("method")
                .removeAllQueryParameters("format")
                .removeAllQueryParameters("loginType")
                .addQueryParameter("appKey", "100001")
                .addQueryParameter("method", method)
                .addQueryParameter("format", "json")
                .addQueryParameter("loginType", "4");
        if (TextUtils.isEmpty(httpUrl.queryParameter("v"))){
            httpBuilder.removeAllQueryParameters("v")
                    .addQueryParameter("v", "1.0");
        }

        //增加必要参数
        HttpUrl newUrl = httpBuilder.build();

        //如果包含 GOP_PATH，需要去掉最后一个path（method）
        if (httpUrl.encodedPath().contains(ServerAddress.GOP_PATH)) {
            newUrl = newUrl.newBuilder()
                    .removePathSegment(httpUrl.pathSize() - 1)
                    .build();
        }

        //对参数签名
        Map<String, String> queries = new LinkedHashMap<>(newUrl.querySize());
        for (String name : newUrl.queryParameterNames()) {
            queries.put(name, newUrl.queryParameter(name));
        }
        String apiSign = MessageDigestUtil.sign(queries, null, Property.APPSECRET);
        newUrl = newUrl.newBuilder()
                .removeAllQueryParameters(P_SIGN)
                .addQueryParameter(P_SIGN, apiSign)
                .build();

        return newUrl.toString();
    }
}
