package me.biubiubiu.one.ui.view;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


public class ValueDateView extends Button implements View.OnClickListener {
    // date and time
    public int mYear;
    public int mMonth;
    public int mDay;

    public ValueDateView(Context context, AttributeSet attr) {
        super(context, attr);
        setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateDisplay();
            }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                updateDisplay();
            }
        };

    private DatePickerDialog mDialog =  new DatePickerDialog(getContext(),
                                                             mDateSetListener,
                                                             mYear, mMonth, mDay);


    public void onClick(View view) {
        mDialog.show();
    }

    private void updateDisplay() {
        String dateStr =  new StringBuilder()
            .append(mYear).append("年")
            .append(mMonth + 1).append("月")
            .append(mDay).append("日")
            .toString();
        setText(dateStr);
    }

}
