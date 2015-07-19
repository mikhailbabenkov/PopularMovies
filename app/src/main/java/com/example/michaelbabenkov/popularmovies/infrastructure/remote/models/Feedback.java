package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 15/07/15.
 */
public class Feedback extends BaseModel{
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("content")
    private String mContent;
    @SerializedName("url")
    private String mUrl;

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public String getUrl() {
        return mUrl;
    }
}
