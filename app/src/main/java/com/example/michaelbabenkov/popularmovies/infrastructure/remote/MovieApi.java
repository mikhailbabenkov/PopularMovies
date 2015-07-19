package com.example.michaelbabenkov.popularmovies.infrastructure.remote;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.FeedBacksResponce;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.MoviesResponse;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.VideosResponce;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public interface MovieApi {

    @GET("/3/discover/movie")
    void getMovies(@Query("sort_by") String sortBy, Callback<MoviesResponse> response);
    @GET("/3/movie/{id}/videos")
    void getVideos(@Path("id") long groupId, Callback<VideosResponce> response);
    @GET("/3/movie/{id}/reviews")
    void getFeedbacks(@Path("id") long groupId, Callback<FeedBacksResponce> response);
}
