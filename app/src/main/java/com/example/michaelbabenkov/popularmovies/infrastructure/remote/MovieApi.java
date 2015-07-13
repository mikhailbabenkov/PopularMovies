package com.example.michaelbabenkov.popularmovies.infrastructure.remote;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.MoviesResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public interface MovieApi {

    @GET("/3/discover/movie")
    void getMovies(@Query("sort_by") String sortBy, Callback<MoviesResponse> response);
}
