package com.example.ethelin.bestmovies.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "movies.db";
    static final int DB_VERSION = 1;// Upgrade the baked in database if you modify this

    public static final String TOP_MOVIE_TABLE = "top_movies";
    public static final String TOP_MOVIE_TITLE = "top_movie_title";

    public static final String TOP_MOVIE_RANK = "top_movie_rank";
    public static final String TOP_MOVIE_YEAR = "top_movie_year";
    public static final String TOP_MOVIE_IMDB_ID = "top_movie_imdb_id";
    public static final String TOP_MOVIE_IMDB_RATING = "top_movie_imdb_rating";
    public static final String TOP_MOVIE_IMDB_VOTES = "top_movie_imdb_votes";
    public static final String TOP_MOVIE_IMDB_LINK = "top_movie_imdb_link";
    public static final String TOP_MOVIE_POSTER = "top_movie_poster";

    public static final String MOVIE_DETAILS_TABLE = "movie_details";
    public static final String MOVIE_DETAILS_IMDB_ID = "movie_details_imdb_id";
    public static final String MOVIE_DETAILS_IMDB_TITLE = "movie_details_imdb_title";
    public static final String MOVIE_DETAILS_TITLE = "movie_details_title";
    public static final String MOVIE_DETAILS_IMDB_RATING = "movie_details_imdb_rating";
    public static final String MOVIE_DETAILS_IMDB_VOTES = "movie_details_imdb_votes";
    public static final String MOVIE_DETAILS_YEAR = "movie_details_year";
    public static final String MOVIE_DETAILS_RATED = "movie_details_rated";
    public static final String MOVIE_DETAILS_RELEASED = "movie_details_released";
    public static final String MOVIE_DETAILS_RUNTIME = "movie_details_runtime";
    public static final String MOVIE_DETAILS_DIRECTOR = "movie_details_director";
    public static final String MOVIE_DETAILS_GENRE = "movie_details_genre";
    public static final String MOVIE_DETAILS_WRITER = "movie_details_writer";
    public static final String MOVIE_DETAILS_ACTORS = "movie_details_actors";
    public static final String MOVIE_DETAILS_PLOT = "movie_details_plot";
    public static final String MOVIE_DETAILS_LANGUAGE = "movie_details_language";
    public static final String MOVIE_DETAILS_COUNTRY = "movie_details_country";
    public static final String MOVIE_DETAILS_AWARDS = "movie_details_awards";
    public static final String MOVIE_DETAILS_POSTER = "movie_details_poster";
    public static final String MOVIE_DETAILS_METASCORE = "movie_details_metascore";


    public static final String UPDATES_TABLE = "updates";
    public static final String UPDATES_UPDATED_AT = "updated_at";
    public static final String UPDATES_ID = "id";


    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabaseFromScratch(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTablesFromDatabase(db);
        createDatabaseFromScratch(db);
    }

    private void createDatabaseFromScratch(SQLiteDatabase db) {
        Timber.d("Creating database: " + DB_NAME);


        createTopMoviesTable(db);
        createMovieDetailsTable(db);
        createUpdatesTable(db);

        Timber.d("Finished creating database: " + DB_NAME);
    }

    private void createUpdatesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UPDATES_TABLE + "(" + UPDATES_ID + " TEXT PRIMARY KEY, " + UPDATES_UPDATED_AT + " TEXT)");
    }

    private void createTopMoviesTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TOP_MOVIE_TABLE + "(" +
                   TOP_MOVIE_IMDB_ID + " TEXT PRIMARY KEY, " +
                   TOP_MOVIE_TITLE + " TEXT, " +
                   TOP_MOVIE_RANK + " INTEGER, " +
                   TOP_MOVIE_YEAR + " INTEGER, " +
                   TOP_MOVIE_IMDB_RATING + " TEXT, " +
                   TOP_MOVIE_IMDB_VOTES + " INTEGER, " +
                   TOP_MOVIE_IMDB_LINK + " TEXT, " +
                   TOP_MOVIE_POSTER + " TEXT)");
    }

    private void createMovieDetailsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MOVIE_DETAILS_TABLE + "(" +
                   MOVIE_DETAILS_IMDB_ID + " TEXT PRIMARY KEY, " +
                   MOVIE_DETAILS_TITLE + " TEXT, " +
                   MOVIE_DETAILS_RATED + " TEXT, " +
                   MOVIE_DETAILS_YEAR + " INTEGER, " +
                   MOVIE_DETAILS_IMDB_RATING + " TEXT, " +
                   MOVIE_DETAILS_IMDB_VOTES + " INTEGER, " +
                   MOVIE_DETAILS_IMDB_TITLE + " TEXT, " +
                   MOVIE_DETAILS_POSTER + " TEXT, " +
                   MOVIE_DETAILS_RELEASED + " TEXT, " +
                   MOVIE_DETAILS_RUNTIME + " TEXT, " +
                   MOVIE_DETAILS_DIRECTOR + " TEXT, " +
                   MOVIE_DETAILS_GENRE + " TEXT, " +
                   MOVIE_DETAILS_WRITER + " TEXT, " +
                   MOVIE_DETAILS_ACTORS + " TEXT, " +
                   MOVIE_DETAILS_PLOT + " TEXT, " +
                   MOVIE_DETAILS_LANGUAGE + " TEXT, " +
                   MOVIE_DETAILS_COUNTRY + " TEXT, " +
                   MOVIE_DETAILS_AWARDS + " TEXT, " +
                   MOVIE_DETAILS_METASCORE + " TEXT)");
    }

    private void dropAllTablesFromDatabase(SQLiteDatabase database) {
        List<String> tables = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        //noinspection TryFinallyCanBeTryWithResources
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    tables.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
        }

        for (String table : tables) {
            if (table.equals("sqlite_sequence")) {
                continue;
            }
            database.execSQL("DROP TABLE IF EXISTS " + table);
        }
    }
}