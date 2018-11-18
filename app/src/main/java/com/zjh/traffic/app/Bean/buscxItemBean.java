package com.zjh.traffic.app.Bean;

import com.zjh.traffic.R;

public class buscxItemBean {

    private int img_icon;
    private String bus_capacity;
    private String bus_remainingTime;
    private String bus_distance;

    public buscxItemBean() {
    }

    public buscxItemBean(String bus_capacity, String bus_distance) {
        this.img_icon = R.drawable.ic_smallbus;
        this.bus_capacity = bus_capacity;
        this.bus_distance = "距离站台" + (int) (Double.parseDouble(bus_distance) * 0.01) + "米";
        this.bus_remainingTime = (int) (((Double.parseDouble(bus_distance) * 0.00001) / 20) * 60) + "分钟到达";
    }

    public int getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(int img_icon) {
        this.img_icon = img_icon;
    }

    public String getBus_capacity() {
        return bus_capacity;
    }

    public void setBus_capacity(String bus_capacity) {
        this.bus_capacity = bus_capacity;
    }

    public String getBus_remainingTime() {
        return bus_remainingTime;
    }

    public String getBus_distance() {
        return bus_distance;
    }

    public void setBus_distance(String bus_distance) {
        this.bus_distance = "距离站台" + (int) (Double.parseDouble(bus_distance) * 0.01) + "米";
        this.bus_remainingTime = (int) (((Double.parseDouble(bus_distance) * 0.00001) / 20) * 60) + "分钟到达";
    }
}
