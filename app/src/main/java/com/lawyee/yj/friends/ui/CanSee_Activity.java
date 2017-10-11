package com.lawyee.yj.friends.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.adapter.MyExpandablelistviewAdapter;
import com.lawyee.yj.friends.tree.FileBean;
import com.lawyee.yj.friends.video.Release_videoActivity;
import com.lawyee.yj.friends.vo.IntentMark;
import com.lawyee.yj.friends.vo.TitleDataVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lawyee.yj.friends.ui.PhoneCameraActivity.CHILDREN;
import static com.lawyee.yj.friends.ui.PhoneCameraActivity.CONTENT;
import static com.lawyee.yj.friends.ui.PhoneCameraActivity.GROUP;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/7 16:49:16:49
 * @Purpose :查看权界面
 */


public class CanSee_Activity extends BaseActivity implements View.OnClickListener {

    private TextView mTitleText;
    private TextView mTitleSend;
    private ImageView mImageView;


    private ArrayList<String> parent = null;
    private Map<String, ArrayList<String>> map = null;


    private ExpandableListView mIdExpandableListView;
    private MyExpandablelistviewAdapter adapter;
    private ArrayList<TitleDataVo> mTitleDate;
    private List<FileBean> mDatas = new ArrayList<FileBean>();
/*    *//**
     * 回调参数
     *//*
    public static final String CONTENT = "content";
    public static final String GROUP ="GroupPosition";
    public static final String CHILDREN ="ChildrenPosition";
    public static  int POISTION ;
    */

    /**
     * 返回数据
     */
    private String result;//回调值
    private int GroupPosition;//回调父类坐标
    private int ChildrenPosition;//回调子类坐标
    private int mBack_group; //父类标示
    private int mBack_children;//子类标示

    private boolean setColor;//判断布局颜色
    private Toolbar mCanSeeToolbar;
    private RelativeLayout mTitleColor;
    private LinearLayout mActivityCanSee;
    private String stringExtra;//传参值


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_can_see_);
        initView();
        initTitle();
        setSupportActionBar(mCanSeeToolbar);
        Select_Mark();
    }

    /**
     * 选择分类
     */
    private void Select_Mark() {
        Intent intent = getIntent();
        String mIntentPcaData = intent.getStringExtra(IntentMark.PCA_INTETNT_CAS);
        String mIntentRevData = intent.getStringExtra(IntentMark.REV_INTETNT_CAS);

        if (mIntentPcaData != null && mIntentPcaData.equals(IntentMark.PCA_INTENT_DATA)) {
            setColor = true;
            Log.e(TAG, "=========== PhoneCameraActivity启用=======");
            initDataView();
        } else if (mIntentRevData != null && mIntentRevData.equals(IntentMark.REV_INTENT_DATA)) {
            setColor = false;
            Log.e(TAG, "=========== Release_videoActivity启用=======");
            initDataView();
        }

    }

    private void initDataView() {
        initTitle();
        addData();
        initData();
        initAllBackgroundColor();
    }

    private void initView() {
        mCanSeeToolbar = (Toolbar) findViewById(R.id.CanSeeToolbar);
        mTitleColor = (RelativeLayout) findViewById(R.id.title_color);
        mTitleColor.setOnClickListener(this);
        mActivityCanSee = (LinearLayout) findViewById(R.id.activity_can_see_);
        mActivityCanSee.setOnClickListener(this);
        mTitleText = (TextView) findViewById(R.id.title_Text);
        mTitleSend = (TextView) findViewById(R.id.title_send);
        mTitleSend.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.imageButton);
        mIdExpandableListView = (ExpandableListView) findViewById(R.id.id_expandableListView);
    }

    private void initTitle() {
        mTitleText.setText(getResources().getString(R.string.CanSee));
        mTitleSend.setText(getResources().getString(R.string.complete));
    }

    private void initAllBackgroundColor() {
        mTitleColor.setBackgroundResource(setColor == true ? R.color.colorPrimaryDark : R.color.black);
        mImageView.setBackgroundResource(setColor == true ? R.color.colorPrimaryDark : R.color.black);
        mTitleSend.setBackgroundResource(setColor == true ? R.color.colorPrimaryDark : R.color.black);
        mActivityCanSee.setBackgroundResource(setColor == true ? R.color.cansee : R.color.black);
        mCanSeeToolbar.setBackgroundResource(setColor == true ? R.color.colorPrimaryDark : R.color.black);
        mIdExpandableListView.setBackgroundResource(setColor == true ? R.color.white : R.color.black);
    }

    /**
     * 初始化SucfaceView
     */
    private void initData() {
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra(CONTENT);
        mBack_children = intent.getIntExtra(CHILDREN, 0);
        mBack_group = intent.getIntExtra(GROUP, 0);

        Log.e(TAG, "mBack_children: ===" + mBack_children + "mBack_group==" + mBack_group + "传过的值==" + stringExtra);

        adapter = new MyExpandablelistviewAdapter(this, map, parent, mTitleDate, setColor);
        mIdExpandableListView.setAdapter(adapter);

        if (mBack_group != 0) {
            mIdExpandableListView.setSelectedGroup(mBack_group);
            adapter.selectIndex(mBack_group);
            adapter.notifyDataSetChanged();
        }
        /**
         * ExpandableListView子类监听
         */
        mIdExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String key = parent.get(i);
                Toast.makeText(CanSee_Activity.this, "" + map.get(key).get(i1), Toast.LENGTH_SHORT).show();
                if (mBack_group != 0) {
                    adapter.selectChildIndex(mBack_group);
                } else {
                    adapter.selectChildIndex(i1);
                    Log.e(TAG, "=======" + i1);
                }
                result = map.get(key).get(i1);
                Log.e(TAG, "result 的值 " + result);
                ChildrenPosition = i1;
//                StartActitvity(map.get(key).get(i1));
                return false;

            }


        });
        /**
         * ExpandableListView父类监听
         */
        mIdExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String key = parent.get(i);
                Toast.makeText(CanSee_Activity.this, "" + key, Toast.LENGTH_SHORT).show();
                adapter.selectIndex(i);
                result = key;
                GroupPosition = i;
                Log.e(TAG, "OnGroup result传来的值 " + result);
