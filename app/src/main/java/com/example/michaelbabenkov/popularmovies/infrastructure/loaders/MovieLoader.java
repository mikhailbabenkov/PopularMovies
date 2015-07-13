package com.example.michaelbabenkov.popularmovies.infrastructure.loaders;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.RestService;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.MoviesResponse;
import com.example.michaelbabenkov.popularmovies.ui.base.ErrorCallback;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public class MovieLoader extends AbsNetworkLoader<MoviesResponse> {
    private final String mSortOrder;
    public MovieLoader(Context context, @NonNull ErrorCallback errorCallback, String sortOrder) {
        super(context, errorCallback);
        mSortOrder = sortOrder;
    }

    @Override
    protected void executeNetworkRequest() {
        RestService.INSTANCE.get().getMovies(mSortOrder, this);
    }
}
