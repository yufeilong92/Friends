package com.lawyee.yj.friends.vo;

import java.util.ArrayList;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/9 17:06
 * @Purpose :
 */

public class TitleDataVo {

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

        public ArrayList<TitleDataVo> add() {
            ArrayList<TitleDataVo> list = new ArrayList<>();
            TitleDataVo data = new TitleDataVo();
            add0(list,data);
            TitleDataVo data1 = new TitleDataVo();
            add1(list,data1);
            TitleDataVo data2 = new TitleDataVo();
            add2(list,data2);
            TitleDataVo data3 = new TitleDataVo();
            add3(list,data3);
            return list;
        }

        private void add3(ArrayList<TitleDataVo> list,TitleDataVo td) {
            td.setTitle("不给谁看");
            td.setContent("选中不可见的朋友");
            list.add(td);
        }

        private void add2(ArrayList<TitleDataVo> list,TitleDataVo td) {
            td.setTitle("部分可见");
            td.setContent("选中的朋友可见");
            list.add(td);
        }

        private void add1(ArrayList<TitleDataVo> list,TitleDataVo td) {
            td.setContent("尽自己可见");
            td.setTitle("私密");
            list.add(td);
        }

        private void add0(ArrayList<TitleDataVo> list,TitleDataVo td) {
            td.setTitle("公开");
            td.setContent("所有人可见");
            list.add(td);
        }


}
