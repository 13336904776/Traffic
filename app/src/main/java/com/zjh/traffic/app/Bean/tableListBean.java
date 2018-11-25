package com.zjh.traffic.app.Bean;

public class tableListBean {
    private int roadId;
    private int redLightTime;
    private int yellowLightTime;
    private int greenLightTime;

    public tableListBean(int roadId, int redLightTime, int yellowLightTime, int greenLightTime) {
        this.roadId = roadId;
        this.redLightTime = redLightTime;
        this.yellowLightTime = yellowLightTime;
        this.greenLightTime = greenLightTime;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public int getRedLightTime() {
        return redLightTime;
    }

    public void setRedLightTime(int redLightTime) {
        this.redLightTime = redLightTime;
    }

    public int getYellowLightTime() {
        return yellowLightTime;
    }

    public void setYellowLightTime(int yellowLightTime) {
        this.yellowLightTime = yellowLightTime;
    }

    public int getGreenLightTime() {
        return greenLightTime;
    }

    public void setGreenLightTime(int greenLightTime) {
        this.greenLightTime = greenLightTime;
    }
}
