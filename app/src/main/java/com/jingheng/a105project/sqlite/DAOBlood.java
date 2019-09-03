package com.jingheng.a105project.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jingheng.a105project.helper.DBHelper;
import com.jingheng.a105project.model.Blood;

import java.util.ArrayList;

public class DAOBlood {
    // 表格名稱
    public static final String TABLE_NAME = "Blood";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String BLOODPRESSURE_COLUMN = "bloodPressure";
    private static final String BLOODSUGER_COLUMN = "name";
    private static final String WEIGHT_COLUMN = "midPrice";
    private static final String CREATEDATE_COLUMN = "bigPrice";

    public static String CREATE_TABLE() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(BLOODPRESSURE_COLUMN + " TEXT NOT NULL, ");
        sb.append(BLOODSUGER_COLUMN + " TEXT NOT NULL, ");
        sb.append(WEIGHT_COLUMN + " TEXT NOT NULL, ");
        sb.append(CREATEDATE_COLUMN + " TEXT NOT NULL) ");
        return sb.toString();
    }

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DAOBlood(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public boolean insert(Blood item) {

        ContentValues cv = new ContentValues();

        cv.put(BLOODPRESSURE_COLUMN, item.getBloodPressure());
        cv.put(BLOODSUGER_COLUMN, item.getBloodSugar());
        cv.put(WEIGHT_COLUMN, item.getWeight());
        cv.put(CREATEDATE_COLUMN, item.getCreateDate());

        long id =  db.insert(TABLE_NAME, null, cv);
        return id > 0;
    }

    // 讀取所有記事資料
    public ArrayList<Blood> getAll() {
        ArrayList<Blood> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    private Blood getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Blood result = new Blood();

        result.setBloodPressure(cursor.getString(1));
        result.setBloodSugar(cursor.getString(2));
        result.setWeight(cursor.getString(3));
        result.setCreateDate(cursor.getString(4));

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
