package com.itheima.testnewproject.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    private static Gson mGson = new Gson();

    private static void setGson(Gson gson) {
        mGson = gson;
    }

    public static Gson getGson() {
        return mGson;
    }

    public static String toJson(Object src) {
        return mGson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }

    public static boolean isJson(String json) {
        return !TextUtils.isEmpty(json) && json.startsWith("{") && json.endsWith("}");
    }

    public static boolean isJsonArray(String json) {
        return !TextUtils.isEmpty(json) && json.startsWith("[") && json.endsWith("]");
    }
}
