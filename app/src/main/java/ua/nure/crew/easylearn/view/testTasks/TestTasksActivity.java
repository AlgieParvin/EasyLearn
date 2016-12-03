package ua.nure.crew.easylearn.view.testTasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class TestTasksActivity extends AppCompatActivity {


    String mTopic;

    int mCurrentQuestion;
    int mCorrectAnswers;

    static List<Question> mQuestions;
    static List<String> mAnswers = new ArrayList<>();

    FragmentPagerAdapter adapter;
    NonscrollableViewPager pager;

    public class TestTasksAdapter extends FragmentPagerAdapter {

        TestTasksAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mQuestions.size() + 1;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == getCount() - 1) {
                return new TestFinishFragment();
            }
            return new TestTasksFragment();
        }
    }

    void kill() {
        finish();
    }

    void loadQuestions() {

        Intent intent = getIntent();
        mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);
        try {
            Topic t = SimpleLoader.getInstance().loadTopic(getAssets(), mTopic);
            mQuestions = t.getTest();
        } catch (DataLoadingException e) {
            Toast.makeText(this, "Unfortunately the test is unaccessible", Toast.LENGTH_LONG).show();
        }
    }

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        mCorrectAnswers = 0;
        loadQuestions();

        setContentView(R.layout.activity_test_tasks);

        pager = (NonscrollableViewPager) findViewById(R.id.test_tasks_pager);
        adapter = new TestTasksAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
