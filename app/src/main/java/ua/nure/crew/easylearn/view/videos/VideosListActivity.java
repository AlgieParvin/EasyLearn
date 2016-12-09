package ua.nure.crew.easylearn.view.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.data.models.Video;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

/**
 * Created by algie on 12/9/16.
 */

public class VideosListActivity extends AppCompatActivity {

    String mTopic;
    List<Video> mVideosList;

    RecyclerView mVideosRecyclerView;

    @Override
    protected void onActivityResult(int request, int result, Intent intent) {
        if (request == 1) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.video_list_activity);

        Intent intent = getIntent();

        mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);

        try {
            Topic topic = SimpleLoader.getInstance().loadTopic(getAssets(), mTopic);
            mVideosList  = topic.getVideos();
        } catch (DataLoadingException e) {
            // then ...
        }

        mVideosRecyclerView = (RecyclerView) findViewById(R.id.videos_recycler_view);
        mVideosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVideosRecyclerView.setHasFixedSize(true);

        mVideosRecyclerView.setAdapter(new VideosAdapter(mVideosList, this));
    }
}
