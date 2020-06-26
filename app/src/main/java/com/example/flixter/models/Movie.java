package com.example.flixter.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String overview;
    String release;
    Double popularity;
    Double voteAverage;
    Integer id;

    public Movie() { }

    // constructor: jsonObject to create Movie object
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        voteAverage = jsonObject.getDouble("vote_average");
        popularity = jsonObject.getDouble("popularity");
        release = jsonObject.getString("release_date");
        id = jsonObject.getInt("id");
    }

    // convert movie JSON Array to list of Movie objects
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        if (posterPath == null) {
            Log.d("MovieImages", "posterPath is null");
            return "flicks_movie_placeholder.gif";
        }
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath); // fix this so it isn't hardcoded
    }


    public String getBackdropPath() {
        if (backdropPath == null) {
            Log.d("MovieImages", "backPath is null");
            return "flicks_backdrop_placeholder.gif";
        }
        Log.d("MovieName", title + backdropPath);
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath); // fix this so it isn't hardcoded
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public String getRelease() {
        return "Release date: " + release;
    }

    public String getPopularity() {
        return "Popularity Index: " + String.valueOf(popularity);
    }
}
