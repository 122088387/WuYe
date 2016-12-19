package com.chaungying.qiandao.bean;

import java.util.List;

/**
 * @author 王晓赛 or 2016/7/28
 */
public class TongJiBean {


    /**
     * respCode : 1001
     * data : [{"createTime":"2016-11-28 08:21:50","wifiName":"(null)","signInLongitude":"114.576678","departmentName":"开发部","id":328,"signInAddress":"河北省石家庄市裕华区谈固东街134号","flag":1,"signInLatitude":"38.034324","signInTime2":"08:21","signInDate":"2016-11-28","userName":"测试人员_兴","signInTime":"08:21","memberId":4614},{"createTime":"2016-11-28 08:21:25","wifiName":"(null)","signInLongitude":"114.576678","departmentName":"开发部","id":327,"signInAddress":"河北省石家庄市裕华区谈固东街134号","flag":0,"signInLatitude":"38.034328","signInTime2":"08:21","signInDate":"2016-11-28","userName":"测试人员_兴","signInTime":"08:21","memberId":4614},{"createTime":null,"wifiName":null,"signInLongitude":null,"departmentName":"开发部","id":null,"signInAddress":null,"flag":null,"signInLatitude":null,"signInTime2":"24:00","signInDate":null,"userName":"测试人员_东","signInTime":null,"memberId":null},{"createTime":null,"wifiName":null,"signInLongitude":null,"departmentName":"开发部","id":null,"signInAddress":null,"flag":null,"signInLatitude":null,"signInTime2":"24:00","signInDate":null,"userName":"测试人员_任","signInTime":null,"memberId":null},{"createTime":null,"wifiName":null,"signInLongitude":null,"departmentName":"开发部","id":null,"signInAddress":null,"flag":null,"signInLatitude":null,"signInTime2":"24:00","signInDate":null,"userName":"测试人员_志","signInTime":null,"memberId":null},{"createTime":null,"wifiName":null,"signInLongitude":null,"departmentName":"开发部","id":null,"signInAddress":null,"flag":null,"signInLatitude":null,"signInTime2":"24:00","signInDate":null,"userName":"测试人员_赛","signInTime":null,"memberId":null}]
     * respMsg : 成功！
     * isAdmin : 0
     */

    private int respCode;
    private String respMsg;
    private int isAdmin; //1不是管理员 0是管理员
    /**
     * createTime : 2016-11-28 08:21:50
     * wifiName : (null)
     * signInLongitude : 114.576678
     * departmentName : 开发部
     * id : 328
     * signInAddress : 河北省石家庄市裕华区谈固东街134号
     * flag : 1
     * signInLatitude : 38.034324
     * signInTime2 : 08:21
     * signInDate : 2016-11-28
     * userName : 测试人员_兴
     * signInTime : 08:21
     * memberId : 4614
     */

    private List<DataBean> data;

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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private boolean isShow;
        private String createTime;
        private String wifiName;
        private String signInLongitude;
        private String departmentName;
        private int id;
        private String signInAddress;
        private int flag;//0签到  1签退 2请假
        private String signInLatitude;
        private String signInTime2;
        private String signInDate;
        private String userName;
        private String signInTime;
        private int memberId;
        private int logicId;

        public int getLogicId() {
            return logicId;
        }

        public void setLogicId(int logicId) {
            this.logicId = logicId;
        }

        public void setShow(boolean isShow) {
            this.isShow = isShow;
        }

        public boolean getShow() {
            return isShow;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getWifiName() {
            return wifiName;
        }

        public void setWifiName(String wifiName) {
            this.wifiName = wifiName;
        }

        public String getSignInLongitude() {
            return signInLongitude;
        }

        public void setSignInLongitude(String signInLongitude) {
            this.signInLongitude = signInLongitude;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSignInAddress() {
            return signInAddress;
        }

        public void setSignInAddress(String signInAddress) {
            this.signInAddress = signInAddress;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getSignInLatitude() {
            return signInLatitude;
        }

        public void setSignInLatitude(String signInLatitude) {
            this.signInLatitude = signInLatitude;
        }

        public String getSignInTime2() {
            return signInTime2;
        }

        public void setSignInTime2(String signInTime2) {
            this.signInTime2 = signInTime2;
        }

        public String getSignInDate() {
            return signInDate;
        }

        public void setSignInDate(String signInDate) {
            this.signInDate = signInDate;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSignInTime() {
            return signInTime;
        }

        public void setSignInTime(String signInTime) {
            this.signInTime = signInTime;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }
    }
}
