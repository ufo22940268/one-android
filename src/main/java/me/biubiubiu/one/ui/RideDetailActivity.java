package me.biubiubiu.one.ui;

import java.util.HashMap;
import java.util.Map;

import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler.ResponseHandler;
import me.biubiubiu.one.util.Parser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import butterknife.Views;
import butterknife.InjectView;

public class RideDetailActivity extends BaseActivity implements View.OnClickListener

                                                                private TextView mNameView;
@InjectView(R.id.view_map) TextView mViewMap;
@InjectView(R.id.user_status) TextView mUserStatusView;
@InjectView(R.id.start_addr) TextView mStartAddrView;
@InjectView(R.id.dest_addr) TextView mDestAddrView;
@InjectView(R.id.start_off_time) TextView mStartOffTimeView;
@InjectView(R.id.wait_time) TextView mWaitTimeView;

@Override
protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    setContentView(R.layout.ride_detail_activity);
    Views.inject(this);
    initViews();
    String id = getIntent().getStringExtra("id");
    loadData(id);
    setActionBarTitle("搭车详情");
    mViewMap.setOnClickListener(this);
}

private void initViews() {
    mNameView = (TextView)findViewById(R.id.name);
}

private void loadData(String id) {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("id", id);
    mHttpHandler.get("ride_detail", map, new ResponseHandler() {
            @Override
            public void onSuccess(String result) {
                if (isFinished()) {
                    return;
                }
                Map<String, String> map = Parser.item(result);
                loadMapToView(map);
            }
        });
}
