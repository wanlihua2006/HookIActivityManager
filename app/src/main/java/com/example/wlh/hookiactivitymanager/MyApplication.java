package com.example.wlh.hookiactivitymanager;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            HookHelper.HookAMS();
            HookHelper.hookHandler();//hookHandler
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
