package me.biubiubiu.one.ui;

import java.util.Map;

import me.biubiubiu.one.R;
import me.biubiubiu.one.core.Constants;
import me.biubiubiu.one.ui.view.ValueDateView;
import me.biubiubiu.one.ui.view.ValuePositionButton;
import me.biubiubiu.one.ui.view.ValueSpinner;
import me.biubiubiu.one.ui.view.ValueTimeView;
import me.biubiubiu.one.util.HttpHandler;
import me.biubiubiu.one.util.LocationUtils;
import me.biubiubiu.one.util.TableValidator;
import me.biubiubiu.one.util.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.Views;

import com.loopj.android.http.RequestParams;
/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class PostRideActivity extends BaseActivity  {

    @InjectView(R.id.form) TableLayout mForm;
    @InjectView(R.id.price) TextView mPriceView;
    @InjectView(R.id.people) ValueSpinner mPeopleView;
    @InjectView(R.id.car_type) ValueSpinner mCarTypeView;
    @InjectView(R.id.start_loc) ValuePositionButton mStartLocView;
    @InjectView(R.id.dest_loc) ValuePositionButton mDestLocView;
    @InjectView(R.id.wait_time) ValueSpinner mWaitTimeView;
    @InjectView(R.id.pick_date) ValueDateView mPickDate;
    @InjectView(R.id.distance) TextView mDistanceView;
    @InjectView(R.id.pick_time) ValueTimeView mPickTime;

    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;

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
    
    public void submit(View view) {
        if (!new TableValidator(mForm).validate()) {
            return;
        }
        
        Map<String, String> map = ViewUtils.collectForm(mForm); 
        map.put("start_off_time", ViewUtils.buildDate(mPickDate, mPickTime));
        map.put("start_lat", String.valueOf(mStartLocView.getLat()));
        map.put("start_lng", String.valueOf(mStartLocView.getLng()));
        map.put("dest_lat", String.valueOf(mDestLocView.getLat()));
        map.put("dest_lng", String.valueOf(mDestLocView.getLng()));

        RequestParams params = ViewUtils.toRequestParams(map);
        HttpHandler handler = new HttpHandler(this);
        handler.post("rides", params);
    }

    @Override
    protected void onActivityResult(int req, int resultCode, Intent arg2) {
        if (req == Constants.REQUEST_GET_LOCATION && resultCode == RESULT_OK) {
            int id = arg2.getIntExtra("view_id", 0);
            String title = arg2.getStringExtra("title");
            float lat = arg2.getFloatExtra("lat", 0);
            float lng = arg2.getFloatExtra("lng", 0);
            ValuePositionButton vpb = null;
            if (id == R.id.start_loc) {
                vpb = mStartLocView;
            } else {
                vpb = mDestLocView;
            }
            
            vpb.setText(title);
            vpb.setLat(lat);
            vpb.setLng(lng);

            if (mStartLocView.getLat() != 0 && mDestLocView.getLat() != 0) {
                updateDistance();
            }
        }
    }

    private void updateDistance() {
        int distance = LocationUtils.distance(mStartLocView.getLat(),
                                              mStartLocView.getLng(),
                                              mDestLocView.getLat(),
                                              mDestLocView.getLng());
        mDistanceView.setText(String.valueOf(distance) + "米");
    }
}
