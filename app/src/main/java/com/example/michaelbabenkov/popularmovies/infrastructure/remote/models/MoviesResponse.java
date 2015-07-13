package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class MoviesResponse {
    @SerializedName("page")
    private String mPage;
    @SerializedName("results")
    private Movie[] mMovies;
    @SerializedName("total_pages")
    private Integer mTotalPages;
    @SerializedName("total_results")
    private Integer mTotalResults;

    public Movie[] getMovies() {
        return mMovies;
    }
}
