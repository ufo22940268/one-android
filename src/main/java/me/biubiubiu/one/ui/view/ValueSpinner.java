package me.biubiubiu.one.ui.view;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.text.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.*;

public class ValueSpinner extends Spinner {

    public String[][] mMap;


    public ValueSpinner(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void setup(String[][] map) {
        mMap = map;

        String[] keys = new String[mMap.length];
        for (int i = 0; i < mMap.length; i ++) {
            keys[i] = mMap[i][0];
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                                                android.R.layout.simple_spinner_item, keys);
        setAdapter(adapter);
    }

}
