package com.lawyee.yj.friends.utils.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/8 10:23
 * @Purpose :
 */

public class locationUtils {
    // 用户名key
    public final static String KEY_NAME = "KEY_NAME";

    public final static String KEY_LEVEL = "KEY_LEVEL";


    private static locationUtils s_SharedPreUtil;

    private static location s_User = null;

    private SharedPreferences msp;
    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context)
    {
        if (s_SharedPreUtil == null)
        {
            s_SharedPreUtil = new locationUtils(context);
        }
    }
    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized locationUtils getInstance()
    {
        return s_SharedPreUtil;
    }

    public locationUtils(Context context) {
        this.msp = context.getSharedPreferences("locationUtils",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }
    public SharedPreferences getSharedPref()
    {
        return msp;
    }

    public synchronized void putUser(location user)
    {

        SharedPreferences.Editor editor = msp.edit();

        String str="";
        try {
            str = SerializableUtil.list2String(user.getLocation());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();

        s_User = user;
    }


    public synchronized location getUser()
    {

        if (s_User == null)
        {
            s_User = new location();


            //获取序列化的数据
            String str = msp.getString(SharedPreUtil.KEY_NAME, "");

            try {
                Object obj = SerializableUtil.string2List(str);
                if(obj != null){
                    s_User = (location) obj;
                }

            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return s_User;
    }
    public synchronized void DeleteUser()
    {
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");
        editor.commit();
        s_User = null;
    }

}
