package com.example.wlh.hookiactivitymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_hook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_hook = (Button) this.findViewById(R.id.bt_hook);
        bt_hook.setOnClickListener(this);
/*        bt_hook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TargetActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void onClick(View view) {
        if (BuildConfig.DEBUG) Log.d("MainActivity", "wanlihua debug click callback");
        Intent intent = new Intent(MainActivity.this, TargetActivity.class);
        startActivity(intent);
    }

    public void onClick1(View view) {
        if (BuildConfig.DEBUG) Log.d("MainActivity", "wanlihua debug click callback1");
        Intent intent = new Intent(MainActivity.this, TargetActivity.class);
        startActivity(intent);
    }
}

/* 插件化技术，替换activity
* 2010-01-02 10:58:28.987 12074-12074/com.example.wlh.hookiactivitymanager D/MainActivity: wanlihua debug click callback
* 2010-01-02 10:58:28.994 12074-12074/com.example.wlh.hookiactivitymanager D/IActivityManagerProxy: wanlihua debug hook startActivity ok
* 2010-01-02 10:58:29.045 12074-12074/com.example.wlh.hookiactivitymanager D/HCallback: wanlihua debug is ready change intent : ComponentInfo{com.example.wlh.hookiactivitymanager/com.example.wlh.hookiactivitymanager.stubActivity}
* 2010-01-02 10:58:29.046 12074-12074/com.example.wlh.hookiactivitymanager D/HCallback: wanlihua debug is changed intent : ComponentInfo{com.example.wlh.hookiactivitymanager/com.example.wlh.hookiactivitymanager.TargetActivity}
*/


