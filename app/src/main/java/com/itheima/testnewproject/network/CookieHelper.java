package com.itheima.testnewproject.network;


import com.itheima.testnewproject.app.MyApplication;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by leo on 2014/12/11.
 */
public class CookieHelper {
    public final static CookieHelper instance = new CookieHelper();
    private Map<String, String> cookieMap = new HashMap<String, String>();
    private String CookieKey = "cookie";

    private CookieHelper() {
        preformCookie();
    }

    public void storeCookie() {
        preformCookie();
    }


    private void preformCookie() {
        cookieMap.clear();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getContext());
        List<Cookie> cookies = myCookieStore.getCookies();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(),cookie.getValue());
        }

    }

    public String getCookieStr() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String key : cookieMap.keySet()) {
            String keyForCompare = key.toLowerCase().trim();
            if ("expires".equals(keyForCompare) || "max-age".equals(keyForCompare) || "httponly".equals(keyForCompare)) {
                continue;
            }
            if (i > 0) {
                sb.append("; ");
            }
            sb.append(key).append("=").append(cookieMap.get(key));
            i++;
        }
        return sb.toString();
    }

}
