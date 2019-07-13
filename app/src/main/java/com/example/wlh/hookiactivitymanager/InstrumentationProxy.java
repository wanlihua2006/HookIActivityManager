package com.example.wlh.hookiactivitymanager;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

public class InstrumentationProxy extends Instrumentation {
    private Instrumentation mInstrumentation;
    private PackageManager mPackageManager;
    private final static String PACKAGE_NAME = "com.example.wlh.hookiactivitymanager";
    private final static String CLASS_NAME = PACKAGE_NAME + ".stubActivity";

    public InstrumentationProxy(Instrumentation mInstrumentation, PackageManager mPackageManager) {
        this.mInstrumentation = mInstrumentation;
        this.mPackageManager = mPackageManager;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {
        //Toast.makeText(MainActivity.this,"hook ativtiy sucess -- who " + who);
        Log.d("InStrumentationProxy","wanlihua debug hook ativtiy sucess -- who " + who);
        List<ResolveInfo> infos = mPackageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (infos == null || infos.size() == 0){
            intent.putExtra(HookHelper.TARGET_INTENT, intent.getComponent().getClassName());
            intent.setClassName(who,CLASS_NAME);
        }

        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",Context.class,IBinder.class,
                    IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return (ActivityResult)execStartActivity.invoke(mInstrumentation,who,contextThread,token,target,intent,requestCode,options);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //return null;
    }

    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String intentname = intent.getStringExtra(HookHelper.TARGET_INTENT);
        if (!TextUtils.isEmpty(intentname)){
            return super.newActivity(cl,intentname,intent);
            //return (Activity)cl.loadClass(className).newInstance();
        }
        return super.newActivity(cl,className,intent);
    }
}
