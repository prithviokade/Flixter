package com.example.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixter.MovieDetailsActivity;
import com.example.flixter.MovieTrailerActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List <Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // View holder is a representation of a row of our view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tmTitle;
        TextView tmOverview;
        ImageView imPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tmTitle = itemView.findViewById(R.id.tmTitle);
            tmOverview = itemView.findViewById(R.id.tmOverview);
            imPoster = itemView.findViewById(R.id.imPoster);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            tmTitle.setText(movie.getTitle());
            tmOverview.setText(movie.getOverview());
            String imageUrl;
            int radius = 30;
            int margin = 0;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // landscape
                imageUrl = movie.getBackdropPath();
                Glide.with(context).load(imageUrl).placeholder(R.drawable.flicks_backdrop_placeholder).transform(new RoundedCornersTransformation(radius, margin)).into(imPoster);
            } else { // portrait
                imageUrl = movie.getPosterPath();
                Glide.with(context).load(imageUrl).placeholder(R.drawable.flicks_movie_placeholder).transform(new RoundedCornersTransformation(radius, margin)).into(imPoster);
            }
            // Glide.with(context).load(imageUrl).transform(new RoundedCorners(radius)).into(imPoster);
            // why did I have to switch height from wrapcontent -> 200 dp for this to look right?


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { // check validity of position
                Movie movie = movies.get(position);
                Intent intent = new Intent(context, MovieDetailsActivity.class); // to do: need to understands intents better
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie)); // pass data
                context.startActivity(intent); // show activity

            }
        }
    }
}
