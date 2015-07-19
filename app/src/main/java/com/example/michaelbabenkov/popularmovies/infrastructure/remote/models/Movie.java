package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import android.database.Cursor;

import com.example.michaelbabenkov.popularmovies.infrastructure.data.MovieContract;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public class Movie extends BaseModel{
    @SerializedName("adult")
    private Boolean mIsAdult;
    @SerializedName("backdrop_path")
    private String mBackDropPath;
    @SerializedName("genre_ids")
    private Integer[] mGenreIds;

    @SerializedName("original_language")
    private String mLanguage;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mIsVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Integer mVoteCount;

    public Boolean getIsAdult() {
        return mIsAdult;
    }

    public String getBackDropPath() {
        return mBackDropPath;
    }

    public Integer[] getGenreIds() {
        return mGenreIds;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public String getTitle() {
        return mTitle;
    }

    public Boolean getIsVideo() {
        return mIsVideo;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setIsAdult(Boolean isAdult) {
        mIsAdult = isAdult;
    }

    public void setBackDropPath(String backDropPath) {
        mBackDropPath = backDropPath;
    }

    public void setGenreIds(Integer[] genreIds) {
        mGenreIds = genreIds;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setIsVideo(Boolean isVideo) {
        mIsVideo = isVideo;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        mVoteCount = voteCount;
    }

    public static Movie fromCursor(final Cursor cursor){
        final Movie movie = new Movie();
        movie.setId(cursor.getString(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_ID)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_TITLE)));
        movie.setPosterPath(cursor.getString(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_POSTER_PATH)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_OVERVIEW)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_RELEASE_DATE)));
        movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieTable.COLUMN_VOTE_AVERAGE)));
        return movie;
    }
}
