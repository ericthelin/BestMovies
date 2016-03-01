package com.example.ethelin.bestmovies.interactors;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import com.example.ethelin.bestmovies.models.MovieDetails;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.List;

public class StoreMovieDetails {

    @Inject
    public StoreMovieDetails() {
        super();
    }

    public Observable<Void> execute(final SQLiteDatabase database, final Observable<MovieDetails> storeMovieObservable) {
        return storeMovieObservable.toList().map(new Func1<List<MovieDetails>, Void>() {

            @Override
            public Void call(List<MovieDetails> movieDetailsList) {
                database.beginTransaction();
                try {
                    Timber.d("Storing " + movieDetailsList.size() + " movieDetailsList");
                    for (MovieDetails movieDetails : movieDetailsList) {
                        ContentValues args = new ContentValues();
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_ID, movieDetails.imdbId);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_TITLE, movieDetails.imdbTitle);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_TITLE, movieDetails.title);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_RATING, movieDetails.imdbRating);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_IMDB_VOTES, movieDetails.imdbVotes);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_YEAR, movieDetails.year);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_RATED, movieDetails.rated);

                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_RELEASED, movieDetails.released);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_RUNTIME, movieDetails.runtime);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_DIRECTOR, movieDetails.director);
//                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_GENRE, movieDetails.genre);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_WRITER, movieDetails.writer);
//                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_ACTORS, movieDetails.actors);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_PLOT, movieDetails.plot);
//                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_LANGUAGE, movieDetails.language);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_COUNTRY, movieDetails.country);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_AWARDS, movieDetails.awards);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_POSTER, movieDetails.poster);
                        args.put(DatabaseOpenHelper.MOVIE_DETAILS_METASCORE, movieDetails.metascore);

                        database.insertWithOnConflict(DatabaseOpenHelper.MOVIE_DETAILS_TABLE, null, args, SQLiteDatabase.CONFLICT_REPLACE);
                        database.yieldIfContendedSafely();
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                return null;
            }
        });
    }
}