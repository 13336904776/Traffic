package com.zjh.traffic.app.Bean;

public class carListBean {
    private int number;
    private int im_car;
    private String plate;
    private String name;
    private String balance;

    public carListBean(int number, int im_car, String plate, String name, String balance) {
        this.number = number;
        this.im_car = im_car;
        this.plate = plate;
        this.name = "车主:" + name;
        this.balance = "余额:" + balance + "元";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIm_car() {
        return im_car;
    }

    public void setIm_car(int im_car) {
        this.im_car = im_car;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "车主:" + name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = "余额:" + balance + "元";
    }
}
