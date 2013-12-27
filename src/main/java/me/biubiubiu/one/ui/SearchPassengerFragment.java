package me.biubiubiu.one.ui;
import me.biubiubiu.one.R;
import me.biubiubiu.one.ui.view.ValueDateView;
import me.biubiubiu.one.ui.view.ValuePositionButton;
import me.biubiubiu.one.ui.view.ValueSpinner;
import me.biubiubiu.one.ui.view.ValueTimeView;
import me.biubiubiu.one.util.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchPassengerFragment extends SearchFragment implements View.OnClickListener {

    protected String[][] PROJECTION_TYPE = {
        {"自驾车", "0"},
        {"出租车", "1"},
    };
    
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("mode", "passenger");
        intent.putExtra("start_lat", mStartLocView.getLat());
        intent.putExtra("start_lng", mStartLocView.getLng());
        intent.putExtra("dest_lat", mDestLocView.getLat());
        intent.putExtra("dest_lng", mDestLocView.getLng());
        intent.putExtra("type", mTypeView.getValue());
        
        String dateStr = ViewUtils.buildDate(mDateView, mTimeView);
        intent.putExtra("start_off_time", dateStr);
        startActivity(intent);
    }
}
