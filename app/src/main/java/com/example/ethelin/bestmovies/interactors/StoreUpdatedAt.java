package com.example.ethelin.bestmovies.interactors;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;

import javax.inject.Inject;

public class StoreUpdatedAt {

    @Inject
    public StoreUpdatedAt() {
        super();
    }

    public void execute(SQLiteDatabase database, String id, String updatedAt) {
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.UPDATES_UPDATED_AT, updatedAt);
        values.put(DatabaseOpenHelper.UPDATES_ID, id);
        database.insertWithOnConflict(DatabaseOpenHelper.UPDATES_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
}