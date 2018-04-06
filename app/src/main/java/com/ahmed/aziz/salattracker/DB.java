package com.ahmed.aziz.salattracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DB extends SQLiteOpenHelper {

    private static final String DB_NAME = "Database.db";
    private static int DB_VERSION = 1;

    private final String TABLE_NAME = "report";

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY, date DATE, FAJR INTEGER, ZUHR INTEGER, ASR INTEGER, MAGHRIB INTEGER, ISHA INTEGER ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void entry(Salat salat){
        SQLiteDatabase db = this.getWritableDatabase();
        String salatName = salat.getName();
        int option = salat.getOption();
        db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + salatName + " ) VALUES ( " + option + " ) ");
    }
}
