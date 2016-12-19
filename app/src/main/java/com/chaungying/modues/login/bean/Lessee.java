package com.chaungying.modues.login.bean;

import com.google.gson.annotations.Expose;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/9
 * //承租人
 */

public class Lessee {

    /**
     * id : 1
     * elementId : 6
     * createTime :
     * districtId : 1
     * principal : 12312
     * address : 33333222
     * company : 333
     * longitude :
     * latitude :
     * principalPhone : 12312
     * houseId : 15
     * buildingId : 2
     */

    @Expose
    public int id;
    @Expose
    public int elementId;
    @Expose
    public String createTime;
    @Expose
    public int districtId;
    @Expose
    public String principal;
    @Expose
    public String address;
    @Expose
    public String company;
    @Expose
    public String longitude;
    @Expose
    public String latitude;
    @Expose
    public String principalPhone;
    @Expose
    public int houseId;
    @Expose
    public int buildingId;
}
