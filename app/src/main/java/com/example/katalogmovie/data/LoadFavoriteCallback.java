package com.example.katalogmovie.data;

import java.util.ArrayList;

public class LoadFavoriteCallback {
    public interface LoadNotesCallback {
        void preExecute();
        void postExecute(ArrayList<Favorite> favorites);
    }
}
