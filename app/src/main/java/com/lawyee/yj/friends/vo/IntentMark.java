package com.lawyee.yj.friends.vo;

/**
 * @Author : YFL  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/23 17:50
 * @Purpose :传输标示
 */
public class IntentMark {
    /**
     * 从Release_videoActivity传入CanSee_Activity标示
     * 小视频传输
     */
    //公开
    public static final String VIDEO_OBSERT_MARK = "ccontent";
    public static final int VIDEO_RQ_OBSERT_MARK = 30014;
    public static final int VIDEO_RS_OBSERT_MARK = 30015;
    //地址
    public static final String VIDEO_LOACTION_MARK = "share_location";
    public static final int VIDEO_RQ_LOCATION_MARK = 30016;
    public static final int VIDEO_RS_LOCATION_MARK = 30017;

    /**
     * 从PhoneCameraActivity传入CanSee_Activity标示
     */
    public static final String PCA_INTENT_DATA = "pca_cas";
    public static String PCA_INTETNT_CAS;
    /**
     * 通用的标示
     */
    public static final int RS_REV_MARK_PHO = 10033;
    public static final int RQ_REV_MARK_PHO = 10032;
    /**
     * 从Release_videoActivity传入CanSee_Activity标示
     */
    public static final String CONTENT = "content";  //内容标示
    public static final String GROUP = "GroupPosition";//父类坐标
    public static final String CHILDREN = "ChildrenPosition";//子类坐标
    public static final String REV_INTENT_DATA = "rca_cas";//传递参数
    public static String REV_INTETNT_CAS;//传递标示
//    ===============================================
    /**
     * 视频 —— 分享——地址
     */

    /**
     * 通用标示
     */
    public static final int REQUESTMARK_REV = 10030;
    public static final int RESULSTMARK_REV = 10031;

    public static final String  REV_LCO_POISTION = "poistion";
    public static final String REV_LCO_LOCATION = "location";

    /**
     * 视频——地址
     */
    public static final String REV_INTENT_DATAS = "rea_location";
    public static final String REV_INTENT_LOCATION = "rea_location";
    /**
     * 分享——地址
     */
    public static final String PHC_INTENT_DATAS = "phc_location";
    public static final String PHC_INTENT_LOCATION = "phc_location";

    /**
     * 地址传过的内容与坐标
     */
    public static String location;
    public static  int poistion ;


}
