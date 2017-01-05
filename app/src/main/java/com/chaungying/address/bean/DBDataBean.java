package com.chaungying.address.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/30
 */

public class DBDataBean extends DataSupport {
    private int mId;
    private int pId;
    private String name;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
