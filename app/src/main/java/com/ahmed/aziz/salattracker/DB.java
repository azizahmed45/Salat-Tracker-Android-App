package com.ahmed.aziz.salattracker;

import android.content.ContentValues;
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
    private final String KEY_ID = "_id";
    private final String KEY_DATE = "DATE";
    private final String KEY_FAJR = "FAJR";
    private final String KEY_ZUHR = "ZUHR";
    private final String KEY_ASR = "ASR";
    private final String KEY_MAGHRIB = "MAGHRIB";
    private final String KEY_ISHA = "ISHA";


    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY, " + KEY_DATE + " TEXT," +
                " " + KEY_FAJR + " INTEGER DEFAULT 0, " + KEY_ZUHR + " INTEGER DEFAULT 0, " + KEY_ASR +
                " INTEGER DEFAULT 0, " + KEY_MAGHRIB + " INTEGER DEFAULT 0, " + KEY_ISHA +
                " INTEGER DEFAULT 0 ) ");
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
        String sDate = new java.sql.Date(date.getTime()).toString();

        Log.d("SQL Date", "" + sDate);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE DATE = '" + sDate + "' ",
                null);

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, sDate);
        values.put(salatName, option);

        if(cursor.getCount()<=0){
            db.insert(TABLE_NAME, null, values);
        }
        else{
            db.update(TABLE_NAME,values, KEY_DATE + " = ?", new String[] {sDate});
        }
        cursor.close();
        db.close();
    }

    public int[] getRow(Date selectedDate){
        int[] options = new int[5];
        String sDate = new java.sql.Date(selectedDate.getTime()).toString();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE DATE = '"
                + sDate +"' ", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            options[0] = cursor.getInt(2);
            options[1] = cursor.getInt(3);
            options[2] = cursor.getInt(4);
            options[3] = cursor.getInt(5);
            options[4] = cursor.getInt(6);
        }
        else{
            options[0] = 0;
            options[1] = 0;
            options[2] = 0;
            options[3] = 0;
            options[4] = 0;
        }
        cursor.close();
        db.close();
        return options;
    }
}
