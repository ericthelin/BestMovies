package com.example.ethelin.bestmovies.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ethelin.bestmovies.R;
import com.example.ethelin.bestmovies.models.TopMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethelin on 2/23/16.
 */
public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MovieViewHolder> {
    private static Context context;
    private List<TopMovie> topMovieList;

    public TopMoviesAdapter(Context context) {
        TopMoviesAdapter.context = context;
        topMovieList = new ArrayList<>();
    }

    public void setMovies(List<TopMovie> movies) {
        topMovieList = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return topMovieList.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(topMovieList.get(i));
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, final int pos) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_movie_list, viewGroup, false);

        MovieViewHolder vh = new MovieViewHolder(itemView);
        itemView.setOnClickListener(vh);

        return vh;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rank;
        public TextView title;
        public TextView imdbRating;
        public TextView year;
        public ImageView poster;
        public TopMovie topMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            rank = (TextView) itemView.findViewById(R.id.rank);
            title = (TextView) itemView.findViewById(R.id.title);
            imdbRating = (TextView) itemView.findViewById(R.id.imdbRating);
            year = (TextView) itemView.findViewById(R.id.year);
            poster = (ImageView) itemView.findViewById(R.id.poster);

        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_IMDB_ID, topMovie.imdbId);
            context.startActivity(i);
        }

        public void bind(TopMovie topMovie) {
            this.topMovie = topMovie;
            rank.setText(String.valueOf(topMovie.rank));
            title.setText(topMovie.title);
            imdbRating.setText(String.valueOf(topMovie.imdbRating));
            year.setText(String.valueOf(topMovie.year));
            Picasso.with(context).load(topMovie.poster).into(poster);
        }
    }
}
