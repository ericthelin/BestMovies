package com.example.ethelin.bestmovies.interactors;

import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.database.delegate.MovieDetailsCursorDelegate;
import com.example.ethelin.bestmovies.models.MovieDetails;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;

public class GetMovieDetails {

    private final GetDatabase getDatabase;

    @Inject
    public GetMovieDetails(GetDatabase getDatabase) {
        this.getDatabase = getDatabase;
    }

    public Observable<MovieDetails> getForQuery(final String query) {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<MovieDetails>>() {

            @Override
            public Observable<MovieDetails> call(SQLiteDatabase database) {
                String[] whereVars = new String[]{"%" + query + "%"};

                MovieDetailsCursorDelegate cursor = new MovieDetailsCursorDelegate(database.rawQuery("SELECT * FROM " + DatabaseOpenHelper.MOVIE_DETAILS_TABLE + " WHERE " + DatabaseOpenHelper.MOVIE_DETAILS_TITLE + " LIKE ?", whereVars));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }

    public Observable<MovieDetails> all() {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<MovieDetails>>() {

            @Override
            public Observable<MovieDetails> call(SQLiteDatabase database) {
                String query = "SELECT * from " + DatabaseOpenHelper.MOVIE_DETAILS_TABLE + " ORDER BY " + DatabaseOpenHelper.MOVIE_DETAILS_IMDB_TITLE;

                MovieDetailsCursorDelegate cursor = new MovieDetailsCursorDelegate(database.rawQuery(query, null));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }

    public Observable<MovieDetails> getById(final String imdbId) {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<MovieDetails>>() {

            @Override
            public Observable<MovieDetails> call(SQLiteDatabase database) {
                String[] whereVars = new String[]{imdbId};
                String where = DatabaseOpenHelper.MOVIE_DETAILS_IMDB_ID + " = ?";

                String query = "SELECT * FROM " + DatabaseOpenHelper.MOVIE_DETAILS_TABLE + " WHERE " + where;

                MovieDetailsCursorDelegate cursor = new MovieDetailsCursorDelegate(database.rawQuery(query, whereVars));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }
}