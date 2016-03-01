package com.example.ethelin.bestmovies.interactors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.ethelin.bestmovies.database.DatabaseOpenHelper;

import javax.inject.Inject;

public class GetUpdatedAt {

    @Inject
    public GetUpdatedAt() {
        super();
    }

    public String execute(SQLiteDatabase database, String id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseOpenHelper.UPDATES_TABLE + " WHERE " + DatabaseOpenHelper.UPDATES_ID + " = ?", new String[]{id});
        //noinspection TryFinallyCanBeTryWithResources
        try {
            if (cursor.moveToFirst()) {
                int updatedAt = cursor.getColumnIndexOrThrow(DatabaseOpenHelper.UPDATES_UPDATED_AT);
                return cursor.getString(updatedAt);
            } else {
                return null;
            }
        } finally {
            cursor.close();
        }
    }
}