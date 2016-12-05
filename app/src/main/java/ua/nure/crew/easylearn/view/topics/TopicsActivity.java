package ua.nure.crew.easylearn.view.topics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.OptionsLoader;
import ua.nure.crew.easylearn.data.models.Option;
import ua.nure.crew.easylearn.data.models.Options;
import ua.nure.crew.easylearn.exceptions.DataLoadingException;
import ua.nure.crew.easylearn.view.enterTest.DialogueActivity;

public class TopicsActivity extends AppCompatActivity {

    public static final String LEVEL_PREF = "LEVEL_PREF";

    HashMap<String, Integer> sTopicsImagesHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_activity);

        sTopicsImagesHashMap.put("Crime and Punishment", R.drawable.crime);
        sTopicsImagesHashMap.put("My Family", R.drawable.family);
        sTopicsImagesHashMap.put("Travelling", R.drawable.travel);
        sTopicsImagesHashMap.put("The Universe", R.drawable.universe);
        sTopicsImagesHashMap.put("Wars and weapons", R.drawable.wars_and_weapons);

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

        // getting info about previously chosen level
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String level = sp.getString(LEVEL_PREF, "None");

        // starting a enter_test_dialogue about entry test if 'level' equals to None
        if (level.equals("None")) {
            level = "Easy";
            Intent intent = new Intent(this, DialogueActivity.class);
            startActivity(intent);
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

        Options options;
        String tabTitles[] = new String[] { "Easy", "Medium", "High" };
        Context context;

        public TopicsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
            try {
                options = OptionsLoader.getInstance().loadOptions(getAssets().open("options"));
                for (Option opt : options.getOptions()) {
                    Log.i("OPTION", opt.getCurrentValue());
                }
            } catch (IOException | DataLoadingException e) {
                Log.i("OPTIONS", "can't load options from xml");
            }
        }
        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TopicsFragment.newInstance("Easy");
                case 1:
                    return TopicsFragment.newInstance("Medium");
                case 2:
                    return TopicsFragment.newInstance("Hard");
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
