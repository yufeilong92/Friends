package com.lawyee.yj.friends.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/1 11:06
 * @Purpose : 自定义GridView
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction())
        { case MotionEvent.ACTION_DOWN ://手指按下时,让父类的ontouch不能滑动
            setParentScrollAble(false);
            break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);//当手指松开时,t让父控件获取ontouch权限
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    //设置父类控件是否获取滑动权限
    private void setParentScrollAble(boolean flag){
        getParent().requestDisallowInterceptTouchEvent(!flag);
    }
}
