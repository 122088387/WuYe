package com.chaungying.modues.login.bean;

import com.chaungying.modues.main.bean.RoleAppsBean;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * @author 种耀淮
 */
public class HttpRequestBase implements Serializable {

    @Expose
    public int respCode;

    @Expose
    public String respMsg;

    @Expose
    public Member user;

    @Expose
    public int isAdmin;//0是管理人员 1 不是管理人员

    @Expose
    public List<RoleAppsBean> roleApps;


}
