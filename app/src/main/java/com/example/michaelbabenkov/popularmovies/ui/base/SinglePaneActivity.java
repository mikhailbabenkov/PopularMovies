package com.example.michaelbabenkov.popularmovies.ui.base;

import android.app.Fragment;
import android.os.Bundle;

import com.example.michaelbabenkov.popularmovies.R;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public abstract class SinglePaneActivity extends BaseActivity{
    private Fragment mFragment;
    private boolean mTwoPane;
    private Fragment mSlaveFragment;
    private static final String SLAVE_TAG = SinglePaneActivity.class.getSimpleName()+ " :slave";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResourceXml());

        if (savedInstanceState == null) {
            mFragment = onCreatePane();
            mFragment.setArguments(intentToFragmentArguments(getIntent()));
            getFragmentManager().beginTransaction()
                    .add(R.id.root_container, mFragment, "single_pane")
                    .commit();
        } else {
            mFragment = getFragmentManager().findFragmentByTag("single_pane");
        }

        if (findViewById(R.id.slave_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                mSlaveFragment =  setSlaveFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.slave_container, mSlaveFragment, SLAVE_TAG)
                        .commit();
            }else{
                mSlaveFragment = getFragmentManager().findFragmentByTag(SLAVE_TAG);
            }
        } else {
            mTwoPane = false;
        }
    }

    protected abstract int setResourceXml();

    protected Fragment setSlaveFragment(){
        return null;
    }
    /**
     * Called in <code>onCreate</code> when the fragment constituting this activity is needed.
     * The returned fragment's arguments will be set to the intent used to invoke this activity.
     */
    protected abstract Fragment onCreatePane();

    public Fragment getFragment() {
        return mFragment;
    }

    public boolean isTwoPane() {
        return mTwoPane;
    }

    public Fragment getSlaveFragment() {
        return mSlaveFragment;
    }
}
