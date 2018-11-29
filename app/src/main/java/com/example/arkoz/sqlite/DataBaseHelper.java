package com.example.arkoz.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_table";

    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public static final String col_3 = "PHONE";
    public static final String col_4 = "EMAIL";
    public static final String col_5 = "COUNTRY";
    public static final String col_6 = "STATE";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE INTEGER,EMAIL TEXT, COUNTRY TEXT, STATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String name, String phone, String email, String country, String state) {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, name);
        contentValues.put(col_3, phone);
        contentValues.put(col_4, email);
        contentValues.put(col_5, country);
        contentValues.put(col_6, state);

        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (res == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}

