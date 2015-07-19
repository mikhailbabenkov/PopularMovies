package com.example.michaelbabenkov.popularmovies.infrastructure.loaders;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.RestService;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.VideosResponce;
import com.example.michaelbabenkov.popularmovies.ui.base.ErrorCallback;

/**
 * Created by michaelbabenkov on 14/07/15.
 */
public class VideoLoader extends AbsNetworkLoader<VideosResponce> {
    private final Long mId;
    public VideoLoader(Context context, @NonNull ErrorCallback errorCallback, Long id) {
        super(context, errorCallback);
        mId = id;
    }

    @Override
    protected void executeNetworkRequest() {
        RestService.INSTANCE.get().getVideos(mId, this);
    }
}
