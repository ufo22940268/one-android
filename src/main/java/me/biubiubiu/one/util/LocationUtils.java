package me.biubiubiu.one.util;

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
import android.support.v4.app.*;
import android.support.v4.app.Fragment;

import java.util.*;

public class LocationUtils {
    
    static public int distance(float fromLat, float fromLng, float toLat, float toLng) {
        float pk = (float) (180/3.14169);

        float a1 = fromLat / pk;
        float a2 = fromLng / pk;
        float b1 = toLat / pk;
        float b2 = toLng / pk;

        float t1 = FloatMath.cos(a1)*FloatMath.cos(a2)*FloatMath.cos(b1)*FloatMath.cos(b2);
        float t2 = FloatMath.cos(a1)*FloatMath.sin(a2)*FloatMath.cos(b1)*FloatMath.sin(b2);
        float t3 = FloatMath.sin(a1)*FloatMath.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return (int)(6366000*tt);
    }

}
