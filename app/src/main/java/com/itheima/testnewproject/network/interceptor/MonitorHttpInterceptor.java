package com.itheima.testnewproject.network.interceptor;


import com.itheima.testnewproject.app.Monitors;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 监听Http请求信息（Okhttp）  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/2/21 17:15 <br/>
 * Version 1.0
 */
public class MonitorHttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        Request request = response.request();
        String url = request.url().toString();
        String method = request.method();
        String requestBodyContent = "";
        String responseContent = "";

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            requestBodyContent = buffer.readString(charset);
        }
        //只记录错误信息
        if (!response.isSuccessful()) {
            responseContent = response.message();
        }
//        ResponseBody responseBody = response.body();
//        if (responseBody != null) {
//            responseContent = responseBody.string();
//            ResponseBody newResponseBody =
//                    ResponseBody.create(responseBody.contentType(), responseContent);
//            response = response.newBuilder().body(newResponseBody).build();
//        }
        Monitors.httpRequest(url, method, requestBodyContent, responseContent);
        return response;
    }
}
