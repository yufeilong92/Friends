package com.lawyee.yj.friends.utils;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/11/30 16:34
 * @Purpose :loge工具类
 */

public class loge {
    private static final int VERBOSE=0;
    private static final int INFO=1;
    private static final int ERROR=2;
    private static final int DEBUG=3;
    private static final int WARN=4;
    public static int logLevel = VERBOSE;
    public static void i(String tag, String msg) {
        if (logLevel <= INFO)
            android.util.Log.i(tag, msg);
    }
    public static void e(String tag, String msg) {
        if (logLevel <= ERROR)
            android.util.Log.e(tag, msg);
    }
    public static void d(String tag, String msg) {
        if (logLevel <= DEBUG)
            android.util.Log.d(tag, msg);
    }
    public static void v(String tag, String msg) {
        if (logLevel <= VERBOSE)
            android.util.Log.v(tag, msg);
    }
    public static void w(String tag, String msg) {
        if (logLevel <= WARN)
            android.util.Log.w(tag, msg);
    }

}
