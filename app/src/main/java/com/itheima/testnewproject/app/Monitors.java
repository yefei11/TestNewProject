package com.itheima.testnewproject.app;


import android.app.Application;
import android.text.TextUtils;

import com.blankj.utilcode.utils.FileUtils;
import com.itheima.testnewproject.network.interceptor.UrlHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.HttpUrl;
import timber.log.Timber;

/**
 * 监控器  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/2 10:04 <br/>
 * Version 1.0
 */
public class Monitors {
    private static final int MAX_LENGTH = 2000;
    private static File sLogDir;
    private static File sLogFile;
    private final static StringBuilder sLogText = new StringBuilder();

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private static boolean enable = false;
    private static MonitorActivityLifecycleCallbacks sActivityLifecycleCallbacks;

    static {
        Timber.plant(new LogTree());
        sActivityLifecycleCallbacks = new MonitorActivityLifecycleCallbacks();
    }

    private enum Level {
        Verbose,
        Debug,
        Info,
        Warn,
        Error,
        Assert,
        Click,
        Network,
        Page
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        Monitors.enable = enable;
    }

    public static void start(Application application) {
        sLogDir = new File(application.getExternalCacheDir(), "logs");
        String filename = DATE_FORMAT.format(new Date()).split(" ")[0] + ".txt";
        sLogFile = new File(sLogDir, filename);
        application.registerActivityLifecycleCallbacks(sActivityLifecycleCallbacks);
        Monitors.enable = true;
    }

    public static void stop(Application application) {
        application.unregisterActivityLifecycleCallbacks(sActivityLifecycleCallbacks);
        sLogText.setLength(0);
        Monitors.enable = false;
    }

    /** 记录Log日志 */
    public static void debugLog(String level, String content) {
        saveLogInfo(Level.valueOf(level), content);
    }

    /** 记录页面跳转 */

    public static void page2page(String leftActivity, String rightActivity, boolean goBack) {
        String msg = leftActivity + (goBack ? " <- " : " -> ") + rightActivity;
        saveLogInfo(Level.Page, msg);
    }

    /** 记录View点击 */
    public static void clickView(String whichClass, String whichTitle, String viewIdName, String viewText) {
        String detail = String.format("%s(%s) 点击 %s(%s)", whichClass, whichTitle, viewIdName, viewText);
        saveLogInfo(Level.Click, detail);
    }


    private synchronized static void saveLogInfo(Level level, String detail) {
        if (!enable) return;

        sLogText.append("操作日志[").append(DATE_FORMAT.format(new Date())).append("]\n")
                .append("操作类型：<").append(level).append(">\n")
                .append("详细内容：\n").append(detail).append("\n\n\n");

        if (sLogText.length() > MAX_LENGTH && sLogFile != null) {
            FileUtils.writeFileFromString(sLogFile, sLogText.toString(), true);
            sLogText.setLength(0);
        }
    }
    /** 记录http请求信息 */
    public static void httpRequest(String url, String method, String requestBodyText, String responseText) {

        //去掉签名信息，防止Url被直接访问
        HttpUrl httpUrl = HttpUrl.parse(url);
        String logUrl = url;
        if (httpUrl != null)
            logUrl = httpUrl.newBuilder().removeAllQueryParameters(UrlHelper.P_SIGN).toString();

        StringBuilder detail = new StringBuilder();
        detail.append(String.format("请求%s>> URL：%s", method, logUrl)).append("\n")
                .append("请求Body：\n").append(requestBodyText).append("\n");
        if (!TextUtils.isEmpty(responseText)) {
            detail.append("返回数据：\n").append(responseText).append("\n");
        }

        saveLogInfo(Level.Network, detail.toString());
    }
    public static List<File> getLogFiles() {
        return Arrays.asList(sLogDir.listFiles());
    }
}
