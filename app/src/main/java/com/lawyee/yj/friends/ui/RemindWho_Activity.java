package com.lawyee.yj.friends.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lawyee.yj.friends.R;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/7 16:51:16:51
 * @Purpose :提醒界面
 */


public class RemindWho_Activity extends BaseActivity implements View.OnClickListener {

    private TextView mTitleText;
    private TextView mTitleSend;
    private ImageView mImageButton;
    private RelativeLayout mTitleColor;
    private Toolbar mRemindWho;

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_who_);
        initView();


    }*/

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_remind_who_);
        initView();
        setSupportActionBar(mRemindWho);
        initTitle();
    }

    private void initTitle() {
        mTitleText.setText(getResources().getString(R.string.RaindmeWho));
        mTitleSend.setText(getResources().getString(R.string.complete));
    }
    private void initView() {
        mTitleText = (TextView) findViewById(R.id.title_Text);
        mTitleSend = (TextView) findViewById(R.id.title_send);
        mTitleSend.setOnClickListener(this);
        mImageButton = (ImageView) findViewById(R.id.imageButton);
        mImageButton.setOnClickListener(this);
        mTitleColor = (RelativeLayout) findViewById(R.id.title_color);
        mTitleColor.setOnClickListener(this);
        mRemindWho = (Toolbar) findViewById(R.id.Remind_who);
        mRemindWho.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                    finish();
                break;
            case R.id.title_send:

                break;
        }
    }
}
