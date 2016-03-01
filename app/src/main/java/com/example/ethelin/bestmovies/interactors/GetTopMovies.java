package com.example.ethelin.bestmovies.interactors;

import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.database.delegate.TopMoviesCursorDelegate;
import com.example.ethelin.bestmovies.models.TopMovie;
import rx.Observable;
import rx.functions.Func1;

import javax.inject.Inject;

public class GetTopMovies {

    private final GetDatabase getDatabase;

    @Inject
    public GetTopMovies(GetDatabase getDatabase) {
        this.getDatabase = getDatabase;
    }

    public Observable<TopMovie> getForQuery(final String query) {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<TopMovie>>() {

            @Override
            public Observable<TopMovie> call(SQLiteDatabase database) {
                String[] whereVars = new String[]{"%" + query + "%"};

                TopMoviesCursorDelegate cursor = new TopMoviesCursorDelegate(database.rawQuery("SELECT * FROM " + DatabaseOpenHelper.TOP_MOVIE_TABLE + " WHERE " + DatabaseOpenHelper.TOP_MOVIE_TITLE + " LIKE ?", whereVars));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }

    public Observable<TopMovie> all() {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<TopMovie>>() {

            @Override
            public Observable<TopMovie> call(SQLiteDatabase database) {
                String query = "SELECT * from " + DatabaseOpenHelper.TOP_MOVIE_TABLE + " ORDER BY " + DatabaseOpenHelper.TOP_MOVIE_RANK + " DESC";

                TopMoviesCursorDelegate cursor = new TopMoviesCursorDelegate(database.rawQuery(query, null));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }

    public Observable<TopMovie> getById(final long playlistId) {
        return getDatabase.execute().flatMap(new Func1<SQLiteDatabase, Observable<TopMovie>>() {

            @Override
            public Observable<TopMovie> call(SQLiteDatabase database) {
                String[] whereVars = new String[]{String.valueOf(playlistId)};
                String where = DatabaseOpenHelper.TOP_MOVIE_IMDB_ID + " = ?";

                String query = "SELECT * FROM " + DatabaseOpenHelper.TOP_MOVIE_TABLE + " WHERE " + where;

                TopMoviesCursorDelegate cursor = new TopMoviesCursorDelegate(database.rawQuery(query, whereVars));
                try {
                    return Observable.from(cursor.getObjectList());
                } finally {
                    cursor.close();
                }
            }
        });
    }
}