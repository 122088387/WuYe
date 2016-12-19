package com.chaungying.ji_xiao.bean;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/12
 */

public class PieNumListBean {
    private String title;
    private int num;
    private boolean showGrayLine;

    public boolean isShowGrayLine() {
        return showGrayLine;
    }

    public void setShowGrayLine(boolean showGrayLine) {
        this.showGrayLine = showGrayLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
