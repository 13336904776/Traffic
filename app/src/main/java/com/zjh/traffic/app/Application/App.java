package com.zjh.traffic.app.Application;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class App extends Application {
    private static SharedPreferences sP;
    private static String userName;//用户名
    private static String password;//密码
    private static boolean firstStart;//第一次启动
    private static boolean rememberPassword;//记住密码
    private static boolean autoLogin;//自动登陆
    private static int Alerting;//账户余额告警值

    private static List<Integer> rechargeCarId;//要充值的小车id
    private static List<String> rechargePlate;//要充值的小车车牌号

    @Override
    public void onCreate() {
        super.onCreate();
        sP = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void showAlertDialog(Context context, String title, String msg,
                                       DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static String getUserName() {
        return sP.getString("userName", "");
    }

    @SuppressLint("ApplySharedPref")
    public static void setUserName(String userName) {
        sP.edit().putString("userName", userName).commit();
    }

    public static String getPassword() {
        return sP.getString("password", "");
    }

    @SuppressLint("ApplySharedPref")
    public static void setPassword(String password) {
        sP.edit().putString("password", password).commit();
    }

    public static boolean isFirstStart() {
        return sP.getBoolean("firstStart", false);
    }

    @SuppressLint("ApplySharedPref")
    public static void setFirstStart(boolean firstStart) {
        sP.edit().putBoolean("firstStart", firstStart).commit();
    }

    public static boolean isRememberPassword() {
        return sP.getBoolean("rememberPassword", false);
    }

    @SuppressLint("ApplySharedPref")
    public static void setRememberPassword(boolean rememberPassword) {
        sP.edit().putBoolean("rememberPassword", rememberPassword).commit();
    }

    public static boolean isAutoLogin() {
        return sP.getBoolean("autoLogin", false);
    }

    @SuppressLint("ApplySharedPref")
    public static void setAutoLogin(boolean autoLogin) {
        sP.edit().putBoolean("autoLogin", autoLogin).commit();
    }

    public static int getAlerting() {
        return sP.getInt("Alerting", 1);
    }

    @SuppressLint("ApplySharedPref")
    public static void setAlerting(int alerting) {
        sP.edit().putInt("Alerting", alerting).commit();
    }

    public static List<Integer> getRechargeCarId() {
        return rechargeCarId;
    }

    public static void setRechargeCarId(List<Integer> rechargeCarId) {
        App.rechargeCarId = rechargeCarId;
    }

    public static List<String> getRechargePlate() {
        return rechargePlate;
    }

    public static void setRechargePlate(List<String> rechargePlate) {
        App.rechargePlate = rechargePlate;
    }
}
