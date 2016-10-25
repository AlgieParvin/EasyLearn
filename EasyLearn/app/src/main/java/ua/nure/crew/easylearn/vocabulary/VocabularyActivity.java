package ua.nure.crew.easylearn.vocabulary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ua.nure.crew.easylearn.R;

public class VocabularyActivity extends AppCompatActivity {

    String mTopic;
    List<Translation> mTaskTypeList;
    RecyclerView mTaskTypeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_type);

        mTaskTypeRecyclerView = (RecyclerView) findViewById(R.id.task_type_recycler_view);
        mTaskTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTaskTypeRecyclerView.setHasFixedSize(true);

        mTaskTypeList = Translation.loadData(mTopic);

        mTaskTypeRecyclerView.setAdapter(new WordExpandableAdapter(this, mTaskTypeList));
    }
}