package ua.nure.crew.easylearn.view.topics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {

    private static Map<String, Integer> sTopicsImagesHashMap;

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
                    handleOnClick(view.getContext(), view.getTag().toString());
                }
            });

            mImageView = (ImageView) v.findViewById(R.id.topics_image_view);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleOnClick(view.getContext(), view.getTag().toString());
                }
            });

            mTextView = (TextView) v.findViewById(R.id.topics_text_view);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleOnClick(view.getContext(), view.getTag().toString());
                }
            });
        }

        public void handleOnClick(Context context, String tag) {
            TopicsActivity activity = (TopicsActivity) context;
            Intent intent = new Intent(activity, TaskTypeActivity.class);
            intent.putExtra(TaskTypeActivity.TOPIC_TAG, tag);
            activity.startActivity(intent);
        }
    }

    public TopicsAdapter(String level, Context context, String difficulty) {
        this.mContext = (TopicsActivity) context;
        mDifficulty = difficulty;

        sTopicsImagesHashMap = new HashMap<>();
        sTopicsImagesHashMap.put("Crime and Punishment", R.drawable.crime);

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
        holder.mCardView.setTag(mTopics[position]);

        holder.mTextView.setText(mTopics[position]);
        holder.mTextView.setTag(mTopics[position]);

        holder.mImageView.setImageResource(R.drawable.crime);
        holder.mImageView.setTag(mTopics[position]);
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