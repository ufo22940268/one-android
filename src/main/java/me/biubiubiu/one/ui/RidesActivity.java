package me.biubiubiu.one.ui;

import me.biubiubiu.one.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.InjectView;
import butterknife.Views;

import com.viewpagerindicator.TabPageIndicator;


/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class RidesActivity extends BaseActivity  {

    @InjectView(R.id.content_indicator) TabPageIndicator indicator;
    @InjectView(R.id.content_pager) ViewPager pager;
    static public final String[] TITLES = {
        "周围车主信息",
        "信息搜索",
    };

    private ContentAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.rides);
        getSupportActionBar().setTitle("看谁能带我");
        Views.inject(this);

        if (mAdapter == null) {
            mAdapter = new ContentAdapter(getSupportFragmentManager());
        }
        pager.setAdapter(mAdapter);
        indicator.setViewPager(pager);
    }

    private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new RidePageFragment();
            } else {
                return new SearchRideFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position % TITLES.length];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
