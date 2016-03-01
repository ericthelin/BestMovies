package com.example.ethelin.bestmovies.interactors;

import android.database.sqlite.SQLiteDatabase;
import rx.Observable;

import javax.inject.Inject;

public class UpdateTopMovies {

    private final StoreTopMovies storeTopMovies;
    private final ParseTopMovies parseTopMovies;

    @Inject
    public UpdateTopMovies(StoreTopMovies storeTopMovies, ParseTopMovies parseTopMovies) {
        this.storeTopMovies = storeTopMovies;
        this.parseTopMovies = parseTopMovies;
    }

    public Observable<Void> execute(SQLiteDatabase database, boolean getDetails) {
        return storeTopMovies.execute(database, parseTopMovies.execute(), getDetails);
    }
}