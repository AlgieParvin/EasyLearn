package ua.nure.crew.easylearn.view.videos;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.data.models.Video;
import ua.nure.crew.easylearn.data.models.Word;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;
import ua.nure.crew.easylearn.view.vocabulary.Translation;
import ua.nure.crew.easylearn.view.vocabulary.WordExpandableAdapter;

/**
 * Created by algie on 12/8/16.
 */

public class VideoVocabularyActivity extends AppCompatActivity {

    public static final String VIDEO_POS_TAG = "VIDEO_VOCABULARY_ACTIVITY_VIDEO_POS_TAG";

    String mTopic;
    int mPosition;
    List<Translation> mTranslationsList;

    RecyclerView mVocabularyRecyclerView;

    Button videoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_vocab_activity);

        Intent intent = getIntent();
        mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);

        mPosition = intent.getIntExtra(VIDEO_POS_TAG, 0);

        mVocabularyRecyclerView = (RecyclerView) findViewById(R.id.video_vocab_recycler_view);
        mVocabularyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVocabularyRecyclerView.setHasFixedSize(true);

        List<Word> words;
        try {
            Topic t = SimpleLoader.getInstance().loadTopic(getAssets(), mTopic);
            Log.i("POS", String.valueOf(mPosition));
            words = t.getVideos().get(mPosition).getVocabulary().getWords();
            mTranslationsList = new ArrayList<>(words.size());

            for (int i = 0; i < words.size(); i++)
            {
                mTranslationsList.add(
                        new Translation(words.get(i)));
            }

        } catch (DataLoadingException e) {
            Toast.makeText(this, R.string.vocab_loading_error, Toast.LENGTH_LONG).show();
        }

        mVocabularyRecyclerView.setAdapter(new WordExpandableAdapter(this, mTranslationsList));

        videoButton = (Button) findViewById(R.id.video_watch_video);
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoVocabularyActivity.this, VideoPlayerActivity.class);
                intent.putExtra(TaskTypeActivity.TOPIC_TAG, mTopic);
                intent.putExtra(VIDEO_POS_TAG, mPosition);

                startActivityForResult(intent, 1);
                finish();
            }
        });
    }
}