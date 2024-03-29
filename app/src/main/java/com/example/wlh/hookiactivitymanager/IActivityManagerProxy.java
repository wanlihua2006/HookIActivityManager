package com.example.wlh.hookiactivitymanager;

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IActivityManagerProxy implements InvocationHandler {
    private static Object mActivityManager;
    private final static String PACKAGE_NAME = "com.example.wlh.hookiactivitymanager";
    private final static String CLASS_NAME = PACKAGE_NAME + ".stubActivity";

    public IActivityManagerProxy(Object mActivityManager) {
        this.mActivityManager = mActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if ("startActivity".equals(method.getName())){
            if (BuildConfig.DEBUG) Log.d("IActivityManagerProxy", "wanlihua debug hook startActivity ok");
            Intent intent = null;

            int index = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent){
                    index = i;
                    break;
                }
            }
            intent = (Intent) args[index];
            Intent subIntent = new Intent();
            subIntent.setClassName(PACKAGE_NAME, CLASS_NAME);
            subIntent.putExtra(HookHelper.TARGET_INTENT, intent);
            args[index] = subIntent;

        }
        return method.invoke(mActivityManager, args);

    }
}
