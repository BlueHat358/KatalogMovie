package com.example.katalogmovie.Support;

import android.database.Cursor;

import com.example.katalogmovie.data.Favorite;

import java.util.ArrayList;

import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.ID;

public class MappingHelper {
    public static ArrayList<Favorite> mapCursorToArrayList(Cursor favoriteCursor) {
        ArrayList<Favorite> favoriteList = new ArrayList<>();
        while (favoriteCursor.moveToNext()) {
            String id = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String judul = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String rilis = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String deskripsi = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String image = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String rating = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            String vote = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(ID));
            favoriteList.add(new Favorite(id, judul, rilis, deskripsi, image, rating, vote));
        }
        return favoriteList;
    }
}
