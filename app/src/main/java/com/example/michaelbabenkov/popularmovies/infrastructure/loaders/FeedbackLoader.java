package com.example.michaelbabenkov.popularmovies.infrastructure.loaders;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.RestService;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.FeedBacksResponce;
import com.example.michaelbabenkov.popularmovies.ui.base.ErrorCallback;

/**
 * Created by michaelbabenkov on 14/07/15.
 */
public class FeedbackLoader extends AbsNetworkLoader<FeedBacksResponce> {
    private final Long mId;
    public FeedbackLoader(Context context, @NonNull ErrorCallback errorCallback, Long id) {
        super(context, errorCallback);
        mId = id;
    }

    @Override
    protected void executeNetworkRequest() {
        RestService.INSTANCE.get().getFeedbacks(mId, this);
    }
}
