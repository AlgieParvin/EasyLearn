package ua.nure.crew.easylearn.view.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ua.nure.crew.easylearn.R;

public class TaskTypeActivity extends AppCompatActivity {

    public static final String TOPIC_TAG = "TASK_TYPE_ACTIVITY_TOPIC";

    String mTopic;
    List<TaskType> mTaskTypeList;
    RecyclerView mTaskTypeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_type_activity);

        Intent intent = getIntent();
        mTopic = intent.getStringExtra(TOPIC_TAG);

        mTaskTypeRecyclerView = (RecyclerView) findViewById(R.id.task_type_recycler_view);
        mTaskTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTaskTypeRecyclerView.setHasFixedSize(true);

        mTaskTypeList = TaskType.loadTypes(getAssets(), mTopic);
        mTaskTypeRecyclerView.setAdapter(new TaskTypeAdapter(mTaskTypeList));
    }


}
