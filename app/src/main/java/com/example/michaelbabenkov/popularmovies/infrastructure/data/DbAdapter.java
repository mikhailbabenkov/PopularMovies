package com.example.michaelbabenkov.popularmovies.infrastructure.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michaelbabenkov on 15/07/15.
 */
public class DbAdapter extends SQLiteOpenHelper {

    private static final String DB_NAME = "movies.db";
    private static final int VERSION = 1;

    public DbAdapter(final Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!db.isReadOnly()) {
            for(final String table : DATABASE_SCRIPT) {
                db.execSQL(table);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String MOVIE_DLL = "CREATE TABLE " +
            MovieContract.MovieTable.TABLE_NAME + " (" +
            MovieContract.MovieTable.COLUMN_ID + " TEXT PRIMARY KEY , " +
            MovieContract.MovieTable.COLUMN_TITLE + " TEXT, " +
            MovieContract.MovieTable.COLUMN_RELEASE_DATE + " TEXT, " +
            MovieContract.MovieTable.COLUMN_VOTE_AVERAGE + " REAL, " +
            MovieContract.MovieTable.COLUMN_OVERVIEW + " TEXT, " +
            MovieContract.MovieTable.COLUMN_POSTER_PATH + " TEXT)";
    private static final String[] DATABASE_SCRIPT = {
            MOVIE_DLL
    };
}
