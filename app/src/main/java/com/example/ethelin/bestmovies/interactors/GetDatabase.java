package com.example.ethelin.bestmovies.interactors;

import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.Constants;
import com.example.ethelin.bestmovies.ReferenceApplication;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import timber.log.Timber;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

//@Singleton
public class GetDatabase {

    private static final String UPDATES_DATABASE = "database";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final AtomicBoolean UPDATING = new AtomicBoolean(false);

    @SuppressWarnings("FieldCanBeLocal")
    private final DatabaseOpenHelper databaseOpenHelper;

    private final UpdateTopMovies updateTopMovies;
    private final UpdateMovieDetails updateMovieDetails;

    private final StoreUpdatedAt storeUpdatedAt;
    private final GetUpdatedAt getUpdatedAt;

    @Inject
    public GetDatabase(UpdateTopMovies updateTopMovies, UpdateMovieDetails updateMovieDetails, StoreUpdatedAt storeUpdatedAt, GetUpdatedAt getUpdatedAt) {
        this.databaseOpenHelper = new DatabaseOpenHelper(ReferenceApplication.getInstance());
        this.updateTopMovies = updateTopMovies;
        this.updateMovieDetails = updateMovieDetails;

        this.storeUpdatedAt = storeUpdatedAt;
        this.getUpdatedAt = getUpdatedAt;
    }


    public Observable<SQLiteDatabase> execute() {
        return execute(false);
    }

    private String getUpdatedAt(SQLiteDatabase database) {
        String result = getUpdatedAt.execute(database, UPDATES_DATABASE);
        return result != null ? result : Constants.UPDATED_AT_DEFAULT;
    }

    private SQLiteDatabase getWritableDatabase() {
        return databaseOpenHelper.getWritableDatabase();
    }

    public Observable<SQLiteDatabase> execute(final boolean ignoreMemoryCache) {
        return Observable.defer(new Func0<Observable<SQLiteDatabase>>() {

            @Override
            public Observable<SQLiteDatabase> call() {
                while (UPDATING.get()) {
                    sleep(100);
                }
                UPDATING.set(true);

                final SQLiteDatabase database = getWritableDatabase();
                final String updatedAt = getUpdatedAt(database);

                Date now = new Date();
                Date updatedAtDate = null;
                try {
                    updatedAtDate = DATE_FORMAT.parse(updatedAt);
                } catch (ParseException e) {
                    Timber.d("Failed to parse updated date", e);
                }

                if (ignoreMemoryCache || updatedAtDate == null || (now.getTime() - updatedAtDate.getTime() > 3600000)) {// One hour ttl
//                    return Observable.merge(updateTopMovies.execute(database),
//                                             updateMovieDetails.all(database)
//                                           )
                    return updateTopMovies.execute(database, true)
                                     .toList()
                                     .map(new Func1<List<Void>, SQLiteDatabase>() {

                                         @Override
                                         public SQLiteDatabase call(List<Void> voids) {
                                             database.execSQL("ANALYZE");
                                             storeUpdatedAt.execute(database, UPDATES_DATABASE, DATE_FORMAT.format(new Date()));
                                             UPDATING.set(false);
                                             return database;
                                         }
                                     })
                                     .onErrorResumeNext(new Func1<Throwable, Observable<? extends SQLiteDatabase>>() {

                                         @Override
                                         public Observable<? extends SQLiteDatabase> call(Throwable throwable) {
                                             Timber.e(throwable, "Error updating database");
                                             UPDATING.set(false);
                                             return Observable.just(database);
                                         }
                                     });
                } else {
                    UPDATING.set(false);
                    return Observable.just(database);
                }
            }

            private void sleep(long duration) {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ignore) {
                }
            }
        });
    }
}