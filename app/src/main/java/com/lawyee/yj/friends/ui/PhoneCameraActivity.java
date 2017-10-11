package com.lawyee.yj.friends.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.adapter.PhotoAdapter;
import com.lawyee.yj.friends.adapter.RecyclerItemClickListener;
import com.lawyee.yj.friends.gaode.Location_Activity;
import com.lawyee.yj.friends.vo.IntentMark;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * Created by yfl on 2016/11/28 18:11.
 *
 * @email: yufeilong92@163.com
 * @purpose: 分享页
 */

public class PhoneCameraActivity extends BaseActivity implements View.OnClickListener {

private IntentMark intentMark=new IntentMark();
    private TextView mTitleText;
    private TextView mTitleSend;
    private Toolbar mCameraToobar;
   private IntentMark mIntentMark=new IntentMark();//标示
    private RecyclerView mRecycler_view;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private RelativeLayout mCamera_loaction;
    private RelativeLayout mCamera_CanSee;
    private RelativeLayout mCamera_Remind_who;
    private TextView mHintLocation;
    /**
     * 地址传过的内容与坐标
     */
    private String location;
    private int poistion;
    /**
     * cansee界面的内容与坐标
     */
    private String CSContent;
    private int CSGroupposition;
    private int CSChildposition;
    public String CS_Intent_Mark;
    public String CS_Intent_ACTIVITY_Mark="PhoneCameraActivity";
    /**
     * 地址
     */
    public static final int REQUESTMARK = 10030; //请求码
    public static final int RESULTMARK = 10031;//结果码
    /**
     * 谁能看
     */
    public static final int PHONECAMERACAN = 10032;
    public static final int LOCATIONCANSEE = 10033;
    /**
     * CanSee回调参数
     */
    public static final String CONTENT = "content";
    public static final String GROUP = "GroupPosition";
    public static final String CHILDREN = "ChildrenPosition";
    public static int POISTION;


    /**
     * 提醒谁
     */
    public static final int PHONECAMERAWHO = 10034;
    public static final int LOCATIONWHO = 10035;


    private ImageView mShowLocback;
    private ImageView mAuj;
    private ImageView mAul;
    private ImageView mAub;
    private ImageView mCameraShare;
    private TextView mHintCansee;

    @Override
    protected void initContentView() {
        setContentView(R.layout.phonecamere_activity);
        findViewID();
        setSupportActionBar(mCameraToobar);
        addPicture();
        initContView();
        setLoaction();
        seeCanSee();
    }

    private void setLoaction() {
        if (location == null) {
            mHintLocation.setText(getResources().getString(R.string.no_show));
        } else {

            if (location.equals(getResources().getString(R.string.no_show))) {
                mHintLocation.setText(getResources().getString(R.string.no_show));
                showlocation(false);
            } else {
                mHintLocation.setMaxEms(10);
                mHintLocation.setMarqueeRepeatLimit(-1);
                mHintLocation.setHorizontallyScrolling(true);
                mHintLocation.setText(location);
                Log.e(TAG, "location:传过的值 " + location);
                Log.e(TAG, "mHintLocation: 传过的值" + mHintLocation.getText().toString());
                showlocation(true);


            }

        }

    }

    /**
     * 公开
     */
    private void seeCanSee() {

        if (CSContent == null) {
            mHintCansee.setText(getResources().getString(R.string.obert));

        } else {
            mHintCansee.setMaxEms(10);
            mHintCansee.setMarqueeRepeatLimit(-1);
            mHintCansee.setHorizontallyScrolling(true);
            mHintCansee.setText(CSContent);
            Log.e(TAG, "location:传过的值 " + CSContent);
            Log.e(TAG, "mHintLocation: 传过的值" + mHintCansee.getText().toString());
           showCanSee(true);
        }

    }

    /**
     * 添加图片
     */

