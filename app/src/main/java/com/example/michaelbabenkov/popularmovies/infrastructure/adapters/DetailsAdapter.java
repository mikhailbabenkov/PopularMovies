package com.example.michaelbabenkov.popularmovies.infrastructure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaelbabenkov.popularmovies.R;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.BaseModel;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Feedback;
import com.example.michaelbabenkov.popularmovies.infrastructure.remote.models.Video;

import java.util.List;

/**
 * Created by michaelbabenkov on 15/07/15.
 */
public class DetailsAdapter extends ArrayAdapter<BaseModel> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private static final int VIEW_TYPE_COUNT = 2;

    public DetailsAdapter(Context context, List<BaseModel> data) {
        super(context, 0, data);
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof Video ? 0 : 1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder;
        final int viewType = getItemViewType(position);
        if (convertView == null) {

            convertView = mLayoutInflater.inflate(viewType == 0 ?
                    R.layout.item_trailer :
                    R.layout.item_feedback, parent, false);
            viewHolder = viewType == 0 ?
                    new VideoViewHolder(convertView):
                    new FeedbackViewHolder(convertView);
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag(R.id.view_holder);
        }

        viewHolder.bind( getItem(position));

        return convertView;
    }

    abstract class BaseViewHolder<T>{
        protected abstract void bind(final T instance);
    }

    class VideoViewHolder extends BaseViewHolder<Video>{
        private ImageView mTrailerImageView;
        private TextView mTrailerNameTextView;
        VideoViewHolder (final View view){
            mTrailerImageView = (ImageView) view.findViewById(R.id.video_image_button);

            mTrailerNameTextView = (TextView) view.findViewById(R.id.trailer_text_view);
            view.setTag(R.id.view_holder, this);
        }

        public void bind(final Video video){
            mTrailerNameTextView.setText(video.getName());
        }
    }

    class FeedbackViewHolder extends BaseViewHolder<Feedback>{
        private TextView mNameTextView;
        private TextView mContentTextView;
        FeedbackViewHolder (final View view){
            mNameTextView = (TextView) view.findViewById(R.id.name_feedback_text_view);
            mContentTextView = (TextView) view.findViewById(R.id.content_feedback_text_view);
            view.setTag(R.id.view_holder, this);
        }

        public void bind(final Feedback feedback){
            mNameTextView.setText(feedback.getAuthor());
            mContentTextView.setText(feedback.getContent());
        }
    }
}
