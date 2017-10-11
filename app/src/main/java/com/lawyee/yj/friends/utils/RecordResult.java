package com.lawyee.yj.friends.utils;

import android.content.Intent;
import android.os.Bundle;

/**
 * @Author : YFL  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/20 14:30
 * @Purpose : 视频地址utils
 */
public class RecordResult {
    private final Bundle _Bundle;
    public static final String RESULT_KEY = "qupai.edit.result";
    public static final String XTRA_PATH = "path";
    public static final String XTRA_THUMBNAIL = "thumbnail";


    public RecordResult(Intent intent) {
        _Bundle = intent.getBundleExtra(RESULT_KEY);
    }

    public String getPath() {
        return _Bundle.getString(XTRA_PATH);
    }

    public String[] getThumbnail() {
        return _Bundle.getStringArray(XTRA_THUMBNAIL);
    }


    public static final String XTRA_DURATION = "duration";
    public long getDuration() {
        return _Bundle.getLong(XTRA_DURATION);
    }
}
