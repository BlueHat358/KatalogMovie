package com.example.katalogmovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katalogmovie.R;
import com.example.katalogmovie.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.MovieList = list;
    }

    private ArrayList<Movie> MovieList;

    public void clearData(){
        MovieList.clear();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_model, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movieItem = MovieList.get(i);

        String judul = movieItem.getJudul();
        String rilis = movieItem.getRilis();
        String deskripsi = movieItem.getDeskripsi();
        String image = movieItem.getImage();

        movieViewHolder.judul.setText(judul);
        movieViewHolder.rilis.setText(rilis);
        movieViewHolder.deskripsi.setText(deskripsi);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/" + image).fit().centerInside().into(movieViewHolder.gambar);
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView judul, rilis, deskripsi;
        ImageView gambar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.tv_judul);
            rilis = itemView.findViewById(R.id.tv_rilis);
            deskripsi = itemView.findViewById(R.id.tv_deskripsi);
            gambar = itemView.findViewById(R.id.img_image);
        }
    }
}
