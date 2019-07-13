package com.example.wlh.hookiactivitymanager;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HCallback implements Handler.Callback {
    public static final int LAUNCHER_ACTIVITY = 100;
    Handler mHandler;

    public HCallback(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == LAUNCHER_ACTIVITY){
            Object r = msg.obj;
            try {
                //得到消息中的Intent（启动stubActivity 的Intent）
                Intent intent = (Intent)FieldUtils.getField(r.getClass(),r,"intent");
                if (BuildConfig.DEBUG) Log.d("HCallback", "wanlihua debug is ready change intent : " + intent.getComponent().toString());
                //得到之前保存起来的Intent（TartgetActivity的Intent）
                Intent target = intent.getParcelableExtra(HookHelper.TARGET_INTENT);
                //将启动的StubActivity的Intent替换为启动TargetActivity的Intent
                intent.setComponent(target.getComponent());
                if (BuildConfig.DEBUG) Log.d("HCallback", "wanlihua debug is changed intent : " + intent.getComponent().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHandler.handleMessage(msg);
        return true;
    }
}
