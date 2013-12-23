package me.biubiubiu.one.ui;

import java.util.HashMap;
import java.util.Map;

import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler.ResponseHandler;
import me.biubiubiu.one.util.Parser;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Views;

public class RideDetailActivity extends BaseActivity {

    private TextView mNameView;
    

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.ride_detail_activity);
        initViews();
        String id = getIntent().getStringExtra("id");
        loadData(id);
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

    private void loadMapToView(Map<String, String> map) {
        mNameView.setText(map.get("user_nickname"));
    }
}
