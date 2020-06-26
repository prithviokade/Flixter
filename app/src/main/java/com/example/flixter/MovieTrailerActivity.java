package com.example.flixter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.databinding.ActivityMainBinding;
import com.example.flixter.databinding.ActivityMovieTrailerBinding;
import com.example.flixter.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    String NOW_PLAYING_URL;
    Movie movie;
    String videoId;
    String youtubeId;
    public static final String VIDEO_ID = "video_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieTrailerBinding binding = ActivityMovieTrailerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setContentView(R.layout.activity_movie_trailer);

        // resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) binding.player;

        // initialize with API key stored in secrets.xml
        playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                videoId = VIDEO_ID;
                Log.d("TrailerActivity", videoId);

                AsyncHttpClient client = new AsyncHttpClient();
                NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/" + videoId + "/videos?api_key=7f1f01fc4ac3ee3515fbc0d6cb412e90&language=en-US";
                client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.d("TrailerActivity", "onSuccessVideoId" + videoId);
                        JSONObject result = json.jsonObject;
                        try {
                            JSONArray results = result.getJSONArray("results");
                            Log.d("TrailerActivity", "Results:" +results.toString());
                            youtubeId = result.getString("key");
                        } catch (JSONException e) {
                            Log.d("TrailerActivity", "Hit JSON exception");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.d("TrailerActivity", "onFailure" + NOW_PLAYING_URL);
                    }
                });

                youTubePlayer.cueVideo(youtubeId);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }


}