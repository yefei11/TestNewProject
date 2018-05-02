package com.itheima.testnewproject.network;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonUtil {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static String toJson(Object src) {
        return GSON.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static boolean isJson(String json) {
        return !TextUtils.isEmpty(json) && json.startsWith("{") && json.endsWith("}");
    }

    public static boolean isJsonArray(String json) {
        return !TextUtils.isEmpty(json) && json.startsWith("[") && json.endsWith("]");
    }
}