    private void addPicture() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setGridColumnCount(4)
                .start(this);
    }

    private void initContView() {
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        mRecycler_view.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mRecycler_view.setAdapter(photoAdapter);
        mRecycler_view.addOnItemTouchListener(new RecyclerItemClickListener(PhoneCameraActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhotoPreview.builder()
                        .setPhotos(selectedPhotos)
                        .setCurrentItem(position)
                        .start(PhoneCameraActivity.this);

            }
        }));

    }

    private void findViewID() {
        initView();
        mTitleText = (TextView) findViewById(R.id.title_Text);
        mTitleText.setOnClickListener(this);
        mTitleSend = (TextView) findViewById(R.id.title_send);
        mTitleSend.setOnClickListener(this);
        mCameraToobar = (Toolbar) findViewById(R.id.Camera_toobar);
        mRecycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        mShowLocback = (ImageView) findViewById(R.id.show_locback);
        mHintLocation = (TextView) findViewById(R.id.hint_location);

        mCamera_loaction = (RelativeLayout) findViewById(R.id.Camera_loaction);
        mCamera_loaction.setOnClickListener(this);
        mCamera_CanSee = (RelativeLayout) findViewById(R.id.Camera_CanSee);
        mCamera_CanSee.setOnClickListener(this);
        mCamera_Remind_who = (RelativeLayout) findViewById(R.id.Camera_Remind_who);
        mCamera_Remind_who.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Camera_loaction:
                Intent intent= startIntent(Location_Activity.class);
                intent.putExtra(IntentMark.REV_LCO_POISTION, poistion);
                intent.putExtra(IntentMark.REV_LCO_LOCATION, mHintLocation.getText().toString().trim());
                intent.putExtra(mIntentMark.PHC_INTENT_LOCATION,mIntentMark.PHC_INTENT_DATAS);
                startActivityForResult(intent, REQUESTMARK);
                break;
            case R.id.Camera_CanSee:
                Intent intent1 = startIntent(CanSee_Activity.class);
                intent1.putExtra(GROUP,CSGroupposition);
                intent1.putExtra(CHILDREN,CSChildposition);
                intent1.putExtra(CONTENT,mHintCansee.getText().toString().trim());
                intent1.putExtra(mIntentMark.PCA_INTETNT_CAS,mIntentMark.PCA_INTENT_DATA);
                Log.e(TAG, "CSContent: "+ mHintCansee.getText().toString().trim());
                startActivityForResult(intent1, IntentMark.RQ_REV_MARK_PHO);
                break;
            case R.id.Camera_Remind_who:
                startActivity(new Intent(PhoneCameraActivity.this, RemindWho_Activity.class));
                break;

            default:
                break;
        }
    }

    private Intent startIntent(Class<?> act){
        Intent intent = new Intent(PhoneCameraActivity.this, act);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode ==
                PhotoPreview.REQUEST_CODE)) {
            if (data != null) {
//                ArrayList<String> photos =
//                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                List<String> photos = null;
                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                }
                selectedPhotos.clear();
                if (photos != null) {
                    selectedPhotos.addAll(photos);
                }
                photoAdapter.notifyDataSetChanged();
            }
        } else if (resultCode == RESULTMARK) {
            location = data.getStringExtra("location");
            poistion = data.getIntExtra("poistion", 0);
            setLoaction();

        } else if (resultCode == IntentMark.RS_REV_MARK_PHO) {
            CSContent = data.getStringExtra(CONTENT);
            CSGroupposition = data.getIntExtra(GROUP, 0);
            CSChildposition = data.getIntExtra(CHILDREN, 0);
            seeCanSee();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    private void initView() {
        mShowLocback = (ImageView) findViewById(R.id.show_locback);
        mAuj = (ImageView) findViewById(R.id.auj);
        mAul = (ImageView) findViewById(R.id.aul);
        mAub = (ImageView) findViewById(R.id.aub);
        mCameraShare = (ImageView) findViewById(R.id.Camera_share);
        mCameraShare.setOnClickListener(this);
        mHintCansee = (TextView) findViewById(R.id.hint_cansee);
        mHintCansee.setOnClickListener(this);
    }

    private void showlocation(boolean isShow) {
        mAuj.setImageResource(isShow == false ? R.mipmap.auj : R.mipmap.auj_show);
    }

    private void showCanSee(boolean isShow) {
        mAul.setImageResource(isShow == false ? R.mipmap.aul : R.mipmap.aul_show);
    }

    private void showRemindWho(boolean isShow) {
        mAub.setImageResource(isShow == false ? R.mipmap.aub : R.mipmap.auj_show);
    }

}
