package com.yjs.ganhuo;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangjingsong on 16/6/27.
 */
public class GanHuoApplication extends Application {
    public static Context mContext;

    public static GanHuoApplication ganHuoApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ganHuoApplication = this;
    }

    public static Context getmContext() {
        return ganHuoApplication;
    }
}
