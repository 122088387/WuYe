package com.chaungying.ji_xiao.bean;

import java.io.Serializable;

/**
 * @author 王晓赛 or 2016/9/20
 */
public class PerRepairDispatchBean implements Serializable{


    /**
     * respCode : 1001
     * moreOneDaySum : 5
     * finishSum : 13
     * respMsg : 成功！
     * repairNum : 76
     * unfinishedSum : 152
     * oneHourSum : 8
     * oneDaySum : 8
     * totalFinish : 165
     * thirtyMinutesSum : 7
     * taskNum : 89
     */

    private int respCode;
    private int moreOneDaySum;
    private int finishSum;
    private String respMsg;
    private int repairNum;
    private int unfinishedSum;
    private int oneHourSum;
    private int oneDaySum;
    private int totalFinish;
    private int thirtyMinutesSum;
    private int taskNum;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getMoreOneDaySum() {
        return moreOneDaySum;
    }

    public void setMoreOneDaySum(int moreOneDaySum) {
        this.moreOneDaySum = moreOneDaySum;
    }

    public int getFinishSum() {
        return finishSum;
    }

    public void setFinishSum(int finishSum) {
        this.finishSum = finishSum;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public int getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(int repairNum) {
        this.repairNum = repairNum;
    }

    public int getUnfinishedSum() {
        return unfinishedSum;
    }

    public void setUnfinishedSum(int unfinishedSum) {
        this.unfinishedSum = unfinishedSum;
    }

    public int getOneHourSum() {
        return oneHourSum;
    }

    public void setOneHourSum(int oneHourSum) {
        this.oneHourSum = oneHourSum;
    }

    public int getOneDaySum() {
        return oneDaySum;
    }

    public void setOneDaySum(int oneDaySum) {
        this.oneDaySum = oneDaySum;
    }

    public int getTotalFinish() {
        return totalFinish;
    }

    public void setTotalFinish(int totalFinish) {
        this.totalFinish = totalFinish;
    }

    public int getThirtyMinutesSum() {
        return thirtyMinutesSum;
    }

    public void setThirtyMinutesSum(int thirtyMinutesSum) {
        this.thirtyMinutesSum = thirtyMinutesSum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }
}
