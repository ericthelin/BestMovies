package com.example.ethelin.bestmovies.database.delegate;

import android.database.Cursor;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.models.MovieDetails;


public class MovieDetailsCursorDelegate extends CursorDelegate<MovieDetails> {

    public MovieDetailsCursorDelegate(Cursor cursor) {
        super(cursor);
    }

    @Override
    public MovieDetails getObject() {
        String[] genre = new String[0];
//        getString(DatabaseOpenHelper.MOVIE_DETAILS_GENRE)
        String[] language = new String[0];
//        getString(DatabaseOpenHelper.MOVIE_DETAILS_LANGUAGE)
        String[] actors = new String[0];
//        getString(DatabaseOpenHelper.MOVIE_DETAILS_ACTORS)

        return new MovieDetails(
                getString(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_ID),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_TITLE),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_TITLE),
                getFloat(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_RATING),
                getInteger(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_VOTES),
                getInteger(DatabaseOpenHelper.MOVIE_DETAILS_YEAR),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_RATED),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_RELEASED),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_RUNTIME),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_DIRECTOR),
                genre,
                getString(DatabaseOpenHelper.MOVIE_DETAILS_WRITER),
                actors,
                getString(DatabaseOpenHelper.MOVIE_DETAILS_PLOT),
                language,
                getString(DatabaseOpenHelper.MOVIE_DETAILS_COUNTRY),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_AWARDS),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_POSTER),
                getString(DatabaseOpenHelper.MOVIE_DETAILS_METASCORE)
                );
    }
}