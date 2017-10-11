package com.lawyee.yj.friends.video;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.adapter.FaceVAdapter;
import com.lawyee.yj.friends.adapter.FaceVPAdapter;
import com.lawyee.yj.friends.gaode.Location_Activity;
import com.lawyee.yj.friends.ui.BaseActivity;
import com.lawyee.yj.friends.ui.CanSee_Activity;
import com.lawyee.yj.friends.utils.MyEditText;
import com.lawyee.yj.friends.vo.IntentMark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : YFL  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/22 10:28:10:28
 * @Purpose :发布视频
 */

public class Release_videoActivity extends BaseActivity implements SurfaceHolder.Callback, View.OnClickListener {
    public static final String TAG = Release_videoActivity.class.getName();

    private ImageButton mFriendsBack;
    private Button mFriendsSend;
    private Toolbar mToolbar;
    private SurfaceView mSurfaceView;
    private String path; //录像地址
    private SurfaceHolder mSurfaceViewHolder;
    private MediaPlayer mediaPlayer;

    private ImageButton mShareLocation;
    private TextView mTvCanSee;
    private TextView mObert;//公开
    private RelativeLayout mInput;
    private IntentMark mMark;
    private LinearLayout mActivityReleaseVideo;
    private Toolbar mVideoToolbar;
    private String resultValue;
    private int mGroupPoistion;
    private int mChildPoistion;

    private int mLPoistion;//地址的坐标
    private String mLcationData;//地址的内容
    private TextView mHintLocation;


    private ArrayList<String> mStaticFacesList;
    private MyEditText mInputSms;
    private ViewPager mFaceViewpager;
    private LinearLayout mFaceDotsContainer;
    private ImageButton mImageFace;
    private LinearLayout mChat_face_container;
    // 7列3行
    private int columns = 6;
    private int rows = 4;
    //表情页
    private List<View> views = new ArrayList<View>();
    private LinearLayout mShowemoji;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_video);
        initView();
        initData();
    }
