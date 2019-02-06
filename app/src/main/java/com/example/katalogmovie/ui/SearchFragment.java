package com.example.katalogmovie.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.katalogmovie.HomeFragment;
import com.example.katalogmovie.R;
import com.example.katalogmovie.Support.ItemClickSupport;
import com.example.katalogmovie.adapter.MovieAdapter;
import com.example.katalogmovie.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    RecyclerView rv_movie;
    MovieAdapter adapter;
    RequestQueue requestQueue;

    private ArrayList<Movie> list;

    private TextView mTextMessage;
    private Button btnSearch;
    private EditText edtSearch;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navigationView = (BottomNavigationView) view.findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        FragmentManager fragmentManager = getFragmentManager();
                        if (fragmentManager != null){
                            HomeFragment homeFragment = new HomeFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            fragmentTransaction.replace(R.id.container, homeFragment, HomeFragment.class.getSimpleName());
                            fragmentTransaction.commit();
                        }
                        break;
                    case R.id.navigation_dashboard:
                        FragmentManager fragmentManager1 = getFragmentManager();
                        if (fragmentManager1 != null){
                            NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();

                            fragmentTransaction.replace(R.id.container, nowPlayingFragment, NowPlayingFragment.class.getSimpleName());
                            fragmentTransaction.commit();
                        }
                        break;
                    case R.id.navigation_notifications:

                        break;
                    case R.id.navigation_favorite:
                        FragmentManager fragmentManager2 = getFragmentManager();
                        if (fragmentManager2 != null){
                            FavoriteFragment favoriteFragment = new FavoriteFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();

                            fragmentTransaction.replace(R.id.container, favoriteFragment, FavoriteFragment.class.getSimpleName());
                            fragmentTransaction.commit();
                        }
                        break;
                }
                return false;
            }
        });

        rv_movie = view.findViewById(R.id.rv_Movie);
        rv_movie.setHasFixedSize(true);
        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnSearch = view.findViewById(R.id.btn_search);
        edtSearch = view.findViewById(R.id.edt_search);
        btnSearch.setOnClickListener(search_pressed);

        list = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getActivity());
    }

    View.OnClickListener search_pressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title = edtSearch.getText().toString();
            loadData(title);
        }
    };

    public void loadData(String movieTitle){
        if(movieTitle != null) {
            String key_api = "56c57613f92ff6ae9064bc04ced5da14";
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + key_api + "&language=en-US&query=" + movieTitle;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray result = response.getJSONArray("results");

                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject movieItem = result.getJSONObject(i);

                                    String id = movieItem.getString("id");
                                    String judul = movieItem.getString("title");
                                    String rilis = movieItem.getString("release_date");
                                    String deskripsi = movieItem.getString("overview");
                                    String image = movieItem.getString("poster_path");
                                    String rating = movieItem.getString("vote_average");
                                    String vote = movieItem.getString("vote_count");

                                    list.add(new Movie(id, judul, rilis, deskripsi, image, rating, vote));
                                }

                                adapter = new MovieAdapter(getActivity(), list);
                                rv_movie.setAdapter(adapter);
                                ItemClickSupport.addTo(rv_movie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                    @Override
                                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                        showSelectedMovie(list.get(position));
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            requestQueue.add(request);
        }
    }

    private void showSelectedMovie(Movie movie){
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ID, movie.getId());
        intent.putExtra(DetailActivity.EXTRA_JUDUL, movie.getJudul());
        intent.putExtra(DetailActivity.EXTRA_RILIS, movie.getRilis());
        intent.putExtra(DetailActivity.EXTRA_DESKRIPSI, movie.getDeskripsi());
        intent.putExtra(DetailActivity.EXTRA_IMAGE, movie.getImage());
        intent.putExtra(DetailActivity.EXTRA_RATING, movie.getRating());
        intent.putExtra(DetailActivity.EXTRA_VOTE, movie.getVote());
        startActivity(intent);
    }

}
