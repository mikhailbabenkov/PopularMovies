package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 14/07/15.
 */
public class VideosResponce {
    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private Video[] mVideos;

    public Long getId() {
        return mId;
    }

    public Video[] getVideos() {
        return mVideos;
    }
}
