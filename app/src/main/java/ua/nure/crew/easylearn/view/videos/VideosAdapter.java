package ua.nure.crew.easylearn.view.videos;

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

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Video;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

/**
 * Created by algie on 12/9/16.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {

    Context mContext;
    List<Video> mVideosList;

    public static class VideoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            ContextWrapper wrapper = (ContextWrapper) view.getContext();
            VideosListActivity context = (VideosListActivity) wrapper.getBaseContext();

            Intent intent = new Intent(context, VideoVocabularyActivity.class);
            intent.putExtra(VideoVocabularyActivity.VIDEO_POS_TAG, getItemId());
            intent.putExtra(TaskTypeActivity.TOPIC_TAG, context.mTopic);
            context.startActivity(intent);
            context.finish();
        }

        CardView mVideoCardView;
        ImageView mVideoSnapshot;
        TextView mVideoTitleTextView;

        public VideoViewHolder(View item) {
            super(item);
            mVideoCardView = (CardView) item.findViewById(R.id.video_card_view);
            mVideoCardView.setOnClickListener(this);

            mVideoSnapshot = (ImageView) item.findViewById(R.id.video_snapshot_image_view);
            mVideoSnapshot.setOnClickListener(this);

            mVideoTitleTextView = (TextView) item.findViewById(R.id.video_title_text_view);
            mVideoTitleTextView.setOnClickListener(this);
        }
    }

    public VideosAdapter(List<Video> videos, Context context) {
        mContext = context;
        mVideosList = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_snapshot_card_view, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = mVideosList.get(position);

        String[] videoParts = video.getVideoUrl().split("\\?")[0].split("/");
        String snaphotUrl = "http://img.youtube.com/vi/" + videoParts[videoParts.length - 1] + "/mqdefault.jpg";

        Picasso.with(mContext)
                .load(snaphotUrl)
                .into(holder.mVideoSnapshot);

        holder.mVideoTitleTextView.setText("12 Angry Men");
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}