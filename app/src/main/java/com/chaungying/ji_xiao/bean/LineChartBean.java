package com.chaungying.ji_xiao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/7
 */

public class LineChartBean implements Serializable {

    /**
     * respCode : 1001
     * tList : [{"data":15,"date":"2016-12-01"},{"data":0,"date":"2016-12-02"},{"data":0,"date":"2016-12-03"},{"data":0,"date":"2016-12-04"},{"data":0,"date":"2016-12-05"},{"data":0,"date":"2016-12-06"},{"data":0,"date":"2016-12-07"}]
     * rList : [{"data":20,"date":"2016-12-01"},{"data":0,"date":"2016-12-02"},{"data":0,"date":"2016-12-03"},{"data":0,"date":"2016-12-04"},{"data":0,"date":"2016-12-05"},{"data":0,"date":"2016-12-06"},{"data":0,"date":"2016-12-07"}]
     * wList : [{"data":0,"date":"2016-12-01"},{"data":0,"date":"2016-12-02"},{"data":0,"date":"2016-12-03"},{"data":0,"date":"2016-12-04"},{"data":0,"date":"2016-12-05"},{"data":0,"date":"2016-12-06"},{"data":0,"date":"2016-12-07"}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * data : 15
     * date : 2016-12-01
     */

    private List<TListBean> tList;
    /**
     * data : 20
     * date : 2016-12-01
     */

    private List<RListBean> rList;
    /**
     * data : 0
     * date : 2016-12-01
     */

    private List<WListBean> wList;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public List<TListBean> getTList() {
        return tList;
    }

    public void setTList(List<TListBean> tList) {
        this.tList = tList;
    }

    public List<RListBean> getRList() {
        return rList;
    }

    public void setRList(List<RListBean> rList) {
        this.rList = rList;
    }

    public List<WListBean> getWList() {
        return wList;
    }

    public void setWList(List<WListBean> wList) {
        this.wList = wList;
    }

    public static class TListBean implements Serializable {
        private int data;
        private String date;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class RListBean implements Serializable {
        private int data;
        private String date;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class WListBean implements Serializable {
        private int data;
        private String date;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
