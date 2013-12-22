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

public class ValuePositionButton extends Button {

    private float lat;
    private float lng;

    
    public ValuePositionButton(Context context, AttributeSet attr) {
        super(context, attr);
    }

    /**
     * @return the lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public float getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(float lng) {
        this.lng = lng;
    }
    
}
