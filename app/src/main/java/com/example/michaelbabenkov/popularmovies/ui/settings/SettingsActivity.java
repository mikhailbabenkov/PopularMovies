package com.example.michaelbabenkov.popularmovies.ui.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.ui.base.SinglePaneActivity;

/**
 * Created by michaelbabenkov on 13/07/15.
 */
public class SettingsActivity extends SinglePaneActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected int setResourceXml() {
        return R.layout.activity_single_pane;
    }

    @Override
    protected Fragment onCreatePane() {
        return new SettingsFragment();
    }
}
