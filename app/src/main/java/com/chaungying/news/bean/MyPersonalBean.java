package com.chaungying.news.bean;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/16
 * <p>
 * 自定义的人员实体类
 */

public class MyPersonalBean {
    private String name;
    private int id;
    private boolean isCheck;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
