package com.lawyee.yj.friends.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawyee.yj.friends.vo.TitleDataVo;
import com.lawyee.yj.friends.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/9 16:17
 * @Purpose :
 */

public class MyExpandablelistviewAdapter extends BaseExpandableListAdapter {

    private final boolean setColor;
    private Map<String, ArrayList<String>> mData;
    private ArrayList<String> mlist;
    private Context mContext;
    private final LayoutInflater inflater;

    private ArrayList<TitleDataVo> mContent;
    private int selectGroupIndex;
    private int selectChildIndex;

    /**
     * 设置父类的选择id
     *
     * @param selectindex 父类选择id
     */

    public void selectIndex(int selectindex) {
        this.selectGroupIndex = selectindex;
        notifyDataSetChanged();
    }

    /**
     * @param i 子类id
     */
    public void selectChildIndex(int i) {
        this.selectChildIndex = i;
        notifyDataSetChanged();
    }

    /**
     * @param context  上下文
     * @param mData    子类集合
     * @param list     父类集合
     * @param mContent 标题数据集合
     * @param setColor 设置颜色
     */
    public MyExpandablelistviewAdapter(Context context, Map<String, ArrayList<String>> mData, ArrayList<String> list, ArrayList<TitleDataVo> mContent, boolean setColor) {
        this.mContext = context;
        this.mData = mData;
        this.mlist = list;
        this.mContent = mContent;
        inflater = LayoutInflater.from(context);
        this.setColor = setColor;
    }

    //获取item总数
    @Override
    public int getGroupCount() {
        return mlist.size();
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mlist.get(groupPosition);
        int size = mData.get(key).size();
        return size;
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return mlist.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = mlist.get(groupPosition);
        return (mData.get(key).get(childPosition));
    }

    //获取当前itemid
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean b, View converview, ViewGroup viewGroup) {
        if (converview == null) {
            converview = inflater.inflate(R.layout.list_item_parents, viewGroup, false);
        }
        TextView tv = (TextView) converview.findViewById(R.id.parent_tv);
        TextView con = (TextView) converview.findViewById(R.id.parent_con);
        ImageView iv = (ImageView) converview.findViewById(R.id.parent_iv);

        con.setText(mContent.get(groupPosition).getContent());
        con.setTextColor(setColor == true ?Color.parseColor("#3F51B5"):Color.parseColor("#ffffff"));
        tv.setText(mlist.get(groupPosition));
        tv.setTextColor(setColor == true ?Color.parseColor("#3F51B5"):Color.parseColor("#ffffff"));

        if (selectGroupIndex == groupPosition) {
            iv.setVisibility(View.VISIBLE);
            if (selectGroupIndex == 3) {
                iv.setImageResource(R.mipmap.tick_one);
            } else {
                iv.setImageResource(R.mipmap.tick);
            }
        } else {

            iv.setVisibility(View.INVISIBLE);
            iv.setImageResource(R.mipmap.tick);
        }

        return converview;
    }

    public static final String TAG = "查询结果";

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View converview, ViewGroup viewGroup) {
        String key = mlist.get(groupPosition);
        String childrenContent = mData.get(key).get(childPosition);
        if (converview == null) {
            converview = inflater.inflate(R.layout.list_item_children, viewGroup, false);
        }
        TextView tv = (TextView) converview.findViewById(R.id.children_tv);
        ImageView iv = (ImageView) converview.findViewById(R.id.children_iv);
        tv.setText(childrenContent);
        tv.setTextColor(setColor == true ?Color.parseColor("#3F51B5"):Color.parseColor("#ffffff"));
        Log.e(TAG, "=====" + selectChildIndex + childPosition);
        if (selectChildIndex == childPosition) {
            iv.setImageResource(R.mipmap.radio_button_on);
        } else {
            iv.setImageResource(R.mipmap.radio_button_off);
        }
        return converview;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
