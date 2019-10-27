package com.jingheng.a105project.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jingheng.a105project.helper.DBHelper;
import com.jingheng.a105project.model.Drug;

import java.util.ArrayList;

public class DAODrug {
    // 表格名稱
    public static final String TABLE_NAME = "Drug";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String DRUGNAME_COLUMN = "Drugname";
    private static final String DRUGTIME_COLUMN = "Drugtime";

    public static String CREATE_TABLE() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(DRUGNAME_COLUMN + " TEXT NOT NULL, ");
        sb.append(DRUGTIME_COLUMN + " TEXT NOT NULL) ");
        return sb.toString();
    }

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DAODrug(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public boolean insert(Drug item) {
        ContentValues cv = new ContentValues();

        cv.put(DRUGNAME_COLUMN, item.getDrugName());
        cv.put(DRUGTIME_COLUMN, item.getDrugTime());

        long id = db.insert(TABLE_NAME, null, cv);
        return id > 0;
    }

    public boolean update(Drug item) {
        ContentValues cv = new ContentValues();

        cv.put(DRUGNAME_COLUMN, item.getDrugName());
        cv.put(DRUGTIME_COLUMN, item.getDrugTime());

        String where = KEY_ID + "=" + item.getDrugId();

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public Drug get(int id) {

        Drug item = null;
        String where = KEY_ID + "=" + id;

        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if (result.moveToFirst()) {
            item = getRecord(result);
        }

        result.close();
        return item;
    }

    // 讀取所有記事資料
    public ArrayList<Drug> getAll() {
        ArrayList<Drug> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    private Drug getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Drug result = new Drug();

        result.setDrugId((int) cursor.getLong(0));
        result.setDrugName(cursor.getString(1));
        result.setDrugTime(cursor.getString(2));

        // 回傳結果
        return result;
    }

    public boolean delete(int id) {
        String where = KEY_ID + "=" + id;

        return db.delete(TABLE_NAME, where, null) > 0;
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
