package com.itheima.testnewproject.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyApplication extends MultiDexApplication {


    private static Context mContext;

    public static boolean isRunning = false;

    public static int count = 0;

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return mContext;
    }

    private String resUrl = "";// 图片头地址

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }


    private static MyApplication mInstance = null;

    private Map<String, Activity> activityMap = new HashMap<String, Activity>();

    public static MyApplication getInstance() {
        return mInstance;
    }


    public void addActivity(String name, Activity activity) {
        activityMap.put(name, activity);
    }

    public void removeActivity(String name) {
        Activity activity = activityMap.remove(name);
        if (activity != null) {
            activity.finish();
            activity = null;
        }

    }

    public void clearActivity() {


        Iterator it = activityMap.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            Activity activity = (Activity) entry.getValue();
            if (activity != null) {
                activity.finish();
            }
        }
    }


    /**
     * ---- ---------------------------------------------------------add---------------------------------------
     **/

    private static Handler mHandler;
    private static long mMainThreadId;
    //缓存协议
    private Map<String, String> mProtocolCacheMap = new HashMap<>();

    public Map<String, String> getProtocolCacheMap() {
        return mProtocolCacheMap;
    }

    /**
     * 得到主线程的handler
     */
    public static Handler getHandler() {
        return mHandler;
    }

    /**
     * 得到主线程的线程id
     */
    public static long getMainThreadId() {
        return mMainThreadId;
    }

    @Override
    public void onCreate() {

        	/*--------------- 创建应用里面需要用到的一些共有的属性 ---------------*/
        // 1.上下文
        mContext = getApplicationContext();

        // 2.主线程handler
        mHandler = new Handler();

        // 3.主线程的id
        mMainThreadId = android.os.Process.myTid();
        /**
         * Tid: thread
         * Pid: process
         * Uid: user
         */
        /**---- ---------------------------------------------------------add---------------------------------------**/
        super.onCreate();

        //Android 7.0升级
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mInstance = this;
//        LeakCanary.install(this);
        }


    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }


    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

}
