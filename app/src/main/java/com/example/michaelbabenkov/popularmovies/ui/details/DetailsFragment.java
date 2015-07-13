package com.example.michaelbabenkov.popularmovies.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;
import com.example.michaelbabenkov.popularmovies.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class DetailsFragment extends BaseFragment {
    @InjectView(R.id.title_text_view)
    TextView mTitleTextView;
    @InjectView(R.id.poster_image_view)
    ImageView mPosterImageView;
    @InjectView(R.id.year_text_view)
    TextView mYearTextView;
    @InjectView(R.id.time_text_view)
    TextView mTimeTextView;
    @InjectView(R.id.rank_text_view)
    TextView mRankTextView;
    @InjectView(R.id.description_text_view)
    TextView mDescriptionTextView;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mMovie = (Movie) bundle.getSerializable(Constants.ARGS_SHOW_DETAILS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind();
    }

    private void bind(){
        if(mMovie!=null){
            mTitleTextView.setText(mMovie.getTitle());
            Picasso.with(mPosterImageView.getContext())
                    .load(Constants.POSTER_ENDPOINT + mMovie.getPosterPath())
                    .placeholder(android.R.drawable.ic_delete)
                    .into(mPosterImageView);
            mYearTextView.setText(getString(R.string.release_date,mMovie.getReleaseDate()));
            mTimeTextView.setText("Not Applicable");

            mRankTextView.setText(getString(R.string.rank, mMovie.getVoteAverage()));
            mDescriptionTextView.setText(mMovie.getOverview());
        }
    }

}
