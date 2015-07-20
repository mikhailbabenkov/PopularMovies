package com.example.michaelbabenkov.popularmovies.ui.details;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.adapters.DetailsAdapter;
import com.example.michaelbabenkov.popularmovies.infrastructure.data.MovieContract;
import com.example.michaelbabenkov.popularmovies.infrastructure.loaders.FeedbackLoader;
import com.example.michaelbabenkov.popularmovies.infrastructure.loaders.VideoLoader;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.BaseModel;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.FeedBacksResponce;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Feedback;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Video;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.VideosResponce;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;
import com.example.michaelbabenkov.popularmovies.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class DetailsFragment extends BaseFragment implements LoaderManager.LoaderCallbacks {
    public static final String TAG = DetailsFragment.class.getSimpleName();
    private static final int LOADER_VIDEOS = 100;
    private static final int LOADER_FEEDBACKS = 101;

    @InjectView(R.id.details_list_view)
    ListView mDetailsListView;
    @InjectView(android.R.id.empty)
    TextView mEmpty;
    private TextView mTitleTextView;
    private ImageView mPosterImageView;
    private TextView mYearTextView;
    private TextView mTimeTextView;
    private TextView mRankTextView;
    private TextView mDescriptionTextView;
    private CheckBox mFavouriteCheckBox;
    private Movie mMovie;
    private DetailsAdapter mAdapter;
    private final List<BaseModel> mObjects = new ArrayList<>();
    private MenuItem mShareMenuItem;
    private ShareActionProvider mShareActionProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mMovie = (Movie) bundle.getSerializable(Constants.ARGS_SHOW_DETAILS);
        } else if (savedInstanceState != null) {
            mMovie = (Movie) savedInstanceState.getSerializable(Constants.ARGS_MOVIE);
        }
        mAdapter = new DetailsAdapter(getActivity(), mObjects);
        if (mMovie != null) {
            getLoaderManager().initLoader(LOADER_VIDEOS, null, this);
            getLoaderManager().initLoader(LOADER_FEEDBACKS, null, this);

        }

        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mShareMenuItem = menu.findItem(R.id.action_share);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareFirstTrailer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View headerView = layoutInflater.inflate(R.layout.header_list_movie_details, null);
        mDetailsListView.setEmptyView(mEmpty);
        mDetailsListView.addHeaderView(headerView);
        mDetailsListView.setAdapter(mAdapter);
        mDetailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Object object = parent.getAdapter().getItem(position);
                if (object instanceof Video) {
                    getActivity().startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + ((Video) object).getKey())));
                }
            }
        });
        mTitleTextView = (TextView) headerView.findViewById(R.id.title_text_view);
        mPosterImageView = (ImageView) headerView.findViewById(R.id.poster_image_view);
        mYearTextView = (TextView) headerView.findViewById(R.id.year_text_view);
        mTimeTextView = (TextView) headerView.findViewById(R.id.time_text_view);
        mRankTextView = (TextView) headerView.findViewById(R.id.rank_text_view);
        mDescriptionTextView = (TextView) headerView.findViewById(R.id.description_text_view);
        mFavouriteCheckBox = (CheckBox) headerView.findViewById(R.id.favourite_check_box);
        bind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getArguments() == null) {
            outState.putSerializable(Constants.ARGS_MOVIE, mMovie);
        }
    }

    private void bind() {
        if (mMovie != null) {
            mTitleTextView.setText(mMovie.getTitle());
            Picasso.with(mPosterImageView.getContext())
                    .load(Constants.POSTER_ENDPOINT + mMovie.getPosterPath())
                    .placeholder(android.R.drawable.ic_delete)
                    .into(mPosterImageView);
            mYearTextView.setText(getString(R.string.release_date, mMovie.getReleaseDate()));
            mTimeTextView.setText("Not Applicable");

            mRankTextView.setText(getString(R.string.rank, mMovie.getVoteAverage()));
            mDescriptionTextView.setText(mMovie.getOverview());

            checkFavourite();

        }
    }

    private void checkFavourite(){

        final ContentResolver contentResolver = getActivity().getContentResolver();
        final Cursor cursor = contentResolver.query(MovieContract.MovieTable.CONTENT_URI,
                null,
                String.format("%s=?",
                        MovieContract.MovieTable.COLUMN_ID),
                new String[]{mMovie.getId()},
                null,
                null);
        if(cursor.getCount()>0){
            mFavouriteCheckBox.setChecked(true);
        }else{
            mFavouriteCheckBox.setChecked(false);
        }
        cursor.close();
        mFavouriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final ContentValues contentValues = new ContentValues();
                    contentValues.put(MovieContract.MovieTable.COLUMN_ID, mMovie.getId());
                    contentValues.put(MovieContract.MovieTable.COLUMN_OVERVIEW, mMovie.getOverview());
                    contentValues.put(MovieContract.MovieTable.COLUMN_POSTER_PATH, mMovie.getPosterPath());
                    contentValues.put(MovieContract.MovieTable.COLUMN_RELEASE_DATE, mMovie.getReleaseDate());
                    contentValues.put(MovieContract.MovieTable.COLUMN_TITLE, mMovie.getTitle());
                    contentValues.put(MovieContract.MovieTable.COLUMN_VOTE_AVERAGE, mMovie.getVoteAverage());
                    contentResolver.insert(MovieContract.MovieTable.CONTENT_URI, contentValues);
                } else {
                    contentResolver.delete(MovieContract.MovieTable.CONTENT_URI,
                            String.format("%s=?", MovieContract.MovieTable.COLUMN_ID),
                            new String[]{mMovie.getId()});
                }
            }
        });
    }

    private void shareFirstTrailer(){
        if(mShareActionProvider == null){
            mShareActionProvider = new ShareActionProvider(getActivity());

        }
        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "http://www.youtube.com/watch?v=" + ((Video) mAdapter.getItem(0)).getKey());
        sendIntent.setType("text/plain");
        MenuItemCompat.setActionProvider(mShareMenuItem, mShareActionProvider);

        mShareActionProvider.setShareIntent(sendIntent);


    }

    public void showDetails(final Movie movie) {
        mShareMenuItem.setVisible(false);
        mObjects.clear();
        mAdapter.notifyDataSetChanged();
        mMovie = movie;
        getLoaderManager().restartLoader(LOADER_VIDEOS, null, this);
        getLoaderManager().restartLoader(LOADER_FEEDBACKS, null, this);
        bind();

    }

    @SuppressWarnings("unchecked")
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_VIDEOS:
                return new VideoLoader(getActivity(), getErrorCallback(),
                        Long.valueOf(mMovie.getId()));
            case LOADER_FEEDBACKS:
                return new FeedbackLoader(getActivity(), getErrorCallback(),
                        Long.valueOf(mMovie.getId()));
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (data != null) {
            switch (loader.getId()) {
                case LOADER_VIDEOS:
                    mObjects.addAll(0, Arrays.asList(((VideosResponce) data).getVideos()));
                    if(mShareMenuItem!=null){
                        mShareMenuItem.setVisible(true);
                    }
                    break;
                case LOADER_FEEDBACKS:
                    mObjects.addAll(Arrays.asList(((FeedBacksResponce) data).getFeedbacks()));
                    checkAddEmptyReview();
                    break;
                default:
                    break;
            }

        }else {
            checkAddEmptyReview();
        }
        mAdapter.notifyDataSetChanged();
    }

    // In case no data from server - add dummy list item so that the movie details can be displayed
    private void checkAddEmptyReview(){
        if(mObjects.isEmpty()) {
            mObjects.add(new Feedback());
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
