package com.example.lostandfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String MY_DATABASE = "lostandfound.db";
    public static final int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, MY_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE lostandfound (name TEXT PRIMARY KEY, phone TEXT , description TEXT, date DATE, location TEXT, post_type TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lostandfound.db");

    }
}
