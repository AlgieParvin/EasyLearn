package ua.nure.crew.easylearn.view.rebuses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sembozdemir.viewpagerarrowindicator.library.ViewPagerArrowIndicator;

import java.util.List;

import ua.nure.crew.easylearn.R;
import ua.nure.crew.easylearn.data.dataManaging.RebusLoader;
import ua.nure.crew.easylearn.data.models.Rebus;
import ua.nure.crew.easylearn.view.type.TaskTypeActivity;

public class RebusesActivity extends AppCompatActivity {

    String mTopic;
    List<Rebus> mRebuses;

    class RebusPagerAdapter extends FragmentPagerAdapter {

        public RebusPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RebusFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mRebuses.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);

        mTopic = getIntent().getStringExtra(TaskTypeActivity.TOPIC_TAG);
        mTopic = mTopic.split("/")[1];
        loadRebuses();

        setContentView(R.layout.rebus_activity);

        ViewPager pager = (ViewPager) findViewById(R.id.rebus_view_pager);
        ViewPagerArrowIndicator indicator =
                (ViewPagerArrowIndicator) findViewById(R.id.rebus_pager_arrows_wrapper);

        pager.setAdapter(new RebusPagerAdapter(getSupportFragmentManager()));
        indicator.bind(pager);
    }

    private void loadRebuses() {
        mRebuses = RebusLoader.getInstance().loadRebuses(getAssets(), mTopic).getRebuses();
    }
}
