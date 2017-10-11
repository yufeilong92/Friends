package com.lawyee.yj.friends.vo;

import java.util.ArrayList;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/9 11:58
 * @Purpose : 谁可见界面数据
 */

public class TitleData {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public ArrayList<TitleData> add() {
        ArrayList<TitleData> list = new ArrayList<>();
        TitleData data = new TitleData();
        add0(list,data);
        TitleData data1 = new TitleData();
        add1(list,data1);
        TitleData data2 = new TitleData();
        add2(list,data2);
        TitleData data3 = new TitleData();
        add3(list,data3);
        return list;
    }

    private void add3(ArrayList<TitleData> list,TitleData td) {
        td.setTitle("不给谁看");
        td.setContent("选中不可见的朋友");
        list.add(td);
    }

    private void add2(ArrayList<TitleData> list,TitleData td) {
        td.setTitle("部分可见");
        td.setContent("选中的朋友可见");
        list.add(td);
    }

    private void add1(ArrayList<TitleData> list,TitleData td) {
        td.setContent("尽自己可见");
        td.setTitle("私密");
        list.add(td);
    }

    private void add0(ArrayList<TitleData> list,TitleData td) {
        td.setTitle("公开");
        td.setContent("所有人可见");
        list.add(td);
    }

}
