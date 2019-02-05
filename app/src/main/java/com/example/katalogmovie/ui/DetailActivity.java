package com.example.katalogmovie.ui;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katalogmovie.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_RILIS = "rilis";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_VOTE = "vote";

    private TextView title, release, overview, rate, vote;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String rilis = getIntent().getStringExtra(EXTRA_RILIS);
        String deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        String image = getIntent().getStringExtra(EXTRA_IMAGE);
        String rating = getIntent().getStringExtra(EXTRA_RATING);
        String vote = getIntent().getStringExtra(EXTRA_VOTE);

        title = findViewById(R.id.tv_judul_detail);
        release = findViewById(R.id.tv_rilis_detail);
        overview = findViewById(R.id.tv_deskripsi_detail);
        rate = findViewById(R.id.tv_rating_detail);
        this.vote = findViewById(R.id.tv_vote_detail);
        this.image = findViewById(R.id.img_image_detail);

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
