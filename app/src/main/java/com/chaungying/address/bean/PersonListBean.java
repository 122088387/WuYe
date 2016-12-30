package com.chaungying.address.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王晓赛 or 2016/8/31
 */
public class PersonListBean implements Serializable {


    /**
     * respCode : 1001
     * data : [{"position":"测试职位","iosToken":"","createTime":null,"lessee":null,"sex":0,"payPassword":"","androidToken":"","state":0,"lesseeId":0,"type":0,"password":"","departmentName":"开发部","portrait":"","id":4524,"districtName":"","districtId":0,"qrcode":"","company":"","userName":"王学东","qrcodeDir":"","departmentId":0,"qrcodeFileName":"","macId":"","roleId":0,"loginName":"15354095859"},{"position":"测试人员","iosToken":"","createTime":null,"lessee":null,"sex":0,"payPassword":"","androidToken":"","state":0,"lesseeId":0,"type":0,"password":"","departmentName":"开发部","portrait":"","id":4613,"districtName":"","districtId":0,"qrcode":"","company":"","userName":"王晓赛","qrcodeDir":"","departmentId":0,"qrcodeFileName":"","macId":"","roleId":0,"loginName":"15631188403"},{"position":"测试人员","iosToken":"","createTime":null,"lessee":null,"sex":0,"payPassword":"","androidToken":"","state":0,"lesseeId":0,"type":0,"password":"","departmentName":"开发部","portrait":"","id":4614,"districtName":"","districtId":0,"qrcode":"","company":"","userName":"郭兴","qrcodeDir":"","departmentId":0,"qrcodeFileName":"","macId":"","roleId":0,"loginName":"15731131542"},{"position":"测试人员","iosToken":"","createTime":null,"lessee":null,"sex":0,"payPassword":"","androidToken":"","state":0,"lesseeId":0,"type":0,"password":"","departmentName":"开发部","portrait":"","id":4615,"districtName":"","districtId":0,"qrcode":"","company":"","userName":"任瑞志","qrcodeDir":"","departmentId":0,"qrcodeFileName":"","macId":"","roleId":0,"loginName":"17778251120"},{"position":"物业人员","iosToken":"","createTime":null,"lessee":null,"sex":0,"payPassword":"","androidToken":"","state":0,"lesseeId":0,"type":0,"password":"","departmentName":"开发部","portrait":"","id":4617,"districtName":"","districtId":0,"qrcode":"","company":"","userName":"任小雷","qrcodeDir":"","departmentId":0,"qrcodeFileName":"","macId":"","roleId":0,"loginName":"18601049669"}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * position : 测试职位
     * iosToken :
     * createTime : null
     * lessee : null
     * sex : 0
     * payPassword :
     * androidToken :
     * state : 0
     * lesseeId : 0
     * type : 0
     * password :
     * departmentName : 开发部
     * portrait :
     * id : 4524
     * districtName :
     * districtId : 0
     * qrcode :
     * company :
     * userName : 王学东
     * qrcodeDir :
     * departmentId : 0
     * qrcodeFileName :
     * macId :
     * roleId : 0
     * loginName : 15354095859
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

    public static class DataBean implements Serializable {
        private String position;
        private String iosToken;
        private Object createTime;
        private Object lessee;
        private int sex;
        private String payPassword;
        private String androidToken;
        private int state;
        private int lesseeId;
        private int type;
        private String password;
        private String departmentName;
        private String portrait;
        private int id;
        private String districtName;
        private int districtId;
        private String qrcode;
        private String company;
        private String userName;
        private String qrcodeDir;
        private int departmentId;
        private String qrcodeFileName;
        private String macId;
        private int roleId;
        private String loginName;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getIosToken() {
            return iosToken;
        }

        public void setIosToken(String iosToken) {
            this.iosToken = iosToken;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getLessee() {
            return lessee;
        }

        public void setLessee(Object lessee) {
            this.lessee = lessee;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(String payPassword) {
            this.payPassword = payPassword;
        }

        public String getAndroidToken() {
            return androidToken;
        }

        public void setAndroidToken(String androidToken) {
            this.androidToken = androidToken;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getLesseeId() {
            return lesseeId;
        }

        public void setLesseeId(int lesseeId) {
            this.lesseeId = lesseeId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getQrcodeDir() {
            return qrcodeDir;
        }

        public void setQrcodeDir(String qrcodeDir) {
            this.qrcodeDir = qrcodeDir;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getQrcodeFileName() {
            return qrcodeFileName;
        }

        public void setQrcodeFileName(String qrcodeFileName) {
            this.qrcodeFileName = qrcodeFileName;
        }

        public String getMacId() {
            return macId;
        }

        public void setMacId(String macId) {
            this.macId = macId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
    }
}
