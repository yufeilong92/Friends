package com.lawyee.yj.friends.utils.Utils;


import java.io.Serializable;

/**
 * @Author : Yufeilong  is Creating a porject in YFPHILPS
 * @Email : yufeilong92@163.com
 * @Time :2016/12/7 11:46
 * @Purpose :要保存的用户对象
 */

public class UserEntity implements Serializable {
    private static final long serialVersionUID = -5683263669918171030L;

    private String userName;
    // 原始密码

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private String password;

}