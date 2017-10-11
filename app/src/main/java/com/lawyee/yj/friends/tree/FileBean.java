package com.lawyee.yj.friends.tree;

import com.lawyee.yj.friends.vo.TitleData;
import com.zhy.tree.bean.TreeNodeId;
import com.zhy.tree.bean.TreeNodeLabel;
import com.zhy.tree.bean.TreeNodePid;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/9 11:14
 * @Purpose :
 */

public class FileBean {

    @TreeNodeId
    private int _id;
    @TreeNodePid
    private int parentId;
    @TreeNodeLabel
    private String name;
    private long length;
    private TitleData  data;
    public FileBean(int _id, int parentId, TitleData data)
    {
        super();
        this._id = _id;
        this.parentId = parentId;
        this.data =data;

    }
    public FileBean(int _id, int parentId, String data)
    {
        super();
        this._id = _id;
        this.parentId = parentId;
        this.name =data;

    }

}
