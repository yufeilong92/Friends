package com.lawyee.yj.friends.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.lawyee.yj.friends.R;


/**
 * @Author : Yufeilong  on YFPHILPS computer
 * @Email : yufeilong92@163.com
 * @Time :2016/11/29 16:29
 * @Purpose :引导页
 */

public class PloitActivity extends FragmentActivity {
    private Handler handler;

    private void startactivitys() {
        startActivity(new Intent(PloitActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ploit_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);}
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startactivitys();
            }
        }, 2000);

    }

}
