package com.example.ethelin.bestmovies.database.delegate;

import android.database.Cursor;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.models.TopMovie;


public class TopMoviesCursorDelegate extends CursorDelegate<TopMovie> {

    public TopMoviesCursorDelegate(Cursor cursor) {
        super(cursor);
    }

    @Override
    public TopMovie getObject() {
        return new TopMovie(
                getInteger(DatabaseOpenHelper.TOP_MOVIE_RANK),
                getString(DatabaseOpenHelper.TOP_MOVIE_TITLE),
                getInteger(DatabaseOpenHelper.TOP_MOVIE_YEAR),
                getString(DatabaseOpenHelper.TOP_MOVIE_IMDB_ID),
                getFloat(DatabaseOpenHelper.TOP_MOVIE_IMDB_RATING),
                getInteger(DatabaseOpenHelper.TOP_MOVIE_IMDB_VOTES),
                getString(DatabaseOpenHelper.TOP_MOVIE_IMDB_LINK),
                getString(DatabaseOpenHelper.TOP_MOVIE_POSTER));
    }
}