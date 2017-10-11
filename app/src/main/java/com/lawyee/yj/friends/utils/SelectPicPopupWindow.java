package com.lawyee.yj.friends.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.lawyee.yj.friends.R;


/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/11/30 10:56
 * @Purpose : 显示点赞框
 */

public class SelectPicPopupWindow extends PopupWindow {

    private final View mMenuView;
    RelativeLayout mfabulouslayou;
    RelativeLayout mCommetn;

    public SelectPicPopupWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.list_item_alert_dialog, null);
        mfabulouslayou = (RelativeLayout) mMenuView.findViewById(R.id.fabulous);
        mCommetn = (RelativeLayout) mMenuView.findViewById(R.id.comment);
        //添加显示的视图
        this.setContentView(mMenuView);
        //设置显示的宽度
        this.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        this.setBackgroundDrawable(colorDrawable);

        mCommetn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mMenuView.findViewById(R.id.linea).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }



}
