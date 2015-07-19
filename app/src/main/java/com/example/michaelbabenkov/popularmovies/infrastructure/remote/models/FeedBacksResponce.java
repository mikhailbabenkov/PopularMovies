package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 14/07/15.
 */
public class FeedBacksResponce {
    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private Feedback[] mFeedbacks;
    @SerializedName("page")
    private Integer mPage;
    @SerializedName("total_pages")
    private Integer mTotalPages;
    @SerializedName("total_results")
    private Integer mTotalResults;

    public Long getId() {
        return mId;
    }

    public Feedback[] getFeedbacks() {
        return mFeedbacks;
    }

    public Integer getPage() {
        return mPage;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }
}
