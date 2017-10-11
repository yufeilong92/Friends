package com.lawyee.yj.friends.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @Author : Yufeilong  on YFPHILPS computer
 * @Email : yufeilong92@163.com
 * @Time :2016/11/30 09:39
 * @Purpose :自定listview解决嵌套问题
 */

public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

/*

    *//*

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int  expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
                MeasureSpec.AT_MOST);
        Log.e("MyListView", "自定义expandSpec: =="+expandSpec );
        super.onMeasure(widthMeasureSpec, expandSpec);
    }*/

    //处理listview中嵌套的lsitview滑动

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
