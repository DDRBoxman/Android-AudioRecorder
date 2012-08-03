package in.uncod.android.audiorecorder;

import java.io.File;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class AudioRecorderActivity extends SherlockFragmentActivity implements ActionBar.TabListener,
        ViewPager.OnPageChangeListener {

    ActionBar.Tab recordTab;
    ActionBar.Tab previousTab;
    ViewPager mPager;

    RecorderFragment mRecorderFragment;
    RecordingsFragment mRecordingsFragment;

    ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audio_recorder);

        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setOnPageChangeListener(this);

        mRecorderFragment = new RecorderFragment();
        mRecordingsFragment = new RecordingsFragment();

        mPager.setAdapter(new AudioPagerAdapter(getSupportFragmentManager()));

        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        recordTab = mActionBar.newTab();
        recordTab.setText("Record");
        recordTab.setTabListener(this);
        mActionBar.addTab(recordTab);

        previousTab = mActionBar.newTab();
        previousTab.setText("Previous Recordings");
        previousTab.setTabListener(this);
        mActionBar.addTab(previousTab);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (tab == recordTab) {
            mPager.setCurrentItem(0);
        }
        else if (tab == previousTab) {
            mPager.setCurrentItem(1);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    class AudioPagerAdapter extends FragmentPagerAdapter {

        public AudioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i == 0) {
                return mRecorderFragment;
            }
            else if (i == 1) {
                return mRecordingsFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public File getRecordingStorageDirectory() {
        File dir = new File(getExternalFilesDir(null), "recordings");
        dir.mkdirs();
        return dir;
    }
}
