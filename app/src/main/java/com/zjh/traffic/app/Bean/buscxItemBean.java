package com.zjh.traffic.app.Bean;

public class buscxItemBean {

    private int img_icon;
    private String bus_capacity;

    public buscxItemBean() {
    }

    public buscxItemBean(int img_icon, String bus_capacity) {
        this.img_icon = img_icon;
        this.bus_capacity = bus_capacity;
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
}
