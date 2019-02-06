package com.example.katalogmovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    String id, judul, rilis, deskripsi, image, rating, vote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.rilis);
        dest.writeString(this.deskripsi);
        dest.writeString(this.image);
        dest.writeString(this.rating);
        dest.writeString(this.vote);
    }

    public Movie() {
    }

    public Movie(String id, String judul, String rilis, String deskripsi, String image, String rating, String vote) {
        this.id = id;
        this.judul = judul;
        this.rilis = rilis;
        this.deskripsi = deskripsi;
        this.image = image;
        this.rating = rating;
        this.vote = vote;
    }

    protected Movie(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.rilis = in.readString();
        this.deskripsi = in.readString();
        this.image = in.readString();
        this.rating = in.readString();
        this.vote = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
