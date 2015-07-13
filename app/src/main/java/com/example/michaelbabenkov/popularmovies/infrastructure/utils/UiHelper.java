package com.example.michaelbabenkov.popularmovies.infrastructure.utils;

import android.content.Context;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public enum UiHelper {
    INSTANCE;

    private Context mContext;
    private int mScreenWidth;
    public UiHelper setContext(final Context context){
        mContext = context;
        return this;
    }

    public UiHelper calculateScreenWidth(){
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        return this;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }
}
