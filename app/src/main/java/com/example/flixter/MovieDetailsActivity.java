package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixter.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie; // movie we want to display full screen

    // instantiate
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView popularity;
    TextView release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // retrieve with getIntent, getParcelable Extra
        // unwrap with Parcels.unwrap
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // member variables
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        popularity = findViewById(R.id.tvPopularity);
        release = findViewById(R.id.tvReleaseDate);

        // set movie info to displayed elements
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        popularity.setText(movie.getPopularity());
        release.setText(movie.getRelease());
        float voteAverage = movie.getVoteAverage().floatValue();
        if (voteAverage > 0) {
            voteAverage /= 2.0f;
        }
        rbVoteAverage.setRating(voteAverage);

    }
}