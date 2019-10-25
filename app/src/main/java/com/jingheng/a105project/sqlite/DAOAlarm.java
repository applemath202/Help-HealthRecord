package com.jingheng.a105project.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jingheng.a105project.helper.DBHelper;
import com.jingheng.a105project.model.Alarm;


public class DAOAlarm {
    // 表格名稱
    public static final String TABLE_NAME = "Alarm";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String WAKEUP_COLUMN = "wakeup";
    private static final String SLEEP_COLUMN = "sleep";
    private static final String BREAKFAST_COLUMN = "breakfast";
    private static final String LUNCH_COLUMN = "lunch";
    private static final String DINNER_COLUMN = "dinner";
    private static final String SPORT_COLUMN = "sport";
    private static final String WEIGHT_COLUMN = "weight";


    public static String CREATE_TABLE() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(WAKEUP_COLUMN + " TEXT NOT NULL, ");
        sb.append(SLEEP_COLUMN + " TEXT NOT NULL, ");
        sb.append(BREAKFAST_COLUMN + " TEXT NOT NULL, ");
        sb.append(LUNCH_COLUMN + " TEXT NOT NULL, ");
        sb.append(DINNER_COLUMN + " TEXT NOT NULL, ");
        sb.append(SPORT_COLUMN + " TEXT NOT NULL, ");
        sb.append(WEIGHT_COLUMN + " TEXT NOT NULL) ");
        return sb.toString();
    }

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DAOAlarm(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public boolean insert(Alarm item) {

        ContentValues cv = new ContentValues();

        cv.put(WAKEUP_COLUMN, item.getWakeup());
        cv.put(SLEEP_COLUMN, item.getSleep());
        cv.put(BREAKFAST_COLUMN, item.getBreakfast());
        cv.put(LUNCH_COLUMN, item.getLunch());
        cv.put(DINNER_COLUMN, item.getDinner());
        cv.put(SPORT_COLUMN, item.getSport());
        cv.put(WEIGHT_COLUMN, item.getWeight());

        long id =  db.insert(TABLE_NAME, null, cv);
        return id > 0;
    }

    public boolean update(Alarm item) {

        ContentValues cv = new ContentValues();

        cv.put(WAKEUP_COLUMN, item.getWakeup());
        cv.put(SLEEP_COLUMN, item.getSleep());
        cv.put(BREAKFAST_COLUMN, item.getBreakfast());
        cv.put(LUNCH_COLUMN, item.getLunch());
        cv.put(DINNER_COLUMN, item.getDinner());
        cv.put(SPORT_COLUMN, item.getSport());
        cv.put(WEIGHT_COLUMN, item.getWeight());

        String where = KEY_ID + "=" + item.getAlarmId();

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public Alarm get(int id) {

        Alarm item = null;
        String where = KEY_ID + "=" + id;

        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if (result.moveToFirst()) {
            item = getRecord(result);
        }

        result.close();
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    private Alarm getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Alarm result = new Alarm();

        result.setWakeup(cursor.getString(1));
        result.setSleep(cursor.getString(2));
        result.setBreakfast(cursor.getString(3));
        result.setLunch(cursor.getString(4));
        result.setDinner(cursor.getString(5));
        result.setSport(cursor.getString(6));
        result.setWeight(cursor.getString(7));

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
