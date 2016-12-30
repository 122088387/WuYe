package com.chaungying.address.bean;

import org.litepal.crud.DataSupport;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/30
 */

public class DataBean extends DataSupport {
    private int id;
    private int pId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
