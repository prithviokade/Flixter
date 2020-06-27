package com.example.flixter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixter.R;

import java.util.ArrayList;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.ViewHolder> {

    Context context;
    ArrayList<String> movies;

    public WatchListAdapter(Context context, ArrayList<String> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_wl_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        String movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // View holder is a representation of a row of our view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listTV = itemView.findViewById(R.id.listTV);
            itemView.setOnClickListener(this);
        }

        public void bind(String movie) {
            listTV.setText(movie);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
