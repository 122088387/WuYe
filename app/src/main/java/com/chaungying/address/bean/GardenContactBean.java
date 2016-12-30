package com.chaungying.address.bean;

import java.util.List;

/**
 * @author 王晓赛 or 2016/8/26
 */
public class GardenContactBean {


    /**
     * respCode : 1001
     * data : [{"id":1,"pId":23,"name":"未来科技城"},{"id":25,"pId":1,"name":"客服部"},{"id":26,"pId":1,"name":"市场部"},{"id":27,"pId":1,"name":"开发部"}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * id : 1
     * pId : 23
     * name : 未来科技城
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
}
