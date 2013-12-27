package me.biubiubiu.one.ui;

import java.util.List;
import java.util.Map;

import java.util.Stack;

import me.biubiubiu.one.R;
import me.biubiubiu.one.ui.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class MapAdapter extends BaseAdapter {

    private List<Map<String, String>> mData;
    
    public void loadData(List<Map<String, String>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public Map<String, String> getItemData(int position) {
        return mData.get(position);
    }


    public int getCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }
}
