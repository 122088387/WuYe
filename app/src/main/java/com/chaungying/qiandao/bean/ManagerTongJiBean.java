package com.chaungying.qiandao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/28
 */

public class ManagerTongJiBean implements Serializable {


    /**
     * totalPeople : 5
     * absenteeismPeople : 2
     * respCode : 1001
     * signInPeople : 0
     * earlyPeople : 3
     * data : [{"userName":"测试人员_志","counts":"1.71666667"},{"userName":"测试人员_兴","counts":"0.40000000"},{"userName":"测试人员_东","counts":"0.00000000"},{"userName":"测试人员_赛","counts":"0.00000000"},{"userName":"测试人员_任","counts":"0.00000000"}]
     * respMsg : 成功！
     * tardinessPeople : 2
     */

    private int totalPeople;
    private int absenteeismPeople;
    private int respCode;
    private int signInPeople;
    private int earlyPeople;
    private String respMsg;
    private int tardinessPeople;
    /**
     * userName : 测试人员_志
     * counts : 1.71666667
     */

    private List<DataBean> data;

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public int getAbsenteeismPeople() {
        return absenteeismPeople;
    }

    public void setAbsenteeismPeople(int absenteeismPeople) {
        this.absenteeismPeople = absenteeismPeople;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getSignInPeople() {
        return signInPeople;
    }

    public void setSignInPeople(int signInPeople) {
        this.signInPeople = signInPeople;
    }

    public int getEarlyPeople() {
        return earlyPeople;
    }

    public void setEarlyPeople(int earlyPeople) {
        this.earlyPeople = earlyPeople;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public int getTardinessPeople() {
        return tardinessPeople;
    }

    public void setTardinessPeople(int tardinessPeople) {
        this.tardinessPeople = tardinessPeople;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable, Comparable<DataBean> {
        private String userName;
        private String counts;
        private int userId;
        private String portrait;//头像地址

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCounts() {
            return counts;
        }

        public void setCounts(String counts) {
            this.counts = counts;
        }

        @Override
        public int compareTo(DataBean dataBean) {
            return (int) (Double.parseDouble(dataBean.getCounts()) / 60 * 10000) - (int) (Double.parseDouble(dataBean.getCounts()) / 60 * 10000);
        }
    }
}
