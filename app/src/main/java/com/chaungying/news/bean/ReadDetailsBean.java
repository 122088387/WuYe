package com.chaungying.news.bean;

import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/14
 */

public class ReadDetailsBean {


    /**
     * total : 5
     * respCode : 1001
     * data : [{"activityId":57,"lookTime":"2016-12-14 15:01:54","state":0,"userName":"郭兴","pushTime":"2016-10-26 10:21:21","memberId":4614,"portrait":"http://221.238.40.119:30017/propertyInterface/uploadFile/images/headImg/4614/1481278758384.JPG"},{"activityId":57,"lookTime":"2016-12-13 15:39:51","state":0,"userName":"王晓赛","pushTime":"2016-10-26 10:21:21","memberId":4613,"portrait":"http://192.168.1.120:80/propertyInterface/uploadFile/images/headImg/4613/1481621725036.JPG"},{"activityId":57,"lookTime":"2016-12-12 10:03:52","state":0,"userName":"王学东","pushTime":"2016-10-26 10:21:21","memberId":4524,"portrait":"http://221.238.40.119:30017/propertyInterface/uploadFile/images/headImg/4524/1481278737995.JPG"},{"activityId":57,"lookTime":"","state":1,"userName":"任小雷","pushTime":"2016-10-26 10:21:21","memberId":4617,"portrait":""},{"activityId":57,"lookTime":"","state":1,"userName":"任瑞志","pushTime":"2016-10-26 10:21:21","memberId":4615,"portrait":"http://221.238.40.119:30017/propertyInterface/uploadFile/images/headImg/4615/1481259887296.jpg"}]
     * respMsg : 成功！
     */

    private int total;
    private int respCode;
    private String respMsg;
    /**
     * activityId : 57
     * lookTime : 2016-12-14 15:01:54
     * state : 0
     * userName : 郭兴
     * pushTime : 2016-10-26 10:21:21
     * memberId : 4614
     * portrait : http://221.238.40.119:30017/propertyInterface/uploadFile/images/headImg/4614/1481278758384.JPG
     */

    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int activityId;
        private String lookTime;
        private int state;
        private String userName;
        private String pushTime;
        private int memberId;
        private String portrait;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getLookTime() {
            return lookTime;
        }

        public void setLookTime(String lookTime) {
            this.lookTime = lookTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }
}
