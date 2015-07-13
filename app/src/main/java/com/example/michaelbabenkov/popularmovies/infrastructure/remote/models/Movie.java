package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public class Movie implements Serializable {
    @SerializedName("adult")
    private Boolean mIsAdult;
    @SerializedName("backdrop_path")
    private String mBackDropPath;
    @SerializedName("genre_ids")
    private Integer[] mGenreIds;
    @SerializedName("id")
    private Long mId;
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

    public Long getId() {
        return mId;
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
}
