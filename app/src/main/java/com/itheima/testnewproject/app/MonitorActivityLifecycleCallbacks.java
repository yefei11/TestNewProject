package com.itheima.testnewproject.app;

import android.app.Activity;

import java.util.LinkedList;

/**
 * 监控Activity生命周期  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/2 15:29 <br/>
 * Version 1.0
 */
public class MonitorActivityLifecycleCallbacks extends SimpleActivityLifecycleCallbacks {

    /** 记录已显示的Activity */
    private LinkedList<String> mShowedActivity = new LinkedList<>();

    @Override
    public void onActivityResumed(Activity activity) {
        /*
          如果 mShowedActivity 包含 currentActivity
          表示goBack，否则表示startActivity
        */

        String currentActivity = String.format("%s(%s)",
                activity.getComponentName().getClassName(),
                activity.getTitle().toString());

        String leftActivity;
        String rightActivity;

        boolean goBack = mShowedActivity.contains(currentActivity);

        if (goBack) {
            leftActivity = currentActivity;
            rightActivity = mShowedActivity.poll();
        } else {
            leftActivity = mShowedActivity.peek();
            rightActivity = currentActivity;
            mShowedActivity.push(currentActivity);
        }

        Monitors.page2page(leftActivity, rightActivity, goBack);
    }
}
