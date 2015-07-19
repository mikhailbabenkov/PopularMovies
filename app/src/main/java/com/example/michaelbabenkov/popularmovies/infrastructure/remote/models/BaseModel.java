package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by michaelbabenkov on 15/07/15.
 */
public class BaseModel implements Serializable {
    @SerializedName("id")
    private String mId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
