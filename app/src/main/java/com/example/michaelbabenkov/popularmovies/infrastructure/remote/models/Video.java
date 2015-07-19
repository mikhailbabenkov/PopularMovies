package com.example.michaelbabenkov.popularmovies.infrastructure.remote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaelbabenkov on 14/07/15.
 */
public class Video extends BaseModel {
    @SerializedName("iso_639_1")
    private String mIso;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private Integer mSize;
    @SerializedName("type")
    private String mType;

    public String getIso() {
        return mIso;
    }

    public String getKey() {
        return mKey;
    }

    public String getName() {
        return mName;
    }

    public String getSite() {
        return mSite;
    }

    public Integer getSize() {
        return mSize;
    }

    public String getType() {
        return mType;
    }
}
