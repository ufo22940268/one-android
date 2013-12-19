package me.biubiubiu.one.ui;

import me.biubiubiu.one.ui.view.*;

import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.*;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import me.biubiubiu.one.BootstrapServiceProvider;
import me.biubiubiu.one.R;
import me.biubiubiu.one.core.BootstrapService;
import me.biubiubiu.one.util.SafeAsyncTask;

import com.viewpagerindicator.TitlePageIndicator;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;


/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class PostRideActivity extends BootstrapFragmentActivity  {

    @InjectView(R.id.form) TableLayout form;
    @InjectView(R.id.price) TextView mPriceView;
    @InjectView(R.id.people) ValueSpinner mPeopleView;
    @InjectView(R.id.car_type) ValueSpinner mCarTypeView;
    @InjectView(R.id.wait_time) ValueSpinner mWaitTimeView;
    static public final String[][] spinner_map_people = {
        {"1人", "1"},
        {"2人", "2"},
        {"3人", "3"},
    };
    static public final String[][] spinner_map_car_type = {
        {"自驾车", "0"},
        {"出租车", "1"},
    };
    static public final String[][] spinner_map_wait_time = {
        {"1小时", "1"},
        {"2小时", "2"},
        {"3小时", "3"},
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_ride);
        getSupportActionBar().setTitle("看谁能带我");
        Views.inject(this);

        mPeopleView.setup(spinner_map_people);
        mCarTypeView.setup(spinner_map_car_type);
        mWaitTimeView.setup(spinner_map_wait_time);
    }
}
