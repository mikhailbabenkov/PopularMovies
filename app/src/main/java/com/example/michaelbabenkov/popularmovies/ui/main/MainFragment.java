package com.example.michaelbabenkov.popularmovies.ui.main;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.adapters.MovieAdapter;
import com.example.michaelbabenkov.popularmovies.infrastructure.data.MovieContract;
import com.example.michaelbabenkov.popularmovies.infrastructure.loaders.MovieLoader;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.MoviesResponse;
import com.example.michaelbabenkov.popularmovies.ui.base.BaseFragment;

import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements LoaderManager.LoaderCallbacks {
    public static final String TAG = MainFragment.class.getSimpleName();
    @InjectView(R.id.movies_grid_view)
    GridView mMoviesGridView;
    @InjectView(android.R.id.empty)
    TextView mEmptyView;
    private Contract mContract = sDummyContract;
    private MovieAdapter mAdapter;
    private String mSortingOrder;
    private static final int LOADER_MOVIES = 100;
    private static final String ARGS_SORT_ORDER = TAG + " :sorting_order";
    private static final Contract sDummyContract = new Contract() {
        @Override
        public void showDetails(Movie movie) {

        }
    };
    private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mContract.showDetails((Movie) parent.getAdapter().getItem(position));
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mContract = (Contract) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass().getSimpleName() +
                    " is not implementing " +
                    Contract.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mSortingOrder = savedInstanceState.getString(ARGS_SORT_ORDER);
        }else{
            final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if(defaultSharedPreferences!=null) {
                mSortingOrder = defaultSharedPreferences.getString(
                        getString(R.string.sorting_order_key), getString(R.string.default_sorting));
            }
        }
        getLoaderManager().initLoader(LOADER_MOVIES, null, this);
        mAdapter = new MovieAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMoviesGridView.setAdapter(mAdapter);
        mMoviesGridView.setOnItemClickListener(mOnItemClickListener);
        mMoviesGridView.setEmptyView(mEmptyView);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(reSort()){
            mAdapter.clear();
            getLoaderManager().restartLoader(LOADER_MOVIES, null, this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContract = sDummyContract;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_MOVIES:
                if(mSortingOrder.equals("favourite")){
                    final Uri uri = MovieContract.MovieTable.CONTENT_URI;
                    return new CursorLoader(getActivity(), uri, null , null , null , null);
                }else{
                    return new MovieLoader(getActivity(), getErrorCallback(), mSortingOrder);
                }

            default:
                return null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARGS_SORT_ORDER, mSortingOrder);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        switch (loader.getId()) {
            case LOADER_MOVIES:
                if(data!=null ){
                    mAdapter.clear();
                    if(mSortingOrder.equals("favourite")){
                        populateAdapter((Cursor)data);
                    }else{
                        mAdapter.addAll(((MoviesResponse)data).getMovies());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    private boolean reSort(){
        boolean result;
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(defaultSharedPreferences!=null){
            final String order = defaultSharedPreferences.getString(
                    getString(R.string.sorting_order_key), getString(R.string.default_sorting));
            result = !order.equals(mSortingOrder);
            mSortingOrder = order;
        }else{
            result = false;
            mSortingOrder = getString(R.string.default_sorting);
        }
        return result;
    }

    private void populateAdapter(Cursor data) {
        final Movie [] movies = new Movie[data.getCount()];
        if(data.moveToFirst()){
            movies[data.getPosition()] = Movie.fromCursor(data);
            while(data.moveToNext()){
                final Movie movie = Movie.fromCursor(data);
                movies[data.getPosition()] = movie;
            }
        }
        mAdapter.addAll(movies);
    }

    public interface Contract {
        void showDetails(final Movie movie);
    }
}
