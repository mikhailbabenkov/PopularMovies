package com.example.michaelbabenkov.popularmovies.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import retrofit.RetrofitError;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public abstract class BaseFragment extends Fragment{

    private ErrorCallback mErrorCallback = STUB_ERROR;

    protected static final ErrorCallback STUB_ERROR = new ErrorCallback() {
        @Override
        public void onError(String errorDescription) {}
        @Override
        public void onNetworkError(RetrofitError networkError) {}
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mErrorCallback = (ErrorCallback) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass()
                    .getSimpleName()
                    + " does not implement contract interface for "
                    + getClass().getSimpleName());
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mErrorCallback = STUB_ERROR;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected ErrorCallback getErrorCallback() {
        return mErrorCallback;
    }
}
