package com.example.michaelbabenkov.popularmovies.ui.main;

import android.app.Fragment;
import android.content.Intent;

import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;
import com.example.michaelbabenkov.popularmovies.ui.base.SinglePaneActivity;
import com.example.michaelbabenkov.popularmovies.ui.details.DetailsActivity;

public class MainActivity extends SinglePaneActivity implements MainFragment.Contract {

    @Override
    protected Fragment onCreatePane() {
        return new MainFragment();
    }

    @Override
    public void showDetails(Movie movie) {
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.ARGS_SHOW_DETAILS, movie);
        startActivity(intent);
    }
}
