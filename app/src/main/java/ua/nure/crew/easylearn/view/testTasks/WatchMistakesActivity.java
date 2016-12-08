package ua.nure.crew.easylearn.view.testTasks;

import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.SimpleLoader;
import ua.nure.crew.easylearn.data.models.Question;
import ua.nure.crew.easylearn.data.models.Topic;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class WatchMistakesActivity extends AppCompatActivity {

    private List<Question> mQuestions = new ArrayList<>();
    private List<String> mAnswers;

    FragmentPagerAdapter adapter;
    NonscrollableViewPager mPager;

    public class TestTasksAdapter extends FragmentPagerAdapter {

        TestTasksAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mQuestions.size();
        }

        @Override
        public Fragment getItem(int position) {
            Question q = mQuestions.get(position);
            Bundle args = new Bundle();

            args.putString(TestTasksActivity.QUESTION, q.getQuestionText());
            ArrayList<String> answers = new ArrayList<>(q.getAnswers());
            args.putStringArrayList(TestTasksActivity.ANSWERS, answers);
            args.putString(TestTasksActivity.CORRECT_ANSWER, q.getRightAnswer());
            args.putString(TestTasksActivity.CHOSEN_ANSWER, mAnswers.get(position));

            WatchMistakesFragment fragment = new WatchMistakesFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        Bundle args = getIntent().getExtras();

        mAnswers = args.getStringArrayList(TestTasksActivity.ANSWERS);

        List<ParcelableQuestion> parcelableQuestions = args.getParcelableArrayList(
                TestTasksActivity.QUESTIONS);
        for (ParcelableQuestion pq : parcelableQuestions) {
            Question q = new Question(pq.question, pq.answers, pq.rightAnswer);
            mQuestions.add(q);
        }

        setContentView(R.layout.tests_activity);

        mPager = (NonscrollableViewPager) findViewById(R.id.test_tasks_pager);
        adapter = new WatchMistakesActivity.TestTasksAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setScrollable(true);
    }
}
