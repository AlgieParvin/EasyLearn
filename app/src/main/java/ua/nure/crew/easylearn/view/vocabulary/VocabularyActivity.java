package ua.nure.crew.easylearn.view.vocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class VocabularyActivity extends AppCompatActivity {

    String mTopic;
    List<Translation> mTranslationsList;
    RecyclerView mVocabularyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_activity);

        Intent intent = getIntent();
        mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);

        mVocabularyRecyclerView = (RecyclerView) findViewById(R.id.vocabulary_recycler_view);
        mVocabularyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVocabularyRecyclerView.setHasFixedSize(true);

        List<Word> words;
        try {
            Topic t = SimpleLoader.getInstance().loadTopic(getAssets(), mTopic);
            words = t.getWords();
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
    }
}