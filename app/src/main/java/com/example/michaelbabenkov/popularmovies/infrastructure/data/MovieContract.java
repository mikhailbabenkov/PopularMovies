package com.example.michaelbabenkov.popularmovies.infrastructure.data;

import android.net.Uri;

import com.example.michaelbabenkov.popularmovies.BuildConfig;

/**
 * Created by michaelbabenkov on 15/07/15.
 */
public class MovieContract {
    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID + ".AUTHORITY";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movie";

    public static final class MovieTable{
        public static final String TABLE_NAME = PATH_MOVIES;

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String COLUMN_ID = "Id";
        public static final String COLUMN_TITLE = "Title";
        public static final String COLUMN_VOTE_AVERAGE = "VoteAverage";
        public static final String COLUMN_POSTER_PATH = "PosterPath";
        public static final String COLUMN_RELEASE_DATE = "ReleaseDate";
        public static final String COLUMN_OVERVIEW = "Overview";
    }
}
