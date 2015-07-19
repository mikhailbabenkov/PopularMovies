package com.example.michaelbabenkov.popularmovies.ui.details;

import android.app.Fragment;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.ui.base.SinglePaneActivity;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class DetailsActivity extends SinglePaneActivity {
    public static final String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected int setResourceXml() {
        return R.layout.activity_single_pane;
    }

    @Override
    protected Fragment onCreatePane() {
        return new DetailsFragment();
    }
}
