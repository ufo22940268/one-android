/*
 * BlockAdapter.java
 * Copyright (C) 2013 ccheng <ccheng@cchengs-MacBook-Air.local>
 *
 * Distributed under terms of the MIT license.
 */

package me.biubiubiu.one.ui;

import me.biubiubiu.one.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class BlockAdapter extends BaseAdapter {

    private String[] mTitles;
    private Context mContext;
    private int[] mImages;
    private int[] mBackgrounds;

    public BlockAdapter(Context context, String[] titles, int[] images, int[] backgrounds) {
        mTitles = titles;
        mContext = context;
        mImages = images;
        mBackgrounds = backgrounds;
    }

    public int getCount() {
        return mTitles.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getBlock(mTitles[position], "", mImages[position], mBackgrounds[position]);
    }

    private View getBlock(String major, String minor, int image, int background) {
        View item = LayoutInflater.from(
                mContext).inflate(R.layout.row_item_repo_manage, null, false);
        item.setBackgroundResource(background);
        TextView majorView =  (TextView)item.findViewById(R.id.major);
        majorView.setText(major);
        majorView.setCompoundDrawablesWithIntrinsicBounds(0, image, 0, 0);
        ((TextView)item.findViewById(R.id.minor)).setText(minor);
        return item;
    }

}
