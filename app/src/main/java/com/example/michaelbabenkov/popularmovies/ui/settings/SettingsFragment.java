package com.example.michaelbabenkov.popularmovies.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.michaelbabenkov.popularmovies.R;

/**
 * Created by michaelbabenkov on 13/07/15.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_xml);
    }
}
