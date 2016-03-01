package com.example.ethelin.bestmovies.interactors;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.models.TopMovie;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

import javax.inject.Inject;

public class StoreTopMovies {

    UpdateMovieDetails updateMovieDetails;
    @Inject
    public StoreTopMovies(UpdateMovieDetails updateMovieDetails) {
        super();
        this.updateMovieDetails = updateMovieDetails;
    }

    public Observable<Void> execute(final SQLiteDatabase database, final Observable<TopMovie> topMoviesObservable, final boolean getDetails) {
        return topMoviesObservable.map(new Func1<TopMovie, Void>() {

            @Override
            public Void call(TopMovie topMovie) {
                database.beginTransaction();
                try {
                    Timber.d("Storing " + topMovie.imdbId + " topMovie");
                    ContentValues args = new ContentValues();
                    args.put(DatabaseOpenHelper.TOP_MOVIE_IMDB_ID, topMovie.imdbId);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_IMDB_LINK, topMovie.imdbLink);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_IMDB_RATING, topMovie.imdbRating);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_IMDB_VOTES, topMovie.imdbVotes);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_POSTER, topMovie.poster);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_RANK, topMovie.rank);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_TITLE, topMovie.title);
                    args.put(DatabaseOpenHelper.TOP_MOVIE_YEAR, topMovie.year);
                    database.insertWithOnConflict(DatabaseOpenHelper.TOP_MOVIE_TABLE, null, args, SQLiteDatabase.CONFLICT_REPLACE);
                    database.yieldIfContendedSafely();
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                if (getDetails) {
                    updateMovieDetails.execute(database, topMovie.imdbId).toBlocking().single();
                }
                return null;
            }
        });
    }
}