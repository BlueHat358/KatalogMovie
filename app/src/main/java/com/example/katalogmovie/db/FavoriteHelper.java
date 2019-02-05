package com.example.katalogmovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.katalogmovie.data.Favorite;

import java.util.ArrayList;

import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.DESKRIPSI;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.ID;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.IMAGE;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.JUDUL;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.RATING;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.RILIS;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.VOTE;
import static com.example.katalogmovie.db.DatabaseContract.TABLE_NAME;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private final DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;

    private SQLiteDatabase database;

    private FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Favorite> query(){
        ArrayList<Favorite> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, ID + " DESC"
                , null);
        cursor.moveToFirst();
        Favorite favorite;
        if (cursor.getCount() > 0){
            do {
                favorite = new Favorite();
                favorite.setId(cursor.getString(cursor.getColumnIndexOrThrow(ID)));
                favorite.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                favorite.setRilis(cursor.getString(cursor.getColumnIndexOrThrow(RILIS)));
                favorite.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                favorite.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                favorite.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                favorite.setVote(cursor.getString(cursor.getColumnIndexOrThrow(VOTE)));

                arrayList.add(favorite);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Favorite favorite) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ID, favorite.getId());
        initialValues.put(JUDUL, favorite.getJudul());
        initialValues.put(RILIS, favorite.getRilis());
        initialValues.put(DESKRIPSI, favorite.getDeskripsi());
        initialValues.put(IMAGE, favorite.getImage());
        initialValues.put(RATING, favorite.getRating());
        initialValues.put(VOTE, favorite.getVote());

        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Favorite favorite) {
        ContentValues args = new ContentValues();
        args.put(ID, favorite.getId());
        args.put(JUDUL, favorite.getJudul());
        args.put(RILIS, favorite.getRilis());
        args.put(DESKRIPSI, favorite.getDeskripsi());
        args.put(IMAGE, favorite.getImage());
        args.put(RATING, favorite.getRating());
        args.put(VOTE, favorite.getVote());

        return database.update(DATABASE_TABLE, args, ID + "= '" + favorite.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_NAME, ID + " = '" + id + "'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , ID + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, ID + " = ?", new String[]{id});
    }
}
