package com.chaungying.modues.login.bean;

import com.google.gson.annotations.Expose;


/**
 * 2016/05/16
 *
 * @author zhangpanxiang
 *         用户实体
 */
public class Member {

    @Expose
    public int id;//主键ID

    @Expose
    public String macId;//mac ID

    @Expose
    public int type;//1物业人员 2 业主


    @Expose
    public String company;//单位信息

    @Expose
    public String password;//密码

    @Expose
    public String userName;//昵称

    @Expose
    public int sex;//性别 1女2男

    @Expose
    public String portrait;//头像地址

    @Expose
    public String qrcode;//二维码地址

    @Expose
    public int state;//状态 0正常

    @Expose
    public int departmentId;//部门Id

    @Expose
    public int districtId;//小区ID
    @Expose
    public int buildingId;//楼宇ID
    @Expose
    public int elementId;//楼层ID

    @Expose
    public String districtName;//小区名字

    @Expose
    public int lesseeId;//承租人Id

    @Expose
    public Lessee lessee;//承租人对象


}
