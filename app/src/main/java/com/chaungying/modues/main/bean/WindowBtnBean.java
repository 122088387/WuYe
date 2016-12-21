package com.chaungying.modues.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/11/29
 */

public class WindowBtnBean implements Serializable {

    /**
     * respCode : 1001
     * shortcuts : [{"isEnable":0,"joinAppId":104,"icoUrl":"http://192.168.1.120:80/propertyManager/uploadFile/appMenusIcos/1480397792913@2x.png","applicationId":162,"code":"APP_SHORTCUT_REPAIR","type":3,"url":null,"id":162,"pId":0,"description":"","callRole":"","name":"发布报修","icoCode":"","orders":1,"isCall":1,"callContent":"","listLayoutId":null,"roleId":38},{"isEnable":0,"joinAppId":106,"icoUrl":"http://192.168.1.120:80/propertyManager/uploadFile/appMenusIcos/1480397828850@2x.png","applicationId":163,"code":"APP_SHORTCUT_TASK","type":3,"url":null,"id":163,"pId":0,"description":"","callRole":"","name":"发布派工","icoCode":"","orders":2,"isCall":1,"callContent":"","listLayoutId":null,"roleId":38},{"isEnable":0,"joinAppId":101,"icoUrl":"http://192.168.1.120:80/propertyManager/uploadFile/appMenusIcos/1480397908724@2x.png","applicationId":164,"code":"APP_SHORTCUT_SIGNIN","type":3,"url":null,"id":164,"pId":0,"description":"","callRole":"","name":"考勤打卡","icoCode":"","orders":3,"isCall":1,"callContent":"","listLayoutId":null,"roleId":38}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * isEnable : 0
     * joinAppId : 104
     * icoUrl : http://192.168.1.120:80/propertyManager/uploadFile/appMenusIcos/1480397792913@2x.png
     * applicationId : 162
     * code : APP_SHORTCUT_REPAIR
     * type : 3
     * url : null
     * id : 162
     * pId : 0
     * description :
     * callRole :
     * name : 发布报修
     * icoCode :
     * orders : 1
     * isCall : 1
     * callContent :
     * listLayoutId : null
     * roleId : 38
     */

    private List<ShortcutsBean> shortcuts;

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

    public List<ShortcutsBean> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(List<ShortcutsBean> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public static class ShortcutsBean implements Serializable {
        private int isEnable;
        private int joinAppId; //joinAppId 关联之前的那个AppId
        private String icoUrl;
        private int applicationId;//自身的AppId
        private String code;
        private int type;
        private Object url;
        private int id;
        private int pId;
        private String description;
        private String callRole;
        private String name;
        private String icoCode;
        private int orders;
        private int isCall;
        private String callContent;
        private Object listLayoutId;
        private int roleId;

        public int getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(int isEnable) {
            this.isEnable = isEnable;
        }

        public int getJoinAppId() {
            return joinAppId;
        }

        public void setJoinAppId(int joinAppId) {
            this.joinAppId = joinAppId;
        }

        public String getIcoUrl() {
            return icoUrl;
        }

        public void setIcoUrl(String icoUrl) {
            this.icoUrl = icoUrl;
        }

        public int getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(int applicationId) {
            this.applicationId = applicationId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCallRole() {
            return callRole;
        }

        public void setCallRole(String callRole) {
            this.callRole = callRole;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcoCode() {
            return icoCode;
        }

        public void setIcoCode(String icoCode) {
            this.icoCode = icoCode;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public int getIsCall() {
            return isCall;
        }

        public void setIsCall(int isCall) {
            this.isCall = isCall;
        }

        public String getCallContent() {
            return callContent;
        }

        public void setCallContent(String callContent) {
            this.callContent = callContent;
        }

        public Object getListLayoutId() {
            return listLayoutId;
        }

        public void setListLayoutId(Object listLayoutId) {
            this.listLayoutId = listLayoutId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }
}
