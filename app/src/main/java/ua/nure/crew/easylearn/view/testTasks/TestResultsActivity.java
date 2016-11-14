package ua.nure.crew.easylearn.view.testTasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ua.nure.crew.easylearn.R;

public class TestResultsActivity extends AppCompatActivity {

    static int currentQuestion = 0;

    FragmentPagerAdapter adapter;
    NonscrollableViewPager mPager;

    public class TestTasksAdapter extends FragmentPagerAdapter {

        TestTasksAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TestTasksActivity.mQuestions.size();
        }

        @Override
        public Fragment getItem(int position) {
            currentQuestion = position;
            Log.i("PAGE", "" + position + " current = " + currentQuestion);

            Bundle args = new Bundle();
            args.putInt("ANSWER", position);
            TestResultsFragment fragment = new TestResultsFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_test_tasks);

        mPager = (NonscrollableViewPager) findViewById(R.id.test_tasks_pager);
        adapter = new TestResultsActivity.TestTasksAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setScrollable(true);
    }
}
