package com.example.michaelbabenkov.popularmovies;

import android.app.Application;

import com.example.michaelbabenkov.popularmovies.infrastructure.utils.UiHelper;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class MovieApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UiHelper.INSTANCE.setContext(this).calculateScreenWidth();
    }
}
