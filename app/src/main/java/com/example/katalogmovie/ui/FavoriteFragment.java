package com.example.katalogmovie.ui;


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

import com.example.katalogmovie.HomeFragment;
import com.example.katalogmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView rv_movie;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
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
                        FragmentManager fragmentManager2 = getFragmentManager();
                        if (fragmentManager2 != null){
                            SearchFragment searchFragment = new SearchFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();

                            fragmentTransaction.replace(R.id.container, searchFragment, SearchFragment.class.getSimpleName());
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
    }

}
