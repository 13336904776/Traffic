package com.zjh.traffic.app.Bean;

public class tableListBean {
    private int TrafficLightId;
    private int redLightTime;
    private int yellowLightTime;
    private int greenLightTime;

    public tableListBean(int trafficLightId, int redLightTime, int yellowLightTime, int greenLightTime) {
        TrafficLightId = trafficLightId;
        this.redLightTime = redLightTime;
        this.yellowLightTime = yellowLightTime;
        this.greenLightTime = greenLightTime;
    }

    public int getTrafficLightId() {
        return TrafficLightId;
    }

    public void setTrafficLightId(int trafficLightId) {
        TrafficLightId = trafficLightId;
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
