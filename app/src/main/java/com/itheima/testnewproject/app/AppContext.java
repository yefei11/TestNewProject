package com.itheima.testnewproject.app;

import com.blankj.utilcode.utils.Utils;
import com.itheima.testnewproject.BuildConfig;
import com.itheima.testnewproject.R;
import com.orhanobut.logger.Logger;

import java.net.CookieHandler;
import java.net.CookieManager;

import timber.log.Timber;


public class AppContext extends MyApplication {
    private static AppContext instance;


    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
//        Settings.init(this);
        Utils.init(this);
        Injectors.init(this);
        Injectors.appComponent().inject(this);
        Timber.plant(BuildConfig.DEBUG ? new DebugTree() : new ReleaseTree());
        Monitors.start(this);

        //发起网络请求的时候带上服务端返回的cookies
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Monitors.stop(this);
    }

    private class DebugTree extends Timber.DebugTree {
        DebugTree() {
            Logger.init(getString(R.string.app_name))
                    .methodCount(1)
                    .methodOffset(6);
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            Logger.log(priority, tag, message, t);
        }
    }

    private class ReleaseTree extends DebugTree {
        @Override
        protected boolean isLoggable(String tag, int priority) {
            return false;
        }
    }
}
