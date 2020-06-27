package com.example.flixter;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixter.adapters.WatchListAdapter;
import com.example.flixter.databinding.ActivityWatchListBinding;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {

    RecyclerView movies;
    ArrayList<String> allWLMov;
    WatchListAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWatchListBinding binding = ActivityWatchListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        movies = binding.movies;

        Bundle b = this.getIntent().getExtras();
        allWLMov = b.getStringArrayList("watchListAll");
        movieAdapter = new WatchListAdapter(this, allWLMov);
        movies.setAdapter(movieAdapter);
        movies.setLayoutManager(new LinearLayoutManager(this));
        

    }
}