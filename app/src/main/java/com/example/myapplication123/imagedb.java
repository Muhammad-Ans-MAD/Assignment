package com.example.myapplication123;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class imagedb extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="new_one.db";
    public static final String TABLE_NAME="stores";
    public static final String COL_1="ID";
    public static final String COL_2="images";
    public static final String COL_3="Location_Name";
    public static final String COL_4="Location_Description";
    public imagedb(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "images BLOB,Location_Name TEXT," +
                "Location_Description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }
}

