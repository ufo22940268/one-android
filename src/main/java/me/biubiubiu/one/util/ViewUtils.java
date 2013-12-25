/*
 * ViewUtils.java
 * Copyright (C) 2013 garlic <garlic@meishixing>
 *
 * Distributed under terms of the MIT license.
 */
package me.biubiubiu.one.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.biubiubiu.one.ui.view.ValueDateView;
import me.biubiubiu.one.ui.view.ValueSpinner;
import me.biubiubiu.one.ui.view.ValueTimeView;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;


public class ViewUtils {

    public static <T> List<T> getTypeViews(ViewGroup root, Class<T> cls) {
        return getTypeViews(root, cls, null);
    }

    public static <T> List<T> getTypeViews(ViewGroup root, Class<T> cls, String key) {
        List<T> list = new ArrayList<T>();
        getTypeViews(list, root, cls, key);
        return list;
    }

    public static <T> void getTypeViews(List<T> views, ViewGroup root, Class<T> cls, String key) {

        Resources res = root.getContext().getResources();

        if (root.getChildCount() == 0) {
            return;
        }

        for (int i = 0; i < root.getChildCount(); i ++) {
            View child = root.getChildAt(i);
            if (cls.isInstance(child)) {
                if (key == null || key.equals(getKey(child))) {
                    views.add((T)child);
                }
            }

            if (child instanceof ViewGroup) {
                getTypeViews(views, (ViewGroup)child, cls, key);
            }
        }
    }

    public static String getKey(View view) {
        Resources res = view.getContext().getResources();
        return res.getResourceEntryName(view.getId());
    }

    public static void setEmptyView(Context context, ListView listView, int layout) {
        View empty = LayoutInflater.from(context).inflate(
                                                          layout, listView, false);
        empty.setVisibility(View.GONE);
        ((ViewGroup)listView.getParent()).addView(empty);
        listView.setEmptyView(empty);
    }

    public static String getFormValue(View view) {
        if (view == null) {
            return null;
        }

        if (view instanceof TextView) {
            return ((TextView)view).getText().toString();
        } else {
            return null;
        }
    }

    public static int getLayoutRes(String name) {
        R.layout ft = new R.layout();
        try {
            return (Integer)ft.getClass().getField(name).get(ft);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Resouce name not founded.");
        }
    }

    public static int getIdRes(String name) {
        R.id ft = new R.id();
        try {
            return (Integer)ft.getClass().getField(name).get(ft);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Resouce name not founded.");
        }
    }

    /**
     *
     *
     * @param parent
     * @return
     */
    public static Map<String, String> collectForm(ViewGroup parent) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < parent.getChildCount(); i ++) {
            ViewGroup row = (ViewGroup)parent.getChildAt(i);
            if (row.getChildCount() >= 2) {
                View field = row.getChildAt(1);
                if (field instanceof TextView) {
                    String value = ((TextView)field).getText().toString();
                    map.put(ViewUtils.getKey(field), value);
                } else if (field instanceof ValueSpinner) {
                    ValueSpinner sp = (ValueSpinner)field;
                    String text = (String)sp.getItemAtPosition(sp.getSelectedItemPosition());
                    String value = sp.toValue(text);
                    map.put(ViewUtils.getKey(field), value);
                }
            }
        }
        return map;
    }

    public static RequestParams collectFormRequst(ViewGroup parent) {
        Map<String, String> map = collectForm(parent);
        return toRequestParams(map);
    }

    public static RequestParams toRequestParams(Map<String, String> map) {
        RequestParams params = new RequestParams();
        for (String key : map.keySet()) {
            params.put(key, map.get(key));
        }
        return params;
    }

    public static String buildDate(ValueDateView dateView, ValueTimeView timeView) {
        StringBuffer sb = new StringBuffer();
        sb.append(dateView.mYear).append("-");
        sb.append(dateView.mMonth).append("-");
        sb.append(dateView.mDay).append(" ");
        sb.append(timeView.mHour).append(":");
        sb.append(timeView.mMinute);
        return sb.toString();
    }

}
