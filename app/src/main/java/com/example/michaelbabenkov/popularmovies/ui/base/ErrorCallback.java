package com.example.michaelbabenkov.popularmovies.ui.base;

import retrofit.RetrofitError;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public interface ErrorCallback {
    void onError(final String errorDescription);
    void onNetworkError(final RetrofitError networkError);
}
