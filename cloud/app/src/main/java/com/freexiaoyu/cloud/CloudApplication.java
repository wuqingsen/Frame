package com.freexiaoyu.cloud;

import android.app.Application;
import android.content.Context;

/**
 * Created by DIY on 2017/4/17. 11:57
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class CloudApplication extends Application {
    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
    public static Context getAppContext() {
        return appContext;
    }
}
