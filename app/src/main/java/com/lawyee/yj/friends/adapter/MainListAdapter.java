package com.lawyee.yj.friends.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lawyee.yj.friends.utils.TitlePopup;
import com.lawyee.yj.friends.utils.Util;
import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.utils.ActionItem;
import com.lawyee.yj.friends.vo.DisplaydataVO;

import java.util.ArrayList;


/**
 * Created by yfl on 2016/11/29 14:56.
 *
 * @email: yufeilong92@163.com
 * @purpose:主界面展示适配器
 */
public class MainListAdapter extends BaseAdapter {

    private static final String TAG = "MainListAdapter:";

    private Context mContext;
    private ArrayList<DisplaydataVO> mData;
    private final LayoutInflater inflater;
    private int number;
    private PopupWindow mPPW;
    private TitlePopup titlePopup;

    public MainListAdapter(Context context, ArrayList<DisplaydataVO> mdata) {
        this.mContext = context;
        this.mData = mdata;
        inflater = LayoutInflater.from(context);
    }

    public void setmData(ArrayList<DisplaydataVO> mData) {
        this.mData = mData;
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
            holder = new ViewHolder();
            converview = inflater.inflate(R.layout.listitem_data, viewGroup, false);
            holder.user_head_pct = (SimpleDraweeView) converview.findViewById(R.id.user_head_pct);
            holder.user_name = (TextView) converview.findViewById(R.id.user_name);
            holder.user_content = (TextView) converview.findViewById(R.id.user_content);
            holder.list_Gridview = (GridView) converview.findViewById(R.id.list_Gridview);
            holder.list_item_time = (TextView) converview.findViewById(R.id.list_item_time);
            holder.list_btnDiscuss = (ImageButton) converview.findViewById(R.id.list_btnDiscuss);
            holder.list_item_discuss = (ListView) converview.findViewById(R.id.list_item_discuss);
            converview.setTag(holder);
        } else {
            holder = (ViewHolder) converview.getTag();
        }

        initMainView(holder, i);

        return converview;
    }

    private void initMainView(ViewHolder holder, int i) {
        DisplaydataVO vo = mData.get(i);
        holder.user_name.setText(vo.getUsername());
        holder.user_content.setText(vo.getUsercontent());
        GridViewAdapter adapter = new GridViewAdapter(mContext, mData.get(i).getPicUrl());
        holder.list_Gridview.setAdapter(adapter);

        showPopuWindow(holder);


    }

    private String cancel = "取消";
    private String fabulous = "赞";
    private String evaluate = "评价";

    private void showPopuWindow(ViewHolder holder) {
        holder.list_btnDiscuss.setOnClickListener(onclick);
        //设置显示的框的宽高
        titlePopup = new TitlePopup(mContext, Util.dip2px(mContext, 180), Util.dip2px(mContext, 30));
        titlePopup
                .addAction(new ActionItem(mContext, fabulous, R.drawable.circle_praise));
        titlePopup.addAction(new ActionItem(mContext,evaluate,
                R.drawable.circle_comment));
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                String s = item.mTitle.toString();
                Log.e(TAG, "onItemClick: "+s );
//                if (s.equals("")) return;
                if (s.equals(fabulous)) {
                    Log.e(TAG, "fabulous: " );
                    titlePopup.cleanAction();
                    titlePopup.addAction(new ActionItem(cancel));

                }
                if (s.equals(evaluate)) {

                }
                if (s.equals(cancel)){
                    Log.e(TAG, "cancel: " );
                    titlePopup.cleanAction();
                    titlePopup
                            .addAction(new ActionItem(mContext, fabulous, R.drawable.circle_praise));
                }
            }
        });
    }

    private View.OnClickListener onclick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            titlePopup.setAnimationStyle(R.style.cricleBottomAnimation);
            titlePopup.show(v);
        }

    };

    class ViewHolder {
        private View converview;
        private SimpleDraweeView user_head_pct;
        private TextView user_name;
        private TextView user_content;
        private GridView list_Gridview;
        private TextView list_item_time;
        private ImageButton list_btnDiscuss;
        private ListView list_item_discuss;

    }
}
