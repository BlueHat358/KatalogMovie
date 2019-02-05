package com.example.katalogmovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
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

    public Favorite() {
    }

    protected Favorite(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.rilis = in.readString();
        this.deskripsi = in.readString();
        this.image = in.readString();
        this.rating = in.readString();
        this.vote = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
