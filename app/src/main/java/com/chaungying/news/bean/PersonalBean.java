package com.chaungying.news.bean;

import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/15
 */

public class PersonalBean {

    /**
     * respCode : 1001
     * roles : [{"id":22,"name":"移动管理员"},{"id":23,"name":"会议服务"},{"id":24,"name":"工程配电"},{"id":26,"name":"客服管家"},{"id":27,"name":"保洁人员"},{"id":28,"name":"中控"},{"id":29,"name":"讲解人员"},{"id":30,"name":"客服核算"},{"id":31,"name":"经理"},{"id":32,"name":"副经理"},{"id":33,"name":"园区业主"},{"id":34,"name":"餐饮"},{"id":36,"name":"展厅讲解"},{"id":37,"name":"设备管理"},{"id":38,"name":"测试人员"},{"id":39,"name":"秩序维护"},{"id":40,"name":"前台"},{"id":41,"name":"会服管理"},{"id":42,"name":"秩序维护管理"},{"id":43,"name":"餐饮服务管理"},{"id":44,"name":"工程维修"}]
     * departments : [{"id":2,"name":"开发部"},{"id":3,"name":"销售部"},{"id":4,"name":"公关部"},{"id":5,"name":"市场部"}]
     * respMsg : 成功！
     * members : [{"id":4524,"userName":"王学东","loginName":"15354095859"},{"id":4613,"userName":"王晓赛","loginName":"15631188403"},{"id":4614,"userName":"郭兴","loginName":"15731131542"},{"id":4615,"userName":"任瑞志","loginName":"17778251120"},{"id":4617,"userName":"任小雷","loginName":"18601049669"},{"id":4619,"userName":"hehe","loginName":"17777777777"}]
     */

    private int respCode;
    private String respMsg;
    /**
     * id : 22
     * name : 移动管理员
     */

    private List<RolesBean> roles;
    /**
     * id : 2
     * name : 开发部
     */

    private List<DepartmentsBean> departments;
    /**
     * id : 4524
     * userName : 王学东
     * loginName : 15354095859
     */

    private List<MembersBean> members;

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

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public List<DepartmentsBean> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentsBean> departments) {
        this.departments = departments;
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class RolesBean {
        private int id;
        private String name;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DepartmentsBean {
        private int id;
        private String name;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class MembersBean {
        private int id;
        private String userName;
        private String loginName;
        private boolean isCheck;
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
    }
}
