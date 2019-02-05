package com.example.katalogmovie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import static com.example.katalogmovie.db.DatabaseContract.TABLE_NAME;
import static com.example.katalogmovie.db.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favorite.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String CREATE_FAVORITE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            FavoriteColumn.ID + "TEXT PRIMARY KEY, " +
            FavoriteColumn.JUDUL + "TEXT, " +
            FavoriteColumn.RILIS + "TEXT, " +
            FavoriteColumn.DESKRIPSI + "TEXT, " +
            FavoriteColumn.IMAGE + "TEXT, " +
            FavoriteColumn.RATING + "TEXT, " +
            FavoriteColumn.VOTE + "TEXT);";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF IS EXISTS ", TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
