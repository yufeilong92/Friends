package com.lawyee.yj.friends.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/7 11:36
 * @Purpose :保存地址
 */

public class SaveLocation {
    //将数据保存在指定文件中
    private static final String FILE_NAME = "location.txt";

    /**
     * @param context
     * @param username
     * @param pwd
     * @return boolean
     * 保存用户名和密码于文件中
     */
    public static boolean saveFile(Context context, String username, String pwd) {
/*
* content.getFilesDir()返回的路径为：/data/data/当前包名/files
* 比如下面这句代码返回的路径为：/data/data/com.itheima.rom/files
*/
        File file = new File(context.getFilesDir(), FILE_NAME);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(username + ":" + pwd);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param context
     * @return 删除用户文件
     */
    public static boolean deleteFile(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        try {
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param context
     * @return 返回用户保存的 username:pwd
     */
    public static String findUser(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
//如果文件不存在则返回 null
        if (!file.exists()) {
            return null;
        }
        String result = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            result = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
