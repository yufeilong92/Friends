package com.lawyee.yj.friends.gaode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;
import com.lawyee.yj.friends.adapter.ShowLocationAdapter;
import com.lawyee.yj.friends.ui.PhoneCameraActivity;
import com.lawyee.yj.friends.R;
import com.lawyee.yj.friends.vo.IntentMark;

import java.util.ArrayList;

/**
 * 高精度定位模式功能演示
 *
 * @author hongming.wang
 * @创建时间： 2015年11月24日 下午5:22:42
 * @项目名称： AMapLocationDemo2.x
 * @文件名称: Hight_Accuracy_Activity.java
 * @类型名称: Hight_Accuracy_Activity
 */
public class Location_Activity extends CheckPermissionsActivity {

    private static final String TAG = "流程查询";
    private TextView tvResult;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    private ShowLocationAdapter adapter;

    private ArrayList<String> mListLocation;
    private ListView mLocationShowLV;
    private ScrollView mLocatonShowScrolLView;
    private LinearLayout mLocationShowLl;
    private RelativeLayout mLocationShowloaing;
    private ImageView mLocationIvBack;
    private RelativeLayout mShowNoshowLocation;
    private ImageView mLocationMS;
    private TextView mLocationShowText;
    private ImageView mLocationShowImge;
    private RelativeLayout mShowShowLocationRela;
    private boolean setColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();

        initSelect();

    }

    private void initSelect() {
        Intent intent = getIntent();
        String mPhcMark = intent.getStringExtra(IntentMark.PHC_INTENT_LOCATION);
        String mRevMark = intent.getStringExtra(IntentMark.REV_INTENT_LOCATION);
        if (mPhcMark != null && mPhcMark.equals(IntentMark.PHC_INTENT_DATAS)){
            Log.e(TAG, "==========phoneCamert调用======== " );
            setColor=true;
            initConetnt();
        }else if (mRevMark != null && mRevMark.equals(IntentMark.REV_INTENT_DATAS))
        {
            Log.e(TAG, "==========Relavtiveview调用======== " );
            setColor=false;
            initConetnt();
        }
    }

    private void initConetnt(){
        //初始化定位
        initLocation();

    }



    private void initData() {

        mLocationShowLV.setOnItemClickListener(itemClickListener);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        int poistion = intent.getIntExtra("poistion", 0);

        Log.e(TAG, "phontCamera 传过的坐标: ==" + poistion + "c传过来的值" + location);
        mShowNoshowLocation.setOnClickListener(listener);
        if (location.equals(getResources().getString(R.string.no_show))) {
            adapter.setInitShow(true);
            mShowShowLocationRela.setVisibility(View.GONE);

        } else {
            if (String.valueOf(poistion) != null) {
                mShowShowLocationRela.setVisibility(View.VISIBLE);
                mLocationShowText.setText(location);
                adapter.setInitShow(false);
                mLocationMS.setVisibility(View.INVISIBLE);
                adapter.setShow(poistion);
                mShowShowLocationRela.setVisibility(View.GONE);
            }
        }


        stopLocation();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String location = getResources().getString(R.string.no_show);
            Resultintent(0, location);
        }
    };

    //初始化控件
    private void initView() {

        tvResult = (TextView) findViewById(R.id.tv_result);

        mLocationShowLV = (ListView) findViewById(R.id.location_showLV);

        mLocatonShowScrolLView = (ScrollView) findViewById(R.id.locaton_showScrolLView);

        mLocationShowLl = (LinearLayout) findViewById(R.id.location_showLl);

        mLocationShowloaing = (RelativeLayout) findViewById(R.id.location_showloaing);

        mLocationIvBack = (ImageView) findViewById(R.id.location_Iv_back);
        mLocationIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLocationMS = (ImageView) findViewById(R.id.location_MS);
        mShowNoshowLocation = (RelativeLayout) findViewById(R.id.show_noshowLocation);


        mLocationShowText = (TextView) findViewById(R.id.location_showText);
        mLocationShowImge = (ImageView) findViewById(R.id.location_showImge);
        mShowShowLocationRela = (RelativeLayout) findViewById(R.id.show_showLocation_Rela);
        mShowShowLocationRela.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        startLocation();


    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int poistion, long id) {
            String location = mListLocation.get(poistion);
            Resultintent(poistion, location);
        }
    };

    private void Resultintent(int poistion, String location) {


        Intent intent = new Intent(Location_Activity.this, PhoneCameraActivity.class);
        intent.putExtra("location", location);
        intent.putExtra("poistion", poistion);
        setResult(new PhoneCameraActivity().RESULTMARK, intent);
        finish();
    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                mListLocation = new ArrayList<>();
//                mListLocation.add(getResources().getString(R.string.no_show));
                String result = Utils.getLocationStr(loc, mListLocation);

                if (loc.getErrorCode() != 0) {
                    // TODO: 2016/12/6跳转到系统设置
                    mLocationShowLl.setVisibility(View.GONE);
                    tvResult.setText(result);//错误信息
                } else {
                    mLocatonShowScrolLView.setVisibility(View.GONE);

                    adapter = new ShowLocationAdapter(Location_Activity.this, mListLocation);
                    mLocationShowLV.setAdapter(adapter);
                    mLocationShowloaing.setVisibility(View.GONE);
                }
                initData();

            } else {
                tvResult.setText("定位失败，loc is null");
                mLocationShowloaing.setVisibility(View.GONE);
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        Log.e("地图查询", "开始定位");
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        Log.e("地图查询", "停止定位");
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
