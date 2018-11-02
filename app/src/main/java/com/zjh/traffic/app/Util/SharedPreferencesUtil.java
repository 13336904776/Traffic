package com.zjh.traffic.app.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private String mTAG = "appDate";
    // 创建一个写入器
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SharedPreferencesUtil mSharedPreferencesUtil;

    // 构造方法
    public SharedPreferencesUtil(Context context) {
        mPreferences = context.getSharedPreferences(mTAG, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    // 单例模式
    public static SharedPreferencesUtil getInstance(Context context) {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = new SharedPreferencesUtil(context);
        }
        return mSharedPreferencesUtil;
    }

    // 存入数据
    public void putSP(String key, Object value) {
        String type = value.getClass().getSimpleName();
        if ("String".equals(type)) {
            mEditor.putString(key, (String) value);
        } else if ("Integer".equals(type)) {
            mEditor.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            mEditor.putBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            mEditor.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            mEditor.putLong(key, (Long) value);
        }
        mEditor.commit();
    }

    // 获取数据
    public Object getSP(String key, Object value) {
        String type = value.getClass().getSimpleName();
        if ("String".equals(type)) {
            return mPreferences.getString(key, (String) value);
        } else if ("Integer".equals(type)) {
            return mPreferences.getInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            return mPreferences.getBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            return mPreferences.getFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            return mPreferences.getLong(key, (Long) value);
        }
        return null;
    }

    // 移除数据
    public void removeSP(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}