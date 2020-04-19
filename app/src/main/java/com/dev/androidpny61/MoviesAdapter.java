package com.dev.androidpny61;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends  RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Activity activity;
    private ArrayList<Movie> movies;

    public MoviesAdapter(Activity activity, ArrayList<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(activity).inflate(R.layout.row_movies, parent, false);

        return new MoviesAdapter.MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.movieName.setText(movie.getName());

        String ratting = "Movie ratting is : "+movie.getRatting();
        holder.movieRatting.setText(ratting);

        Glide.with(activity).load(movie.getImage()).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView movieName;
        TextView movieRatting;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieBanner);
            movieName = itemView.findViewById(R.id.movieName);
            movieRatting = itemView.findViewById(R.id.movieRatting);
        }
    }
}
