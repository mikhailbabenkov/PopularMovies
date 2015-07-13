package com.example.michaelbabenkov.popularmovies.infrastructure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Movie;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.UiHelper;
import com.squareup.picasso.Picasso;

/**
 * Created by michaelbabenkov on 10/07/15.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    public MovieAdapter(Context context) {
        super(context, 0);
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_poster, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.view_holder);
        }

        viewHolder.bind( getItem(position));

        return convertView;
    }

    class ViewHolder{
        private ImageView mPosterImageView;
        ViewHolder (final View view){
            mPosterImageView = (ImageView) view.findViewById(R.id.poster_image_view);
            view.setTag(R.id.view_holder, this);
        }

        public void bind(final Movie movie){
            //calculate height base on screen width (assuming that we have 3 columns and 0.67 proportion)
            float height = (float)(UiHelper.INSTANCE.getScreenWidth())/3f*1.33f;
            mPosterImageView.getLayoutParams().height = (int) height;
            //load pic with picasso
            Picasso.with(mPosterImageView.getContext())
                    .load(Constants.POSTER_ENDPOINT + movie.getPosterPath())
                    .placeholder(android.R.drawable.ic_delete)
                    .into(mPosterImageView);
        }
    }
}
