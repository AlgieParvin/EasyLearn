package ua.nure.crew.easylearn.view.testTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.InitialTest;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class TestTasksActivity extends AppCompatActivity {

    // this fields are used for passing and getting data from one activity to another
    // activity or fragment
    public static final String TOPIC_TAG = "TEST_TASKS_ACTIVITY_TOPIC";
    public static final String PURPOSE_TAG = "TEST_TASKS_ACTIVITY_PURPOSE";

    public static final String ENTRY = "TEST_TASKS_ACTIVITY_ENTRY";
    public static final String TEST = "TEST_TASKS_ACTIVITY_TEST";
    public static final String VIDEOS = "TEST_TASKS_ACTIVITY_VIDEOS";

    public static final String QUESTIONS = "TEST_TASKS_ACTIVITY_QUESTIONS";
    public static final String QUESTION = "TEST_TASKS_ACTIVITY_QUESTION";
    public static final String ANSWERS = "TEST_TASKS_ACTIVITY_ANSWERS";
    public static final String CORRECT_ANSWER = "TEST_TASKS_ACTIVITY_CORRECT_ANSWER";
    public static final String CHOSEN_ANSWER = "TEST_TASKS_ACTIVITY_CHOSEN_ANSWER";

    public static final String LEVEL_TAG = "TEST_TASKS_ACTIVITY_LEVEL_TAG";

    // mPurpose shows whether this activity is used for simple testing, enter testing or as part
    // of a video task
    // it can be assigned to ENTRY, TEST or VIDEOS
    String mPurpose;

    String mTopic;

    int mCorrectAnswers;

    List<Question> mQuestions;
    List<String> mAnswers = new ArrayList<>();

    FragmentPagerAdapter adapter;
    NonscrollableViewPager pager;

    public class TestTasksAdapter extends FragmentPagerAdapter {

        TestTasksAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            // Fragment for each question + fragment with results
            return mQuestions.size() + 1;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == getCount() - 1) {
                return TestResultsFragment.newInstance(mQuestions.size(), mCorrectAnswers);
            }

            Question q = mQuestions.get(position);
            return TestTasksFragment.newInstance(q);
        }
    }

    void checkAnswer(String answer) {
        mAnswers.add(answer);
        int position = pager.getCurrentItem();
        if (mQuestions.get(position).isAnswerRight(answer)) {
            mCorrectAnswers++;
        }
        pager.setCurrentItem(position + 1);
    }

    void prepareForReturn(String level) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(LEVEL_TAG, level);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        Intent intent = getIntent();

        mCorrectAnswers = 0;

        mPurpose = intent.getStringExtra(PURPOSE_TAG);
        if (mPurpose == null) {
            mPurpose = TEST;
        }

        try {
            if (mPurpose.equals(ENTRY)) {
                mQuestions = new TestLoader().getInitialQuestions(getAssets());
            } else if(mPurpose.equals(TEST)) {
                mTopic = intent.getStringExtra(TaskTypeActivity.TOPIC_TAG);
                mQuestions = new TestLoader().getTestQuestions(getAssets(), mTopic);
            }
        } catch (DataLoadingException e) {
            Toast.makeText(this, getString(R.string.test_loading_error), Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.tests_activity);

        pager = (NonscrollableViewPager) findViewById(R.id.test_tasks_pager);
        adapter = new TestTasksAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
