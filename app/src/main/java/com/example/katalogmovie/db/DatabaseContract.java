package com.example.katalogmovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String TABLE_NAME = "FAVORITE_TABLE"

    public static final String AUTHORITY = "com.example.katalogmovie";
    private static final String SCHEME = "content";

    private DatabaseContract(){ }

    public static final class FavoriteColumn extends BaseColumns{

        public static final String ID = "id";
        public static final String JUDUL = "judul";
        public static final String RILIS = "rilis";
        public static final String DESKRIPSI = "deskripsi";
        public static final String IMAGE = "image";
        public static final String RATING = "rating";
        public static final String VOTE = "vote";

    }

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}