package com.example.michaelbabenkov.popularmovies.ui.settings;

import android.app.Fragment;
import android.view.Menu;

import com.example.michaelbabenkov.popularmovies.ui.base.SinglePaneActivity;

/**
 * Created by michaelbabenkov on 13/07/15.
 */
public class SettingsActivity extends SinglePaneActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected Fragment onCreatePane() {
        return new SettingsFragment();
    }
}
