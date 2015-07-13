package com.example.michaelbabenkov.popularmovies.infrastructure.loaders;

import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.support.annotation.NonNull;

import com.example.michaelbabenkov.popularmovies.ui.base.ErrorCallback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public abstract class AbsNetworkLoader<T> extends Loader<T> implements Callback<T> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */

    private final ErrorCallback mErrorCallback;
    private T mData;

    public AbsNetworkLoader(Context context, @NonNull final ErrorCallback errorCallback) {
        super(context);
        mErrorCallback = errorCallback;
    }

    @Override
    protected void onStartLoading() {
        if(mData != null)
            deliverResult(mData);

        if(takeContentChanged() || mData == null)
            forceLoad();
    }

    @Override
    protected void onForceLoad() {
        executeNetworkRequest();
    }

    @Override
    protected void onStopLoading() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {

        } else {
            cancelLoad();
        }
    }

    @Override
    public void deliverResult(T data) {
        if(isReset()){
            releaseData(data);
            return;
        }

        T oldData = mData;
        mData = data;

        if(isStarted()) {
            super.deliverResult(data);
        }

        if(oldData != null) {
            releaseData(oldData);
        }
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if(mData != null){
            releaseData(mData);
            mData = null;
        }
    }

    protected void releaseData(T data) {}

    protected abstract void executeNetworkRequest();

    @Override
    public void success(T data, Response response) {
        if(isAbandoned()) return;
        deliverResult(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void failure(RetrofitError error) {
        if (isAbandoned()) return;

        mErrorCallback.onNetworkError(error);
        deliverResult(null);
    }
}
