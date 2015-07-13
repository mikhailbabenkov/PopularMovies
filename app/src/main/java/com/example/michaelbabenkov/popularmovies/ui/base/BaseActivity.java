package com.example.michaelbabenkov.popularmovies.ui.base;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.ui.settings.SettingsActivity;

import retrofit.RetrofitError;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements ErrorCallback{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // JUST Override to work it properly
    //WHY IT DOESN'T WORK PROPERLY WHEN I CLICK BACK ON ACTION BAR , BUT HARDWARE BACK BUTTON WORKS FINE :(((((
    // ( I guess because of "parentActivity" flag in Manifest)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_settings:
                onBackPressed();
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    @Override
    public void onError(final String errorDescription) {
        new AlertDialog.Builder(this)
                .setMessage(errorDescription)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void onNetworkError(final RetrofitError networkError) {
        onError(networkError.getMessage());
    }

    /**
     * Converts an intent into a {@link android.os.Bundle} suitable for use as fragment arguments.
     */
    public static Bundle intentToFragmentArguments(Intent intent) {
        Bundle arguments = new Bundle();
        if (intent == null) {
            return arguments;
        }

        final Uri data = intent.getData();
        if (data != null) {
            arguments.putParcelable("_uri", data);
        }

        final Bundle extras = intent.getExtras();
        if (extras != null) {
            arguments.putAll(intent.getExtras());
        }

        return arguments;
    }

    private void showSettings(){
        final Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);
    }
}
