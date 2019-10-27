package com.jingheng.a105project.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jingheng.a105project.sqlite.DAOAlarm;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAODrug;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOPee;
import com.jingheng.a105project.sqlite.DAOSport;
import com.jingheng.a105project.sqlite.DAOWater;
import com.jingheng.a105project.sqlite.DAOWeight;

public class DBHelper extends SQLiteOpenHelper {
    // 資料庫名稱
    private static final String DATABASE_NAME = "mydata.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    private static final int VERSION = 12;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    // 建構子，在一般的應用都不需要修改
    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHelper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立應用程式需要的表格
        db.execSQL(DAOBlood.CREATE_TABLE());
        db.execSQL(DAOSport.CREATE_TABLE());
        db.execSQL(DAOFood.CREATE_TABLE());
        db.execSQL(DAOWater.CREATE_TABLE());
        db.execSQL(DAOAlarm.CREATE_TABLE());
        db.execSQL(DAOBloodSugar.CREATE_TABLE());
        db.execSQL(DAOWeight.CREATE_TABLE());
        db.execSQL(DAOPee.CREATE_TABLE());
        db.execSQL(DAODrug.CREATE_TABLE());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        db.execSQL("DROP TABLE IF EXISTS " + DAOBlood.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOSport.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOFood.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOWater.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOAlarm.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOBloodSugar.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOWeight.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAOPee.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAODrug.TABLE_NAME);

        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
