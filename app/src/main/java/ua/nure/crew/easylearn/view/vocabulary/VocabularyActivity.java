package ua.nure.crew.easylearn.view.vocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.data.models.Word;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class VocabularyActivity extends AppCompatActivity {

    String mTopic;
    List<Translation> mTaskTypeList;
    RecyclerView mTaskTypeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_type);

        Intent intent = getIntent();
        mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);

        mTaskTypeRecyclerView = (RecyclerView) findViewById(R.id.task_type_recycler_view);
        mTaskTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTaskTypeRecyclerView.setHasFixedSize(true);

        //mTaskTypeList = Translation.loadData(mTopic);
        List<Word> words;
        try {
            words = SimpleLoader.getInstance().loadTopic(getAssets(), mTopic).getWords();
            mTaskTypeList = new ArrayList<>(words.size());

            for (int i = 0; i < words.size(); i++)
            {
                mTaskTypeList.add(
                    new Translation(words.get(i)));
            }

        } catch (DataLoadingException e) {
            e.printStackTrace(); // Change to showing error to user!!!
        }


        mTaskTypeRecyclerView.setAdapter(new WordExpandableAdapter(this, mTaskTypeList));
    }
}