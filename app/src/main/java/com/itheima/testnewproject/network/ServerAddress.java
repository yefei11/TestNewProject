package com.itheima.testnewproject.network;

import com.itheima.testnewproject.BuildConfig;

import okhttp3.HttpUrl;

/**
 * 统一管理服务器地址  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/2/28 13:52 <br/>
 * Version 1.0
 */
public class ServerAddress {

    /* 孔磊*/
//        private static final String DEBUG_URL = "http://192.168.1.96:8122/router/";
    /* 肖华敏*/
        private static final String DEBUG_URL = "http://192.168.1.105:8122/router/";
    /* 唐文驰*/
//        private static final String DEBUG_URL = "http://192.168.1.112:8122/router/";
    /* 谭磊*/
//    private static final String DEBUG_URL = "http://192.168.1.105:8122/router/";
    //    private static final String DEBUG_URL = "http://192.168.1.105:8122/router/";
    /* 蔡文盛 */
//        private static final String DEBUG_URL = "http://192.168.1.102:8122/router/";
    /* 黄中明 */
    //    private static final String DEBUG_URL = "http://192.168.1.153:8122/mobile/router/";
    /* 谢有才 */
    //    private static final String DEBUG_URL = "http://192.168.1.152:8122/router/";
    /*熊慧峰*/
    //    private static final String DEBUG_URL = "http://192.168.1.103:8122/router/";
    /*蔡伟华*/
//    private static final String DEBUG_URL = "http://192.168.1.104:8122/router/";
    //    private static final String DEBUG_URL = "http://192.168.1.152:8122/router/";
    //杨贞平
//    private static final String DEBUG_URL = "http://192.168.1.158:8122/router/";
    //陈友仕
//    private static final String DEBUG_URL = "http://192.168.1.61:8122/router/";

//    private static final String TEST_URL = "http://test6.tronker.com/newmobile/router/";
    private static final String TEST_URL = "http://test.tronker.com/newmobile/router/";
    private static final String PRE_RELEASE_URL = "http://uat.tronker.com/newmobile/router/";
//        private static final String TEST_URL = "http://test5.tronker.com/newmobile/router/";
    //    private static final String PRE_RELEASE_URL = "http://ubt.tronker.com/newmobile/router/";
    private static final String RELEASE_URL = "https://www.tronker.com/zephapp205/router/";

    public static final String GOP_PATH = "gop/";

    private static HttpUrl API_URL;

    static {
        String apiUrl = RELEASE_URL;
        switch (BuildConfig.BUILD_TYPE) {
            case "debug":
                apiUrl = DEBUG_URL;
                break;
            case "aTest":
                apiUrl = TEST_URL;
                break;
            case "pre_release":
                apiUrl = PRE_RELEASE_URL;
                break;
            case "release":
                apiUrl = RELEASE_URL;
                break;
        }
        updateApiUrl(apiUrl);
    }

    private static void updateApiUrl(String apiUrl) {
        API_URL = HttpUrl.parse(apiUrl);
    }

    public static String getApiUrl() {
        return API_URL.toString();
    }

    public static String getApiHost() {
        return API_URL.host();
    }
}
