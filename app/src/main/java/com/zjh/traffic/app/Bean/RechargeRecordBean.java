package com.zjh.traffic.app.Bean;

public class RechargeRecordBean {
    private String userName;
    private String rechargePlate;
    private int rechargeMoney;
    private String StringrechargeTime;

    public RechargeRecordBean() {
    }

    public RechargeRecordBean(String userName, String rechargePlate, int rechargeMoney, String stringrechargeTime) {
        this.userName = userName;
        this.rechargePlate = rechargePlate;
        this.rechargeMoney = rechargeMoney;
        StringrechargeTime = stringrechargeTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRechargePlate() {
        return rechargePlate;
    }

    public void setRechargePlate(String rechargePlate) {
        this.rechargePlate = rechargePlate;
    }

    public int getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(int rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }


    public String getStringrechargeTime() {
        return StringrechargeTime;
    }

    public void setStringrechargeTime(String stringrechargeTime) {
        StringrechargeTime = stringrechargeTime;
    }
}
