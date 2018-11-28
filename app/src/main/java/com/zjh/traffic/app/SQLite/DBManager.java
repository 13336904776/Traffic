package com.zjh.traffic.app.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjh.traffic.app.Bean.RechargeRecordBean;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(List<RechargeRecordBean> RechargeRecords) {
        db.beginTransaction();
        try {
            for (RechargeRecordBean rechargeRecord : RechargeRecords) {
                db.execSQL("INSERT INTO RechargeRecord VALUES(?,?, ?, ?)",
                        new Object[]{rechargeRecord.getUserName(), rechargeRecord.getRechargePlate(),
                                rechargeRecord.getRechargeMoney(), rechargeRecord.getStringrechargeTime()});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<RechargeRecordBean> query() {
        ArrayList<RechargeRecordBean> list = new ArrayList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            RechargeRecordBean rechargeRecord = new RechargeRecordBean();
            rechargeRecord.setUserName(c.getString(c.getColumnIndex("userName")));
            rechargeRecord.setRechargePlate(c.getString(c.getColumnIndex("rechargePlate")));
            rechargeRecord.setRechargeMoney(c.getInt(c.getColumnIndex("rechargeMoney")));
            rechargeRecord.setStringrechargeTime(c.getString(c.getColumnIndex("rechargeTime")));
            list.add(rechargeRecord);
        }
        c.close();
        return list;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM RechargeRecord", null);
        return c;
    }


    public void closeDB() {
        db.close();
    }
}