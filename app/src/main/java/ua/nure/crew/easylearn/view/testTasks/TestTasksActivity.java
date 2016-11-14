package ua.nure.crew.easylearn.view.testTasks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.models.Question;

public class TestTasksActivity extends AppCompatActivity {

    int currentQuestion;

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
            return mQuestions.size();
        }

        @Override
        public Fragment getItem(int position) {
            return new TestTasksFragment();
        }
    }

    // should be reimplemented
    void loadQuestions() {
        mQuestions = Arrays.asList(new Question(), new Question(), new Question(), new Question());
    }

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        loadQuestions();

        setContentView(R.layout.activity_test_tasks);

        pager = (NonscrollableViewPager) findViewById(R.id.test_tasks_pager);
        adapter = new TestTasksAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
