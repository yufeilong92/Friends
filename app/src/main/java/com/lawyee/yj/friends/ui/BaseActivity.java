package com.lawyee.yj.friends.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by yfl on 2016/11/28 17:00.
 *
 * @email: yufeilong92@163.com
 * @purpose: Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();
    public ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 初始化fresco
         */
        Fresco.initialize(this);
        initContentView();
        bar = getSupportActionBar();
        bar.setDisplayShowTitleEnabled(false);
    }

    protected abstract void initContentView();

    public void OnHomeBack(View view) {
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void OnBack(View view){
        finish();
    }

}
