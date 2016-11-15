package ua.nure.crew.easylearn.view.topics;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.OptionsLoader;
import ua.nure.crew.easylearn.data.models.Option;
import ua.nure.crew.easylearn.data.models.Options;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;

public class TopicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        ViewPager viewPager = (ViewPager) findViewById(R.id.topics_pager);
        TopicsPagerAdapter pagerAdapter =
                new TopicsPagerAdapter(getSupportFragmentManager(), TopicsActivity.this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    class TopicsPagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[] { "Easy", "Medium", "High" };
        Context context;

        public TopicsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TopicsFragment.newInstance("Low");
                case 1:
                    return TopicsFragment.newInstance("Medium");
                case 2:
                    return TopicsFragment.newInstance("High");
                default:
                    return TopicsFragment.newInstance("Easy");
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(TopicsActivity.this).inflate(R.layout.topics_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
    }
}
