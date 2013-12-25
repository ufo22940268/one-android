package me.biubiubiu.one.ui.view;

import me.biubiubiu.one.core.Constants;
import me.biubiubiu.one.ui.GetPositionActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class ValuePositionButton extends Button implements View.OnClickListener {

    private float lat;
    private float lng;


    public ValuePositionButton(Context context, AttributeSet attr) {
        super(context, attr);
        setOnClickListener(this);
    }
    
    public void onClick(View view) {
        pickLocation(view.getId());
    }

    private void pickLocation(int id) {
        Intent intent = new Intent(getContext(), GetPositionActivity.class);
        intent.putExtra("view_id", id);
        Activity act = (Activity)getContext();
        act.startActivityForResult(intent, Constants.REQUEST_GET_LOCATION);
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
