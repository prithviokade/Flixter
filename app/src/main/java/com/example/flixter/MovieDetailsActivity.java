package com.example.flixter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.flixter.databinding.ActivityMovieDetailsBinding;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie; // movie we want to display full screen


    // instantiate
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView popularity;
    TextView release;
    ImageView background;
    ImageButton play;
    Button add;
    Button viewbtn;

    ArrayList<Movie> watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // retrieve with getIntent, getParcelable Extra
        // unwrap with Parcels.unwrap
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // member variables
        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        rbVoteAverage = binding.rbVoteAverage;
        popularity = binding.tvPopularity;
        release = binding.tvReleaseDate;
        background = binding.ivBack;
        play = binding.playbtn;
        add = binding.addbtn;
        viewbtn = binding.viewbtn;

        watchList = new ArrayList<>();

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
            Glide.with(this).load(imageUrl).placeholder(R.drawable.flicks_movie_placeholder).into(background);
        } else {
            imageUrl = movie.getBackdropPath();
            Log.d("MovieDetails", imageUrl);
            Glide.with(this).load(imageUrl).placeholder(R.drawable.flicks_backdrop_placeholder).into(background);
        }
        /*
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = movie.getId().toString();
                if (videoID != null) {
                    Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                    Log.d("MovieDetailsSent", videoID);
                    intent.putExtra(MovieTrailerActivity.VIDEO_ID_EXTRA, videoID);
                    MovieDetailsActivity.this.startActivity(intent);
                }
            }
        }); */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = movie.getId().toString();
                if (videoID != null) {
                    Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                    Log.d("MovieDetailsSent", videoID);
                    intent.putExtra(MovieTrailerActivity.VIDEO_ID_EXTRA, videoID);
                    MovieDetailsActivity.this.startActivity(intent);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (add.getText() == "Remove from Watch List") {
                    add.setText("Add to Watch List");

                    String movieTitle = movie.getTitle();
                    if (watchList != null) {
                        watchList.remove(movie);
                        Toast.makeText(getApplicationContext(), movieTitle + " removed from Watch List", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    add.setText("Remove from Watch List");

                    String movieTitle = movie.getTitle();
                    watchList.add(movie);
                    Toast.makeText(getApplicationContext(), movieTitle + " added to Watch List", Toast.LENGTH_SHORT).show();

                }

            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this, WatchListActivity.class);
                // Bundle b = new Bundle();
                ArrayList<Parcelable> watchListP = new ArrayList<>();
                for (int i = 0; i < watchList.size(); i++) {
                    watchListP.add(Parcels.wrap(watchList.get(i)));
                }
                intent.putExtra("watchListAll", watchListP);
                //intent.putExtras(b);
                MovieDetailsActivity.this.startActivity(intent);
            }
        });

    }



}