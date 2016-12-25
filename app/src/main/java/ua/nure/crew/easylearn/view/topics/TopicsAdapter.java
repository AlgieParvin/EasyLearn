package ua.nure.crew.easylearn.view.topics;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {

    static private final int TOPIC_TAG = 1;
    static private final int LEVEL_TAG = 2;

    private TopicsActivity mContext;
    private String mDifficulty;
    private String[] mTopics;

    public static class TopicsViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public ImageView mImageView;
        public TextView mTextView;

        public TopicsViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTextView.performClick();
                }
            });

            mImageView = (ImageView) v.findViewById(R.id.topics_image_view);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTextView.performClick();
                }
            });

            mTextView = (TextView) v.findViewById(R.id.topics_text_view);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tag = view.getTag().toString();
                    ContextWrapper wrapper = (ContextWrapper)view.getContext();
                    TopicsActivity activity = (TopicsActivity) wrapper.getBaseContext();
                    handleOnClick(activity, tag);
                }
            });
        }

        public void handleOnClick(TopicsActivity activity, String tag) {
            Intent intent = new Intent(activity, TaskTypeActivity.class);
            intent.putExtra(TaskTypeActivity.TOPIC_TAG, tag);
            activity.startActivity(intent);
        }
    }

    public TopicsAdapter(Context context, String difficulty) {
        this.mContext = (TopicsActivity) context;
        mDifficulty = difficulty;

        loadTopics();
    }

    @Override
    public TopicsViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topics_card_item, parent, false);
        TopicsViewHolder vh = new TopicsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TopicsViewHolder holder, int position) {
        String topic = mDifficulty + "/" + mTopics[position];
        holder.mCardView.setTag(topic);

        holder.mTextView.setText(mTopics[position]);
        holder.mTextView.setTag(topic);

        if (mContext.sTopicsImagesHashMap.containsKey(mTopics[position])) {
            holder.mImageView.setImageResource(mContext.sTopicsImagesHashMap.get(mTopics[position]));
        } else {
            holder.mImageView.setImageResource(R.drawable.default_topic);
        }
        holder.mImageView.setTag(topic);
    }

    @Override
    public int getItemCount() {
        return mTopics.length;
    }

    private void loadTopics() {
        try {
            mTopics = SimpleLoader.getInstance().getTopicNames(mContext.getAssets(), mDifficulty);
            for (int i = 0; i < mTopics.length; i++) {
                mTopics[i] = mTopics[i].substring(0, mTopics[i].length() - 4);
            }
        } catch (DataLoadingException e) {
            // Should be done something
            // And I'll do it sometime
            // Maybe
        }
    }
}