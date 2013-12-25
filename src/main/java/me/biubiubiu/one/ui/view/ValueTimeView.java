package me.biubiubiu.one.ui.view;

import java.util.Calendar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;


public class ValueTimeView extends Button implements View.OnClickListener {
    // date and time
    public int mHour;
    public int mMinute;
    
    public ValueTimeView(Context context, AttributeSet attr) {
        super(context, attr);
        setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateDisplay();
    }
    
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                updateDisplay();
            }
        };

    
    private TimePickerDialog mDialog =  new TimePickerDialog(getContext(),
                                                             mTimeSetListener, mHour, mMinute, false);

    public void onClick(View view) {
        mDialog.show();
    }

    private void updateDisplay() {
        String timeStr =  new StringBuilder()
            .append(pad(mHour))
            .append(":")
            .append(pad(mMinute)).toString();
        setText(timeStr);
    }
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

}
