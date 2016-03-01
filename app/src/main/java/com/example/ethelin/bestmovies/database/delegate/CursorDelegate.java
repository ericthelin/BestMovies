package com.example.ethelin.bestmovies.database.delegate;

import android.database.Cursor;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CursorDelegate<PT> {

    private final Map<String, Integer> columnNameToIndexMap = new HashMap<>();

    protected Cursor cursor;

    public CursorDelegate(Cursor cursor) {
        if (cursor == null) {
            throw new NullPointerException("Cursor cannot be null.");
        }
        this.cursor = cursor;
        cursor.moveToFirst();
    }

    public abstract PT getObject();

    public List<PT> getObjectList() {
        List<PT> objectList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                try {
                    PT object = getObject();
                    objectList.add(object);
                } catch (Exception e) {
                    Timber.d("catching the random bug where object creation fails", e);
                }
            } while (cursor.moveToNext());
        }
        return objectList;
    }

    public PT getSingle() {
        if (cursor.moveToFirst()) {
            return getObject();
        }
        return null;
    }

    public void close() {
        cursor.close();
    }

    public int getCount() {
        return cursor.getCount();
    }

    protected Integer getIndex(String columnName) {
        if (!columnNameToIndexMap.containsKey(columnName)) {
            columnNameToIndexMap.put(columnName, cursor.getColumnIndex(columnName));
        }
        return columnNameToIndexMap.get(columnName);
    }

    protected String getString(String columnName) {
        Integer index = getIndex(columnName);
        if (index == null || index == -1) {
            Timber.w("Attempted to retrieve non-existent String column: " + columnName);
            return null;
        }
        return cursor.getString(index);
    }

    protected Integer getInteger(String columnName) {
        Integer index = getIndex(columnName);
        if (index == null || index == -1) {
            return null;
        }
        return cursor.getInt(index);
    }

    protected Long getLong(String columnName) {
        Integer index = getIndex(columnName);
        if (index == null || index == -1) {
            Timber.w("Attempted to retrieve non-existent Long column: " + columnName);
            return null;
        }
        return cursor.getLong(index);
    }


    protected Float getFloat(String columnName) {
        Integer index = getIndex(columnName);
        if (index == null || index == -1) {
            Timber.w("Attempted to retrieve non-existent Float column: " + columnName);
            return null;
        }
        return cursor.getFloat(index);
    }

    protected Boolean getBoolean(String columnName, Boolean defaultValue) {
        Integer index = getIndex(columnName);
        if (cursor.isNull(index)) {
            return defaultValue;
        }
        return cursor.getInt(index) == 1;
    }

    protected Boolean getBoolean(String columnName) {
        return getBoolean(columnName, null);
    }

    protected byte[] getBlob(String columnName) {
        Integer index = getIndex(columnName);
        if (index == null || index == -1) {
            Timber.w("Attempted to retrieve non-existent Blob column: " + columnName);
            return null;
        }
        return cursor.getBlob(index);
    }

    public void getColumnNames() {
        String names = "";
        for (String name : cursor.getColumnNames()) {
            names = names + name + " ";
        }
    }

    protected Long asLong(Integer i) {
        if (i == null) {
            return null;
        }
        return i.longValue();
    }
}