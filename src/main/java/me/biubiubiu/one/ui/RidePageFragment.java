package me.biubiubiu.one.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public class RidePageFragment extends PageFragment implements AdapterView.OnItemClickListener {
    
    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        View view = super.onCreateView(arg0, arg1, arg2);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
        String id = mAdapter.getItemData(position).get("_id");
        Intent intent = new Intent(getActivity(), RideDetailActivity.class);
        intent.putExtra("id", id);
        getActivity().startActivity(intent);
    }
}
