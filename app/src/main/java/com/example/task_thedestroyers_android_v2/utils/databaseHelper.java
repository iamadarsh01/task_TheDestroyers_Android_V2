package com.example.task_thedestroyers_android_v2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task_thedestroyers_android_v2.Model.toDoModel;

public class databaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "TODO DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TASK";
    private static final String COL_3 = "STATUS";





    public databaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " +TABLE_NAME+ "(ID PRIMARY KEY AUTOINCREMENT , TASK TEXT, STATUS INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);










    }

    public  void insertTask(toDoModel model){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

    }
}
