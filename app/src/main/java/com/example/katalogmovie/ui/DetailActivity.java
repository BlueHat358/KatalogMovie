package com.example.katalogmovie.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katalogmovie.R;
import com.example.katalogmovie.data.Favorite;
import com.squareup.picasso.Picasso;

import static com.example.katalogmovie.db.DatabaseContract.CONTENT_URI;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.DESKRIPSI;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.ID;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.IMAGE;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.JUDUL;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.RATING;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.RILIS;
import static com.example.katalogmovie.db.DatabaseContract.FavoriteColumn.VOTE;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_RILIS = "rilis";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_VOTE = "vote";

    private TextView title, release, overview, rate, vote;
    ImageView image;
    Button btnFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final String id = getIntent().getStringExtra(EXTRA_ID);
        final String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        final String rilis = getIntent().getStringExtra(EXTRA_RILIS);
        final String deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE);
        final String rating = getIntent().getStringExtra(EXTRA_RATING);
        final String vote = getIntent().getStringExtra(EXTRA_VOTE);

        title = findViewById(R.id.tv_judul_detail);
        release = findViewById(R.id.tv_rilis_detail);
        overview = findViewById(R.id.tv_deskripsi_detail);
        rate = findViewById(R.id.tv_rating_detail);
        this.vote = findViewById(R.id.tv_vote_detail);
        this.image = findViewById(R.id.img_image_detail);
        btnFavorite = findViewById(R.id.btn_favorite);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues initialValues =  new ContentValues();
                initialValues.put(ID, id);
                initialValues.put(JUDUL, judul);
                initialValues.put(RILIS, rilis);
                initialValues.put(DESKRIPSI, deskripsi);
                initialValues.put(IMAGE, image);
                initialValues.put(RATING, rating);
                initialValues.put(VOTE, vote);

                getContentResolver().insert(CONTENT_URI, initialValues);

                Toast.makeText(DetailActivity.this, "Movie has been inserted", Toast.LENGTH_SHORT).show();

                //finish();
            }
        });

        title.setText(judul);
        release.setText(rilis);
        overview.setText(deskripsi);
        rate.setText(rating);
        this.vote.setText(vote);

        Picasso.with(this).load("https://image.tmdb.org/t/p/w500/" + image).into(this.image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