//                StartActitvity(key);
                return false;
            }
        });

    }


    /**
     * 先测试出传过去String值
     *
     * @param
     */
    private void StartActitvity() {
        Log.e(TAG, "流程==0==" + result);
        if (result == null) {
            result = stringExtra;
        }
        Log.e(TAG, "流程==1==");
        if (setColor) {
            Log.e(TAG, "RS_REV_MARK_PHO:============== ");
            Intent intent = startIntent(PhoneCameraActivity.class);
            intent.putExtra(CONTENT, result);
            intent.putExtra(GROUP, GroupPosition);
            intent.putExtra(CHILDREN, ChildrenPosition);
            setResult(IntentMark.RS_REV_MARK_PHO, intent);
        } else {
            Log.e(TAG, "RS_REV_MARK_PHO: ==============");
            Intent intent1 = startIntent(Release_videoActivity.class);
            intent1.putExtra(IntentMark.CONTENT, result);
            intent1.putExtra(IntentMark.GROUP, GroupPosition);
            intent1.putExtra(IntentMark.CHILDREN, ChildrenPosition);
            setResult(IntentMark.RS_REV_MARK_PHO, intent1);
        }
        finish();
    }

    private Intent startIntent(Class<?> cla) {
        return new Intent(CanSee_Activity.this, cla);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                break;
            case R.id.title_send:
                Log.e(TAG, "title_send:  被点击");
                StartActitvity();
                break;
        }
    }

    private void addData() {
        mTitleDate = new TitleDataVo().add();

        parent = new ArrayList<String>();
        parent.add("公开");
        parent.add("尽自己可见");
        parent.add("部分可见");
        parent.add("不给谁看");

        map = new HashMap<String, ArrayList<String>>();

        ArrayList<String> list1 = new ArrayList<String>();
//        list1.add("child1-1");
//        list1.add("child1-2");
//        list1.add("child1-3");
        map.put("公开", list1);

        ArrayList<String> list2 = new ArrayList<String>();
//        list2.add("child2-1");
//        list2.add("child2-2");
//        list2.add("child2-3");
        map.put("尽自己可见", list2);

        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("child3-1");
        list3.add("child3-2");
        list3.add("child3-3");
        map.put("部分可见", list3);
        ArrayList<String> list4 = new ArrayList<String>();
        list4.add("child3-1");
        list4.add("child3-2");
        list4.add("child3-3");
        map.put("不给谁看", list4);

    }


}