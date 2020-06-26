package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie; // movie we want to display full screen


    // instantiate
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView popularity;
    TextView release;
    ImageView background;

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
        background = findViewById(R.id.ivBack);

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
        String imageUrl;
        Log.d("MovieDetailsHere", Integer.toString(this.getResources().getConfiguration().orientation));
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movie.getPosterPath();
            Log.d("MovieDetails", imageUrl);
            if (background == null) {
                Log.d("MovieDetailsHere", "noooo");
            }
            Glide.with(this).load(imageUrl).placeholder(R.drawable.flicks_movie_placeholder).into(background);
        } else {
            imageUrl = movie.getBackdropPath();
            Log.d("MovieDetails", imageUrl);
            Glide.with(this).load(imageUrl).placeholder(R.drawable.flicks_backdrop_placeholder).into(background);
        }


    }
}