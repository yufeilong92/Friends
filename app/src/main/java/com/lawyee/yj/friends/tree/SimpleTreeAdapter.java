package com.lawyee.yj.friends.tree;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.vo.TitleData;
import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/9 11:14
 * @Purpose :
 */

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {

    private ArrayList<TitleData> mData;

    public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel, ArrayList<TitleData> mData) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
        this.mData = mData;

    }

    @Override
    public View getConvertView(Node node, int i, View converview, ViewGroup viewGroup) {
        ViewHolder holder;

        if (converview == null) {
            converview = mInflater.inflate(R.layout.list_item_parent, viewGroup, false);
            holder = new ViewHolder(converview);
            converview.setTag(holder);
        } else {
            holder = (ViewHolder) converview.getTag();
        }
        if (node.getIcon() == -1) {
            holder.mGroundIv.setImageResource(R.mipmap.radio_button_off);
        } else {
            holder.mGroundIv.setImageResource(R.mipmap.radio_button_off);
        }

        Log.e(">>>>>>>>>>", "========="+i );
        holder.mListParentTv.setText(node.getName());
        holder.mListParentContent.setText(mData.get(i).getContent());

        return converview;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mListParentTv;
        public TextView mListParentContent;
        public ImageView mGroundIv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mListParentTv = (TextView) rootView.findViewById(R.id.list_parent_tv);
            this.mListParentContent = (TextView) rootView.findViewById(R.id.list_parent_content);
            this.mGroundIv = (ImageView) rootView.findViewById(R.id.ground_iv);
        }

    }
}
