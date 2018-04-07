package com.ahmed.aziz.salattracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DB extends SQLiteOpenHelper {

    private static final String DB_NAME = "Database.db";
    private static int DB_VERSION = 1;

    private final String TABLE_NAME = "report";

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY, DATE TEXT, FAJR INTEGER DEFAULT 0, ZUHR INTEGER DEFAULT 0, ASR INTEGER DEFAULT 0, MAGHRIB INTEGER DEFAULT 0, ISHA INTEGER DEFAULT 0 ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void entry(Salat salat, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String salatName = salat.getName();
        int option = salat.getOption();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String sDate = df.format(date);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE DATE = \"" + sDate + "\"", null);

        if(cursor.getCount()<=0){
            db.execSQL("INSERT INTO " + TABLE_NAME + " ( DATE, " + salatName + " ) VALUES ( \"" + sDate+ "\" , " + option + " ) ");
        }
        else{
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + salatName + " = " + option + " WHERE DATE = \"" + sDate + "\"");
        }
        cursor.close();


    }
}
