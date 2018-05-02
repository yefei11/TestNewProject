package com.itheima.testnewproject.app;

import android.util.Log;

import timber.log.Timber;

/**
 * 监听Log日志  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/3 10:07 <br/>
 * Version 1.0
 */
public class LogTree extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        Monitors.debugLog(getPriorityName(priority),
                message + (t != null ? t.getMessage() : ""));
    }

    private String getPriorityName(int priority) {
        String name = "Unknow";
        switch (priority) {
            case Log.VERBOSE:
                name = "Verbose";
                break;
            case Log.DEBUG:
                name = "Debug";
                break;
            case Log.INFO:
                name = "Info";
                break;
            case Log.WARN:
                name = "Warn";
                break;
            case Log.ERROR:
                name = "Error";
                break;
            case Log.ASSERT:
                name = "Assert";
                break;
        }
        return name;
    }
}