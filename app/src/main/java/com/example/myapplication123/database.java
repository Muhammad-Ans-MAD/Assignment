package com.example.myapplication123;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="yes.db";
    public static final String TABLE_NAME="Records";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="EMAIL";
    public static final String COL_4="Password";


    public database(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT,EMAIL TEXT," +
                "Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }
}