*/

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_release_video);
        initStaticFaces();
        initView();
        setSupportActionBar(mVideoToolbar);
        initData();
        setLocation();
        AddData();
    }

    /**
     * 初始化表情staticFacesList
     */
    private void initStaticFaces() {
        mStaticFacesList = new ArrayList<>();
        try {
            String[] mFaces = getAssets().list("face/png");
            for (int i = 0; i < mFaces.length; i++) {
                mStaticFacesList.add(mFaces[i]);
            }
            mStaticFacesList.remove("emotion_del_normal.png");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        mFriendsBack = (ImageButton) findViewById(R.id.friends_back);
        mFriendsSend = (Button) findViewById(R.id.friends_send);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mShareLocation = (ImageButton) findViewById(R.id.share_location);
        mTvCanSee = (TextView) findViewById(R.id.Tv_CanSee);
        mObert = (TextView) findViewById(R.id.obert);


        mInput = (RelativeLayout) findViewById(R.id.input);

        mInput.setOnClickListener(this);
        mFriendsBack.setOnClickListener(this);
        mFriendsSend.setOnClickListener(this);
        mShareLocation.setOnClickListener(this);
        mTvCanSee.setOnClickListener(this);
        mObert.setOnClickListener(this);

        mMark = new IntentMark();

        mActivityReleaseVideo = (LinearLayout) findViewById(R.id.activity_release_video);
        mActivityReleaseVideo.setOnClickListener(this);
        mVideoToolbar = (Toolbar) findViewById(R.id.Video_Toolbar);
        mVideoToolbar.setOnClickListener(this);
        mHintLocation = (TextView) findViewById(R.id.hintLocation);
        mHintLocation.setVisibility(View.INVISIBLE);
        mHintLocation.setOnClickListener(this);

        //表情显示
        mInputSms = (MyEditText) findViewById(R.id.input_sms);
        mInputSms.setOnClickListener(this);
//        表情布局
        mChat_face_container = (LinearLayout) findViewById(R.id.chat_face_container);
        mFaceViewpager = (ViewPager) findViewById(R.id.face_viewpager);
        mFaceViewpager.setOnPageChangeListener((ViewPager.OnPageChangeListener) new PageChange());
        mFaceViewpager.setOnClickListener(this);
        //表情小圆点布局
        mFaceDotsContainer = (LinearLayout) findViewById(R.id.face_dots_container);
        mFaceDotsContainer.setOnClickListener(this);
        InitViewPager();
        //表情图标按钮
        mImageFace = (ImageButton) findViewById(R.id.image_face);
        mImageFace.setOnClickListener(this);
        mShowemoji = (LinearLayout) findViewById(R.id.showemoji);
        mShowemoji.setOnClickListener(this);

    }

    private void initData() {
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
            mSurfaceViewHolder = mSurfaceView.getHolder();
            /**
             * 注册surefaceView创建。改变和销毁时调用的执行方法
             */
            mSurfaceViewHolder.addCallback(this);
            /**
             *  这里必须设置为SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS哦，意思
             *  是创建一个push的'surface'，主要的特点就是不进行缓冲
             */
            mSurfaceViewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.friends_back:
                this.finish();
                break;
            case R.id.friends_send:
                // TODO: 2016/12/23 提角服务器

                break;
            case R.id.share_location:
                // TODO: 2016/12/23 地址分享
                Share_location();

                break;
            case R.id.obert:
                // TODO: 2016/12/23 是否公开
                Share_obert();
                break;
            case R.id.Tv_CanSee:
                Share_obert();
                break;
            case R.id.hintLocation:
                Share_location();
                break;
            case R.id.image_face:
                hideSoftInputView();//隐藏键盘
                if (mShowemoji.getVisibility() == View.GONE) {
                    mShowemoji.setVisibility(View.VISIBLE);
                } else if (mShowemoji.getVisibility() == View.VISIBLE) {
                    mShowemoji.setVisibility(View.GONE);
                }
                break;
            case R.id.input_sms:
                if (mShowemoji.getVisibility() == View.VISIBLE) {
                mShowemoji.setVisibility(View.GONE);
            }
                break;

        }
    }


    /**
     * 公开
     */
    private void Share_obert() {
        String obert = mObert.getText().toString().trim();
        Intent intent = StartActivity(CanSee_Activity.class);
        intent.putExtra(mMark.VIDEO_OBSERT_MARK, obert);
        intent.putExtra(IntentMark.GROUP, mGroupPoistion);
        intent.putExtra(IntentMark.CHILDREN, mChildPoistion);
        intent.putExtra(mMark.REV_INTETNT_CAS, mMark.REV_INTENT_DATA);
        startActivityForResult(intent, mMark.VIDEO_RQ_OBSERT_MARK);
    }

    /**
     * 地址
     */
    private void Share_location() {
        Intent intent = StartActivity(Location_Activity.class);
        intent.putExtra(IntentMark.REV_LCO_POISTION, mLPoistion);
        intent.putExtra(IntentMark.REV_LCO_LOCATION, mHintLocation.getText().toString().trim());
        intent.putExtra(IntentMark.REV_INTENT_LOCATION, IntentMark.REV_INTENT_DATAS);
        startActivityForResult(intent, IntentMark.REQUESTMARK_REV);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentMark.RS_REV_MARK_PHO) {
            resultValue = data.getStringExtra(IntentMark.CONTENT);
            mGroupPoistion = data.getIntExtra(IntentMark.GROUP, 0);
            mChildPoistion = data.getIntExtra(IntentMark.CHILDREN, 0);
            AddData();
        } else if (resultCode == IntentMark.RESULSTMARK_REV) {
            mLcationData = data.getStringExtra(IntentMark.REV_LCO_LOCATION);
            mLPoistion = data.getIntExtra(IntentMark.REV_LCO_POISTION, 0);
            Log.e(TAG, "地址传来== " + mLcationData + "|||||" + mLPoistion);
            setLocation();
        }


    }

    /**
     * 添加数据
     */
    private void AddData() {
        if (resultValue == null) {
            mObert.setText(getResources().getString(R.string.obert));
        } else {
            mObert.setMaxEms(10);
            mObert.setMarqueeRepeatLimit(-1);
            mObert.setHorizontallyScrolling(true);
            mObert.setText(resultValue);
            Log.e(TAG, "location:传过的值 " + resultValue);
            Log.e(TAG, "mHintLocation: 传过的值" + mObert.getText().toString());
        }
    }

    private void setLocation() {

        Log.e(TAG, "=====传过来的值====== " + mLcationData);
        if (mLcationData == null) {
            mHintLocation.setText(getResources().getString(R.string.no_show));
        } else {

            Log.e(TAG, "=====传过来的值====== " + mLcationData);
            if (mLcationData.equals(getResources().getString(R.string.no_show))) {
                mHintLocation.setText(getResources().getString(R.string.no_show));
                showlocation(false);
            } else {
                mHintLocation.setMaxEms(10);
                mHintLocation.setMarqueeRepeatLimit(-1);
                mHintLocation.setHorizontallyScrolling(true);
                mHintLocation.setText(mLcationData);
                Log.e(TAG, "location:传过的值 " + mLcationData);
                Log.e(TAG, "mHintLocation: 传过的值" + mHintLocation.getText().toString());
                showlocation(true);
            }
        }
    }


    private void showlocation(boolean isColor) {
        mShareLocation.setImageResource(isColor == false ? R.mipmap.ic_location_up : R.mipmap.ic_location_down);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer.setDisplay(mSurfaceViewHolder);
        Log.e(TAG, "surfaceCreated: 创建");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e(TAG, "surfaceChanged:尺寸改变 ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e(TAG, "surfaceDestroyed: 销毁");
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    /**
     * @param cla 跳转到的类
     * @return intent 意图
     */
    private Intent StartActivity(Class<?> cla) {
        Intent intent = new Intent(Release_videoActivity.this, cla);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ====");
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: =======");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ==========");
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    /**
     * 初始化表情
     */
    private void InitViewPager() {
//    获取表情页数

        for (int i = 0; i < getPagerCount(); i++) {
            views.add(viewPagerItem(i));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(16, 16);
            mFaceDotsContainer.addView(dotsItem(i), params);
        }
        FaceVPAdapter mVpAdapter = new FaceVPAdapter(views);
        mFaceViewpager.setAdapter(mVpAdapter);
        mFaceDotsContainer.getChildAt(0).setSelected(true);

    }

    private ImageView dotsItem(int position) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dot_image, null);
        ImageView iv = (ImageView) layout.findViewById(R.id.face_dot);
        iv.setId(position);
        return iv;
    }

    //添加布局
    private View viewPagerItem(int position) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.face_gridview, null);//表情布局
        GridView gridview = (GridView) layout.findViewById(R.id.chart_face_gv);
        /**
         * 注：因为每一页末尾都有一个删除图标，所以每一页的实际表情columns *　rows　－　1; 空出最后一个位置给删除图标
         * */
        ArrayList<String> subList = new ArrayList<String>();
        subList.addAll(mStaticFacesList
                .subList(position * (columns * rows - 1),
                        (columns * rows - 1) * (position + 1) > mStaticFacesList.size() ?
                                mStaticFacesList.size() : (columns * rows - 1) * (position + 1)));

        /**
         * 末尾添加删除图标
         */
        subList.add("emotion_del_normal.png");

        FaceVAdapter faceVAdapter = new FaceVAdapter(this, subList);
        gridview.setAdapter(faceVAdapter);
        gridview.setNumColumns(columns);
        //点击表情执行操作
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    String png = ((TextView) ((LinearLayout) view).getChildAt(1)).getText().toString();
                    if (!png.contains("emotion_del_normal")) {//如果不是删除图标
                        insert(getFace(png));
                    } else {
                        delete();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return gridview;
    }


    /**
     * 删除图标执行事件
     * 注：如果删除的是表情，在删除时实际删除的是tempText即图片占位的字符串，所以必需一次性删除掉tempText，才能将图片删除
     */
    private void delete() {
        if (mInputSms.getText().length() != 0) {
            int iCursorEnd = Selection.getSelectionEnd(mInputSms.getText());
            int iCursorStart = Selection.getSelectionStart(mInputSms.getText());
            if (iCursorEnd > 0) {
                if (iCursorEnd == iCursorStart) {
                    if (isDeletePng(iCursorEnd)) {
                        String st = "#[face/png/f_static_000.png]#";
                        ((Editable) mInputSms.getText()).delete(
                                iCursorEnd - st.length(), iCursorEnd);
                    } else {
                        ((Editable) mInputSms.getText()).delete(iCursorEnd - 1,
                                iCursorEnd);
                    }
                } else {
                    ((Editable) mInputSms.getText()).delete(iCursorStart,
                            iCursorEnd);
                }
            }
        }

    }

    /**
     * 判断即将删除的字符串是否是图片占位字符串tempText 如果是：则讲删除整个tempText
     **/
    private boolean isDeletePng(int cursor) {
        String st = "#[face/png/f_static_000.png]#";
        String content = mInputSms.getText().toString().substring(0, cursor);
        if (content.length() >= st.length()) {
            String checkStr = content.substring(content.length() - st.length(),
                    content.length());
            String regex = "(\\#\\[face/png/f_static_)\\d{3}(.png\\]\\#)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(checkStr);
            return m.matches();
        }
        return false;

    }

    private SpannableStringBuilder getFace(String png) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        try {

            /**
             * 经过测试，虽然这里tempText被替换为png显示，但是但我单击发送按钮时，获取到輸入框的内容是tempText的值而不是png
             * 所以这里对这个tempText值做特殊处理
             * 格式：#[face/png/f_static_000.png]#，以方便判斷當前圖片是哪一個
             * */
            String tempText = "#[" + png + "]#";
            sb.append(tempText);
            sb.setSpan(new ImageSpan(Release_videoActivity.this, BitmapFactory.decodeStream(
                    getAssets().open(png))), sb.length() - tempText.length(), sb.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     * 想输入框添加表情
     */
    private void insert(CharSequence text) {
        //开始的位置
        int iCursoriStart = Selection.getSelectionStart((mInputSms.getText()));
        //结束位置
        int iCursorEnd = Selection.getSelectionEnd((mInputSms.getText()));
        if (iCursorEnd != iCursoriStart) {
            ((Editable) mInputSms.getText()).replace(iCursoriStart, iCursorEnd, "");
        }
        int iCuros = Selection.getSelectionEnd((mInputSms.getText()));
        ((Editable) mInputSms.getText()).insert(iCuros, text);
    }

    /**
     * 根据表情数量以及GridView设置的行数和列数计算Pager数量
     *
     * @return
     */

    private int getPagerCount() {
        int size = mStaticFacesList.size();
        return size % (columns * rows - 1) == 0 ? size / (columns * rows - 1) :
                size / (columns * rows - 1) + 1;
    }

    /**
     * .隐藏键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 表情页改变时，dots效果也要跟着
     */
    class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mFaceDotsContainer.getChildCount(); i++) {
                mFaceDotsContainer.getChildAt(i).setSelected(false);
            }
            mFaceDotsContainer.getChildAt(position).setSelected(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
