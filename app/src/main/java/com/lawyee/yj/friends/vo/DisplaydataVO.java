package com.lawyee.yj.friends.vo;

import com.lawyee.yj.friends.utils.loge;
import com.lawyee.yj.friends.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Yufeilong  on YFPHILPS computer
 * @Email : yufeilong92@163.com
 * @Time :2016/11/29 15:06
 * @Purpose : listview图片加载
 */
public class DisplaydataVO {
    private static final String TAG = "DisplaydataVO";
    private String username;//用户名
    private Integer headPath;//头像路径
    private String usercontent;//用户内容

    private List<String > PicUrl;

    public List<String> getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(List<String> picUrl) {
        PicUrl = picUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHeadPath() {
        return headPath;
    }

    public void setHeadPath(Integer headPath) {
        this.headPath = headPath;
    }

    public String getUsercontent() {
        return usercontent;
    }

    public void setUsercontent(String usercontent) {
        this.usercontent = usercontent;
    }

    public List<Integer> getImgesPath() {
        return imgesPath;
    }

    public void setImgesPath(List<Integer> imgesPath) {
        this.imgesPath = imgesPath;
    }

    private List<Integer> imgesPath;

    public ArrayList<DisplaydataVO> add() {
        ArrayList<DisplaydataVO> displaydataVOs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DisplaydataVO dd = new DisplaydataVO();
            dd.setUsername("小花" + i);
            dd.setHeadPath(R.drawable.app_head);
            dd.setUsercontent("今天是" + i + "号，" + "心情很不错");
            dd.setImgesPath(StringPics());
            dd.setPicUrl(StringsPic());
            displaydataVOs.add(dd);
        }

        loge.e(TAG, "add: " + displaydataVOs.toString());
        return displaydataVOs;
    }

    private List<Integer> StringPics() {
        List<Integer> strings = new ArrayList<>();
        strings.add(R.mipmap.a);
        strings.add(R.mipmap.b);
        strings.add(R.mipmap.c);
        strings.add(R.mipmap.d);
        return strings;
    }

    private List<String> StringsPic() {
        String url = "http://a3.att.hudong.com/63/03/01300000178423121774037786421.jpg";
        String url1 = "http://pic2.ooopic.com/11/74/83/42bOOOPICca.jpg";
        String url2 = "http://img2.3lian.com/2014/f2/123/d/78.jpg";
        String url3 = "http://img.hb.aicdn.com/563cf5958a5c866e2079b8db9692c3cf40fffc521a58b-7mgXrw_fw658";
        String url4 = "http://hubei.sinaimg.cn/2013/1017/U7189P1190DT20131017110041.jpg";
        String url5 = "http://img3.redocn.com/20131005/Redocn_2013092813101732.jpg";


        ArrayList<String> list = new ArrayList<>();
        list.add(url);
        list.add(url1);
        list.add(url2);
        list.add(url3);
        list.add(url4);
        list.add(url5);

        return list;
    }

}
