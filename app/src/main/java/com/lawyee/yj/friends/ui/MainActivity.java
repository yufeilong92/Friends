package com.lawyee.yj.friends.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lawyee.yj.friends.adapter.MainListAdapter;
import com.lawyee.yj.friends.utils.SwpipeListViewOnScrollListener;
import com.lawyee.yj.friends.video.SamllVideoActivity;
import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.vo.DisplaydataVO;

import java.util.ArrayList;
/**
 * @Author : Yufeilong  is Creating a porject in
 * @Email : yufeilong92@163.com
 * @Time : :
 * @Purpose : 主显示页
 */




public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int ADDMORE = 10021;
    private MainListAdapter adapter;
    private ArrayList<DisplaydataVO> mRsults = new ArrayList<>();
    private Handler handlers = new Handler();
    private TextView mTitleTextsh;
    private ImageButton mTitleCamera;
    private Toolbar mMainToolbar;
    private ListView mMainListView;
    private SwipeRefreshLayout mSRLayout;
    private View minflate;
    private TextView mHeadName;
    private RelativeLayout mHeadBackgroud;
    private SimpleDraweeView mHeadPic;

    private int visibleLastIndex;//显示最后一条数据位置


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
        findid();
        setSupportActionBar(mMainToolbar);
        initView();
        addNewData();
    }

    private void initView() {
        mSRLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_light);
        mSRLayout.setOnRefreshListener(refreshListener);
        adapter = new MainListAdapter(this, new DisplaydataVO().add());

        minflate = LayoutInflater.from(this).inflate(R.layout.listview_head, null);

        mMainListView.addHeaderView(minflate);

        mMainListView.setAdapter(adapter);
        //添加listview头信息
        addHeader_info();
        SwpipeListViewOnScrollListener listener = new SwpipeListViewOnScrollListener(mSRLayout, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                // TODO: 2016/11/29 加载新的数据
                new LoadDataThread().start();
            }

            @Override
            public void onScroll(AbsListView absListView, int fistVisbleItem, int visibleItem, int totalItemCoun) {
                visibleLastIndex = fistVisbleItem + visibleItem - 1;//减去最后一个加载中那条
                Log.e(TAG, "onScroll: "+"fistVisbleItem="+fistVisbleItem+"visibleItem=" +visibleItem+"visibleLastIndex="+visibleLastIndex);
            }
        });
        mMainListView.setOnScrollListener(listener);
    }

    private void addHeader_info() {

        mHeadName = (TextView) minflate.findViewById(R.id.main_head_username);
        mHeadBackgroud = (RelativeLayout) minflate.findViewById(R.id.main_head_background);
        mHeadPic = (SimpleDraweeView) minflate.findViewById(R.id.main_herad_Py_Imge);

        mHeadName.setText("小丽");
        mHeadBackgroud.setBackgroundColor(getResources().getColor(R.color.cyan));
        String URI = "http://p3.so.qhmsg.com/t01cd945e7fdb2cd60d.jpg";
        Uri parse = Uri.parse(URI);
        mHeadPic.setImageURI(parse);
    }

    private void findid() {
        mTitleTextsh = (TextView) findViewById(R.id.title_Textsh);
        mTitleTextsh.setOnClickListener(this);
        mTitleCamera = (ImageButton) findViewById(R.id.title_camera);
        mTitleCamera.setOnClickListener(this);
        mMainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mMainListView = (ListView) findViewById(R.id.main_ListView);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.SR_layout);

    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // TODO: 2016/11/29 刷新加载数据
            addNewData();

        }
    };

    private void addNewData() {
        mSRLayout.setRefreshing(true);
        handlers.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSRLayout.isRefreshing()) {
                    mSRLayout.setRefreshing(false);
                }
            }
        }, 3000);
    }



    private void addListDat(ArrayList<DisplaydataVO> mlist) {
        if (mRsults == null) {
            clearlistdata();
        }
        if (mlist == null || mlist.isEmpty())
            return;
        mRsults.clear();
        mRsults.addAll(mlist);
    }

    private void clearlistdata() {
        if (mRsults == null) {
            mRsults = new ArrayList<>();
            adapter = new MainListAdapter(this, mRsults);
            mMainListView.setAdapter(adapter);
        } else {
            adapter.setmData(mRsults);

        }
    }



 /*   @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // TODO: 2016/11/29 加载新的数据
        new LoadDataThread().start();

    }

    @Override
    public void onScroll(AbsListView absListView, int fistVisbleItem, int visibleItem, int totalItemCoun) {
        visibleLastIndex = fistVisbleItem + visibleItem - 1;//减去最后一个加载中那条
        Log.e(TAG, "onScroll: "+"fistVisbleItem="+fistVisbleItem+"visibleItem=" +visibleItem+"visibleLastIndex="+visibleLastIndex);

    }*/

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == ADDMORE) {
                adapter.notifyDataSetChanged();
                mSRLayout.setRefreshing(false);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_camera:
                showdialog();
                break;
        }
    }

    private void showdialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String[] items = getResources().getStringArray(R.array.DialogItem);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String select_item = items[i].toString();
//                Toast.makeText(MainActivity.this,
//                        "选择了---》" + select_item, Toast.LENGTH_SHORT)
//                        .show();

                if (select_item.equals(items[0].toString())) {
                    StartCamera();
                } else if(select_item.equals(items[1].toString())) {
                    StartVideo();

                }
            }
        });

        builder.show();
    }

    private void StartVideo() {
        Intent intent = new Intent(MainActivity.this, SamllVideoActivity.class);
        startActivity(intent);


    }


    private void StartCamera() {

        Intent intent = new Intent(MainActivity.this, PhoneCameraActivity.class);
        startActivity(intent);
    }

    class LoadDataThread extends Thread {
        @Override
        public void run() {
            addMoreData();//加载数据
            Message msg = handler.obtainMessage();
            msg.arg1 = ADDMORE;
            msg.sendToTarget();
        }
    }

    private void addMoreData() {

        // TODO: 2016/11/29 获取新的数据添加到list

    }


}
