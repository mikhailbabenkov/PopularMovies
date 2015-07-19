package com.example.michaelbabenkov.popularmovies.ui.main;

import android.app.Fragment;
import android.content.Intent;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;
import com.example.michaelbabenkov.popularmovies.ui.base.SinglePaneActivity;
import com.example.michaelbabenkov.popularmovies.ui.details.DetailsActivity;
import com.example.michaelbabenkov.popularmovies.ui.details.DetailsFragment;

public class MainActivity extends SinglePaneActivity implements MainFragment.Contract {

    @Override
    protected int setResourceXml() {
        return R.layout.activity_main;
    }

    @Override
    protected Fragment setSlaveFragment() {
        return new DetailsFragment();
    }

    @Override
    protected Fragment onCreatePane() {
        return new MainFragment();
    }

    @Override
    public void showDetails(Movie movie) {
        if(isTwoPane()){
            final Fragment fragment = getSlaveFragment();
            if(fragment!=null && fragment instanceof DetailsFragment){
                ((DetailsFragment) fragment).showDetails(movie);
            }
        }else{
            final Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Constants.ARGS_SHOW_DETAILS, movie);
            startActivity(intent);
        }
    }
}
