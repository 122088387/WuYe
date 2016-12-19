package com.chaungying.modues.main.bean;

import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/6
 */

public class DepartmentRankBean {

    /**
     * respCode : 1001
     * data : [{"userNum":8,"userName":"王学东"},{"userNum":2,"userName":"郭兴"}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * userNum : 8
     * userName : 王学东
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Comparable<DataBean>{
        private int userNum;
        private String userName;

        public int getUserNum() {
            return userNum;
        }

        public void setUserNum(int userNum) {
            this.userNum = userNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public int compareTo(DataBean dataBean) {
            return dataBean.userNum - this.userNum;
        }
    }
}
