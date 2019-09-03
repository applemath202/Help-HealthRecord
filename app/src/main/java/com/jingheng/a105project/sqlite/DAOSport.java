package com.jingheng.a105project.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jingheng.a105project.helper.DBHelper;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Sport;

import java.util.ArrayList;

public class DAOSport {// 表格名稱
    public static final String TABLE_NAME = "Sport";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String SPORTNAME_COLUMN = "sportName";
    private static final String SPORTTIME_COLUMN = "sportTime";
    private static final String CREATEDATE_COLUMN = "createDate";

    public static String CREATE_TABLE() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(SPORTNAME_COLUMN + " TEXT NOT NULL, ");
        sb.append(SPORTTIME_COLUMN + " TEXT NOT NULL, ");
        sb.append(CREATEDATE_COLUMN + " TEXT NOT NULL) ");
        return sb.toString();
    }

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DAOSport(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public boolean insert(Sport item) {

        ContentValues cv = new ContentValues();

        cv.put(SPORTNAME_COLUMN, item.getSportName());
        cv.put(SPORTTIME_COLUMN, item.getSportTime());
        cv.put(CREATEDATE_COLUMN, item.getCreateDate());

        long id =  db.insert(TABLE_NAME, null, cv);
        return id > 0;
    }

    // 讀取所有記事資料
    public ArrayList<Sport> getAll() {
        ArrayList<Sport> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    private Sport getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Sport result = new Sport();

        result.setSportName(cursor.getString(1));
        result.setSportTime(cursor.getString(2));
        result.setCreateDate(cursor.getString(3));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }
}
