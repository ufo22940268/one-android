package me.biubiubiu.one.ui;

import me.biubiubiu.one.R;
import me.biubiubiu.one.core.Constants;
import me.biubiubiu.one.ui.view.ValuePositionButton;

import me.biubiubiu.one.ui.view.ValueSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Views;
import butterknife.InjectView;

public class ProfileActivity extends BaseActivity {

    private ValuePositionButton mMyLocView;
    @InjectView(R.id.sex) ValueSpinner mSexView;
    @InjectView(R.id.age_segment) ValueSpinner mAgeSegmentView;

    static public final String[][] PROJECTION_SEX = {
        {"男", "0"},
        {"女", "1"},
    };

    static public final String[][] PROJECTION_AGE_SEGMENT = {
        {"10", "10"},
        {"20", "20"},
        {"30", "30"},
    };
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.profile_activity);
        initViews();
        setActionBarTitle("我的个人信息");
        Views.inject(this);
    }

    private void initViews() {
        mMyLocView = (ValuePositionButton)findViewById(R.id.my_loc);
        mSexView.setup(PROJECTION_SEX);
        mAgeSegmentView.setup(PROJECTION_AGE_SEGMENT);
    }

    public void submit(View view) {

    }
    
    @Override
    protected void onActivityResult(int req, int resultCode, Intent arg2) {
        if (req == Constants.REQUEST_GET_LOCATION && resultCode == RESULT_OK) {
            int id = arg2.getIntExtra("view_id", 0);
            String title = arg2.getStringExtra("title");
            float lat = arg2.getFloatExtra("lat", 0);
            float lng = arg2.getFloatExtra("lng", 0);
            ValuePositionButton vpb = mMyLocView;
            vpb.setText(title);
            vpb.setLat(lat);
            vpb.setLng(lng);
        }
    }

}
