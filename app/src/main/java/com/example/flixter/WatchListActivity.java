package com.example.flixter;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixter.adapters.MovieAdapter;
import com.example.flixter.databinding.ActivityWatchListBinding;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {

    RecyclerView movies;
    ArrayList<Parcelable> allWLMovP;
    ArrayList<Movie> allWLMov;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWatchListBinding binding = ActivityWatchListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        allWLMov = new ArrayList<>();
        movies = binding.movies;

        allWLMovP = this.getIntent().getParcelableArrayListExtra("watchListAll");
        for (int i = 0; i < allWLMovP.size(); i++) {
            allWLMov.add((Movie) Parcels.unwrap(allWLMovP.get(i)));
        }
        movieAdapter = new MovieAdapter(this, allWLMov);
        movies.setAdapter(movieAdapter);
        movies.setLayoutManager(new LinearLayoutManager(this));
        

    }
}