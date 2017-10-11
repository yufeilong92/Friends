package com.lawyee.yj.friends.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawyee.yj.friends.R;

import java.util.ArrayList;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/6 14:17
 * @Purpose : 显示地图
 */
public class ShowLocationAdapter extends BaseAdapter {


    private ArrayList<String> mData;
    private Context mContext;
    private final LayoutInflater mInflater;
    private int id;
    private ImageView mShowSelect;

    private boolean isshow ;//是否是第一次

    public ShowLocationAdapter(Context context, ArrayList<String> mData) {
        this.mData = mData;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setShow(int id) {
        this.id = id;
        notifyDataSetChanged();
    }
    public void setInitShow(boolean isshow){
        this.isshow=isshow;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData == null ? null : mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (converview == null) {
            converview = mInflater.inflate(R.layout.show_loaction, null);
            holder = new ViewHolder(converview);
            converview.setTag(holder);
        } else {
            holder = (ViewHolder) converview.getTag();
        }

        Log.e(">>>>>>", "id: "+id );

     if(isshow){
         holder.mLocationCountry.setText(mData.get(i).toString());
     }else {
         if (i==0){
       /*     holder.mLocationCountry.setLines(2);
            holder.mLocationCountry.setTextSize(16);
            holder.mLocationCountry.setTextColor(Color.BLACK);*/
             holder.mShowSelect.setVisibility(View.VISIBLE);
             holder.mLocationCountry.setText(mData.get(i).toString());
         }

         if (i==id){
             holder.mShowSelect.setVisibility(View.VISIBLE);
         }else {
             holder.mShowSelect.setVisibility(View.GONE);
         }

         holder.mLocationCountry.setText(mData.get(i).toString());
     }


        return converview;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView mLocationCountry;
        public ImageView mShowSelect;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mLocationCountry = (TextView) rootView.findViewById(R.id.location_country);
            this.mShowSelect = (ImageView) rootView.findViewById(R.id.show_select);
        }

    }


}
