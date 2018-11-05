package com.zjh.traffic.app.Application;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class App extends Application {
    private static SharedPreferences sP;
    private static String userName;//用户名
    private static String password;//密码
    private static boolean firstStart;//第一次启动
    private static boolean rememberPassword;//记住密码
    private static boolean autoLogin;//自动登陆
    private static int Alerting;//账户余额告警值

    @Override
    public void onCreate() {
        super.onCreate();
        sP = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void showAlertDialog(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
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
}
