package me.biubiubiu.one.ui;

import me.biubiubiu.one.Injector;
import me.biubiubiu.one.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SearchRideFragment extends BaseFragment implements View.OnClickListener {

    private View mSubmitView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) inflater.inflate(R.layout.search_rides_form,
                                                        container, false);
        mSubmitView = parent.findViewById(R.id.submit);
        mSubmitView.setOnClickListener(this);
        setActionBarTitle("搜索结果");
        return parent;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("mode", "search");
        intent.putExtra("lat", 12.2f);
        intent.putExtra("lng", 12.2f);
        startActivity(intent);
    }
}
