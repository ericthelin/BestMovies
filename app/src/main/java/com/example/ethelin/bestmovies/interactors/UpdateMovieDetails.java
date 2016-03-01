package com.example.ethelin.bestmovies.interactors;

import android.database.sqlite.SQLiteDatabase;
import rx.Observable;

import javax.inject.Inject;

public class UpdateMovieDetails {

    private final StoreMovieDetails storeMovieDetails;
    private final ParseMovieDetails parseMovieDetails;


    @Inject
    public UpdateMovieDetails(StoreMovieDetails storeMovieDetails, ParseMovieDetails parseMovieDetails) {
        this.storeMovieDetails = storeMovieDetails;
        this.parseMovieDetails = parseMovieDetails;
    }

    public Observable<Void> execute(SQLiteDatabase database, String imdbId) {
        return storeMovieDetails.execute(database, parseMovieDetails.execute(imdbId));
    }

    public Observable<Void> all(final SQLiteDatabase database) {
//        return getTopMovies
//                .all()
//                .map(new Func1<TopMovie, Void>() {
//                    @Override
//                    public Void call(TopMovie topMovie) {
//                        Log.w("ekt", "updating details for " + topMovie.imdbId);
//                        storeMovieDetails.execute(database, parseMovieDetails.execute(topMovie.imdbId));
//                        return null;
//                    }
//                });
        return null;
    }
}